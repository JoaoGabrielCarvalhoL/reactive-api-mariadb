package br.com.carv.reactiveexample.repository;

import br.com.carv.reactiveexample.model.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Long> {

    List<Beer> findByUpc(String upc);
}
