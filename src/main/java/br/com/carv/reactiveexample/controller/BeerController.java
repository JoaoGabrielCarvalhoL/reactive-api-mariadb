package br.com.carv.reactiveexample.controller;

import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Mono<BeerGenericResponse>> getBeerById(@PathVariable("id") Long id);

    @GetMapping("/upc/{upc}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Flux<BeerGenericResponse>> getBeerByUPC(@PathVariable("upc") String upc);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> save(@RequestBody @Validated BeerPostRequest beerPostRequest);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> update(@RequestBody BeerPutRequest beerPutRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Flux<BeerGenericResponse>> allBeers();
}
