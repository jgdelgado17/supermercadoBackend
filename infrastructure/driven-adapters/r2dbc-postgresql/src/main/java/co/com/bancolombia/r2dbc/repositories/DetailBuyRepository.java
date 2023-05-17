package co.com.bancolombia.r2dbc.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.bancolombia.r2dbc.entities.DetailBuyEntity;

public interface DetailBuyRepository extends ReactiveCrudRepository<DetailBuyEntity, Integer> {
    
}
