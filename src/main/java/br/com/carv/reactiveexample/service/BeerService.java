package br.com.carv.reactiveexample.service;

import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Mono<BeerGenericResponse> findById(Long id);

    void deleteBeerId(Long id);

    Flux<BeerGenericResponse> findAllBeers();

    Mono<BeerGenericResponse> save(BeerPostRequest beerPostRequest);

    Mono<BeerGenericResponse> update(BeerPutRequest beerPutRequest);

    Flux<BeerGenericResponse> findByUpc(String upc);
}
