package co.com.bancolombia.r2dbc.adapters;

import org.springframework.stereotype.Service;

import co.com.bancolombia.model.detailbuy.Detailbuy;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DetailBuyAdapter implements RepositoryCrud<Detailbuy, Integer> {@Override
    public Mono<Detailbuy> create(Detailbuy T) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Mono<Detailbuy> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Flux<Detailbuy> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<Detailbuy> update(Detailbuy T, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
