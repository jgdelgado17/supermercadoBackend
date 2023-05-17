package co.com.bancolombia.usecase.buy;

import co.com.bancolombia.model.buy.Buy;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BuyUseCase {

    private final RepositoryCrud<Buy, Integer> repositoryCrud;

    public Mono<Buy> createBuy(Buy buy) {
        return repositoryCrud.create(buy);
    }

    public Mono<Buy> findByIdBuy(Integer id) {
        return repositoryCrud.findById(id);
    }

    public Flux<Buy> findAllBuy() {
        return repositoryCrud.findAll();
    }

    public Mono<Buy> updateBuy(Buy buy, Integer id) {
        return repositoryCrud.update(buy, id);
    }

    public Mono<Void> deleteBuyById(Integer id) {
        return repositoryCrud.deleteById(id);
    }
}
