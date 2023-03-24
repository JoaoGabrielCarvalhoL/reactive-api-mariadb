package br.com.carv.reactiveexample.controller.config;

import br.com.carv.reactiveexample.controller.handler.BeerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BeerRouterConfig {

    public static final String BEER_BASE_URL = "/beers";
    public static final String BEER_URL_ID = "/beers/{id}";

    @Bean
    public RouterFunction<ServerResponse> beerRoutes(BeerHandler beerHandler) {
        return RouterFunctions.route()
                .GET(BEER_URL_ID, RequestPredicates.accept(MediaType.APPLICATION_JSON), beerHandler::getBeerByID)
                .POST(BEER_BASE_URL, RequestPredicates.accept(MediaType.APPLICATION_JSON), beerHandler::saveBeer)
                .PUT(BEER_BASE_URL, RequestPredicates.accept(MediaType.APPLICATION_JSON), beerHandler::updateBeer)
                .DELETE(BEER_URL_ID, RequestPredicates.accept(MediaType.APPLICATION_JSON), beerHandler::deleteBeer)
                .build();
    }


}
