package br.com.carv.reactiveexample.controller.handler;

import br.com.carv.reactiveexample.controller.config.BeerRouterConfig;
import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.request.BeerRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import br.com.carv.reactiveexample.service.BeerService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
public class BeerHandler {

    private final BeerService beerService;
    private final Validator validator;

    public BeerHandler(BeerService beerService, Validator validator) {
        this.beerService = beerService;
        this.validator = validator;
    }

    private void validate(BeerRequest beerPostRequest) {
        Errors errors = new BeanPropertyBindingResult(beerPostRequest, "beerPostRequest");
        validator.validate(beerPostRequest, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> getBeerByID(ServerRequest request) {
        Long beerId = Long.valueOf(request.pathVariable("id"));

        return beerService.findById(beerId).flatMap(beerGenericResponse -> {
            return ServerResponse.ok().bodyValue(beerGenericResponse);
        }).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveBeer(ServerRequest request) {
        Mono<BeerPostRequest> beerPostRequestMono = request.bodyToMono(BeerPostRequest.class).doOnNext(this::validate);
        return beerService.saveBeerMono(beerPostRequestMono)
                .flatMap(beer -> {
                    return ServerResponse.ok()
                            .header("location", BeerRouterConfig.BEER_BASE_URL + "/" + beer.getId()).build();
                });
    }

    public Mono<ServerResponse> updateBeer(ServerRequest request) {
        Mono<BeerPutRequest> beerPutRequestMono = request.bodyToMono(BeerPutRequest.class).doOnNext(this::validate);
        return beerService.updateBeerMono(beerPutRequestMono)
                .flatMap(beer -> {
                    return ServerResponse.noContent().build();
                });
    }

    public Mono<ServerResponse> deleteBeer(ServerRequest request) {
        Long beerId = Long.valueOf(request.pathVariable("id"));
        return beerService.reactiveDelete(beerId).flatMap(voidMono -> {
            return ServerResponse.noContent().build();
        });

    }
}
