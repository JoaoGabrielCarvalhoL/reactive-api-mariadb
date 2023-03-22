package br.com.carv.reactiveexample.controller.impl;

import br.com.carv.reactiveexample.controller.BeerController;
import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import br.com.carv.reactiveexample.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/beers")
public class BeerControllerImpl implements BeerController {

    private final BeerService beerService;

    public BeerControllerImpl(BeerService beerService) {
        this.beerService = beerService;
    }

    @Override
    public ResponseEntity<Mono<BeerGenericResponse>> getBeerById(Long id) {
        return ResponseEntity.ok(this.beerService.findById(id));
    }

    @Override
    public ResponseEntity<Flux<BeerGenericResponse>> getBeerByUPC(String upc) {
        return ResponseEntity.ok(this.beerService.findByUpc(upc));
    }

    @Override
    public ResponseEntity<Void> save(BeerPostRequest beerPostRequest) {
        Mono<BeerGenericResponse> beer = this.beerService.save(beerPostRequest);
        String id = beer.map(BeerGenericResponse::getId).toString();

        return ResponseEntity.created(UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1/beers" + id)
                .build().toUri()).build();
    }

    @Override
    public ResponseEntity<Void> update(BeerPutRequest beerPutRequest) {
        this.beerService.update(beerPutRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Flux<BeerGenericResponse>> allBeers() {
        return ResponseEntity.ok(this.beerService.findAllBeers());
    }
}
