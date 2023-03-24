package br.com.carv.reactiveexample.service.impl;

import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import br.com.carv.reactiveexample.exception.BeerNotFoundException;
import br.com.carv.reactiveexample.mapper.BeerMapper;
import br.com.carv.reactiveexample.model.Beer;
import br.com.carv.reactiveexample.repository.BeerRepository;
import br.com.carv.reactiveexample.service.BeerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BeerServiceImpl implements BeerService {

    private final Logger logger = Logger.getLogger(BeerServiceImpl.class.getCanonicalName());

    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Cacheable(cacheNames = "beerCache", key = "#id")
    @Override
    public Mono<BeerGenericResponse> findById(Long id) {
        logger.info("Getting beer by id: " + id);
        return this.beerRepository.findById(id).map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public void deleteBeerId(Long id) {
        logger.info("Deleting beer by id: " + id);
        this.beerRepository.deleteById(id);
    }

    @Override
    public Flux<BeerGenericResponse> findAllBeers() {
        logger.info("Getting all beers");
        return this.beerRepository.findAll().map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public Mono<BeerGenericResponse> save(BeerPostRequest beerPostRequest) {

        Mono<BeerGenericResponse> monoBeerGeneric = this.beerRepository.save(BeerMapper.toBeer(beerPostRequest))
                .map(savedBeer -> BeerMapper.toBeerGenericResponse(savedBeer))
                .doOnSuccess(success -> logger.info("Saving beer into database"));

        monoBeerGeneric.subscribe();

        return monoBeerGeneric;

    }

    @Override
    public Mono<BeerGenericResponse> update(BeerPutRequest beerPutRequest) {
        logger.info("Updating beer into database");
        return this.beerRepository.save(BeerMapper.toBeer(beerPutRequest)).map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public Flux<BeerGenericResponse> findByUpc(String upc) {
        logger.info("Getting beers by upc: " + upc);
        List<Beer> beers = this.beerRepository.findByUpc(upc);
        return Flux.fromIterable(beers).map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public Mono<BeerGenericResponse> saveBeerMono(Mono<BeerPostRequest> beerPostRequestMono) {
        return beerPostRequestMono.map(BeerMapper::toBeer).flatMap(beerRepository::save)
                .map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public Mono<BeerGenericResponse> updateBeerMono(Mono<BeerPutRequest> beerPutRequestMono) {
        return beerPutRequestMono.map(BeerMapper::toBeer).flatMap(beerRepository::save)
                .map(BeerMapper::toBeerGenericResponse);
    }

    @Override
    public Mono<Void> reactiveDelete(Long beerId) {
        return beerRepository.findById(beerId)
                .switchIfEmpty(Mono.error(new BeerNotFoundException("Beer Not Found")))
                .map(beer -> {
                    return beer.getId();
                }).flatMap(foundId -> beerRepository.deleteById(foundId))
                .onErrorResume(error -> error instanceof  BeerNotFoundException, e ->
                        ServerResponse.notFound().build().then());
    }
}
