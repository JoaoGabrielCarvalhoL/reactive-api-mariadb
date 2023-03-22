package br.com.carv.reactiveexample.mapper;

import br.com.carv.reactiveexample.dto.request.BeerPostRequest;
import br.com.carv.reactiveexample.dto.request.BeerPutRequest;
import br.com.carv.reactiveexample.dto.response.BeerGenericResponse;
import br.com.carv.reactiveexample.model.Beer;

public abstract class BeerMapper {

    public static Beer toBeer(BeerPostRequest beerPostRequest) {
        Beer beer = new Beer(beerPostRequest.getBeerName(), beerPostRequest.getUpc(),
                beerPostRequest.getQuantityOnHands(), beerPostRequest.getUnitPrice());

        return beer;
    }

    public static Beer toBeer(BeerPutRequest beerPutRequest) {
        Beer beer = new Beer(beerPutRequest.getBeerName(), beerPutRequest.getUpc(),
                beerPutRequest.getQuantityOnHands(), beerPutRequest.getUnitPrice());

        return beer;
    }

    public static BeerGenericResponse toBeerGenericResponse(Beer beer) {
        BeerGenericResponse beerGenericResponse = new BeerGenericResponse(beer.getId(), beer.getVersion(),
                beer.getBeerName(), beer.getUpc(), beer.getQuantityOnHands(), beer.getUnitPrice());

        return beerGenericResponse;
    }
}
