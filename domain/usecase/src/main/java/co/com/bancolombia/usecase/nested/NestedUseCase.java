package co.com.bancolombia.usecase.nested;

import co.com.bancolombia.model.nested.Nested;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class NestedUseCase {

    private final RepositoryCrud<Nested, Integer> repositoryCrud;

    public Mono<Nested> createNested(Nested nested) {
        return repositoryCrud.create(nested);
    }

    public Mono<Nested> findByIdNested(Integer id) {
        return repositoryCrud.findById(id);
    }

    public Flux<Nested> findAllNested() {
        return repositoryCrud.findAll();
    }

    public Mono<Nested> updateNested(Nested nested, Integer id) {
        return repositoryCrud.update(nested, id);
    }

    public Mono<Void> deleteNestedById(Integer id) {
        return repositoryCrud.deleteById(id);
    }
}
