package co.com.bancolombia.usecase.detailbuy;

import co.com.bancolombia.model.detailbuy.Detailbuy;
// import co.com.bancolombia.model.Detailbuy.Detailbuy;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DetailbuyUseCase {

    private final RepositoryCrud<Detailbuy, Integer> repositoryCrud;

    public Mono<Detailbuy> createDetailbuy(Detailbuy detailbuy) {
        return repositoryCrud.create(detailbuy);
    }

    public Mono<Detailbuy> findByIdDetailbuy(Integer id) {
        return repositoryCrud.findById(id);
    }

    public Flux<Detailbuy> findAllDetailbuy() {
        return repositoryCrud.findAll();
    }

    public Mono<Detailbuy> updateDetailbuy(Detailbuy detailbuy, Integer id) {
        return repositoryCrud.update(detailbuy, id);
    }

    public Mono<Void> deleteDetailbuyById(Integer id) {
        return repositoryCrud.deleteById(id);
    }
}
