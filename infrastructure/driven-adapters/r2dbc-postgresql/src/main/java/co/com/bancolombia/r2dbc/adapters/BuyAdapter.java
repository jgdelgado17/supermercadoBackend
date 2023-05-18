package co.com.bancolombia.r2dbc.adapters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.bancolombia.model.nested.Nested;
import co.com.bancolombia.model.productbuy.ProductBuy;
import co.com.bancolombia.r2dbc.entities.BuyEntity;
import co.com.bancolombia.r2dbc.entities.DetailBuyEntity;
import co.com.bancolombia.r2dbc.repositories.BuyRepository;
import co.com.bancolombia.r2dbc.repositories.DetailBuyRepository;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import co.com.bancolombia.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BuyAdapter implements RepositoryCrud<Nested, Integer> {

    private final ProductUseCase productUseCase;
    private final BuyRepository buyRepository;
    private final DetailBuyRepository detailBuyRepository;

    @Override
    public Mono<Nested> create(Nested nested) {
        List<ProductBuy> listProductsBuy = nested.getProducts();

        BuyEntity entity = BuyEntity.builder()
                .id(nested.getId())
                .document(nested.getDocument())
                .date(LocalDateTime.now())
                .idType(nested.getIdType())
                .clientName(nested.getClientName())
                .build();

        return buyRepository.save(entity)
                .flatMap(buyEntity -> {

                    updateProduct(listProductsBuy, buyEntity.getId());

                    Nested n = new Nested(buyEntity.getId(), buyEntity.getDocument(), buyEntity.getDate(),
                            buyEntity.getIdType(), buyEntity.getClientName(), listProductsBuy);

                    return Mono.just(n);
                });
    }

    @Override
    public Mono<Nested> findById(Integer id) {
        return buyRepository.findById(id)
                .flatMap(buy -> {
                    List<ProductBuy> buys = new ArrayList<>();

                    detailBuyRepository.findByBuy(buy.getId())
                            .flatMap(detailBuy -> {
                                ProductBuy productBuy = new ProductBuy(detailBuy.getProduct(), detailBuy.getQuantity());
                                buys.add(productBuy);
                                return Mono.just(productBuy);
                            });
                    Nested nested = new Nested(buy.getId(), buy.getDocument(), buy.getDate(), buy.getIdType(),
                            buy.getClientName(), buys);

                    return Mono.just(nested);
                });
    }

    @Override
    public Flux<Nested> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<Nested> update(Nested nested, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    private void updateProduct(List<ProductBuy> listProductsBuy, Integer idBuy) {

        for (ProductBuy product : listProductsBuy) {
            Integer id = product.getIdProduct();
            int quantity = product.getQuantity();
            productUseCase.findByIdProduct(id)
                    .flatMap(producto -> {
                        if ((producto.getInInventory() - quantity) >= producto.getMin()) {

                            producto.setInInventory(producto.getInInventory() - quantity);
                            return productUseCase.updateProduct(producto, id)
                                    .doOnNext(pr -> {
                                        DetailBuyEntity detailBuyEntity = new DetailBuyEntity(null, producto.getId(),
                                                idBuy, product.getQuantity());
                                        detailBuyRepository.save(detailBuyEntity).subscribe();
                                    });
                        } else
                            return Mono.error(new Throwable("El inventario está abajo del minimo"));
                    }).subscribe();
        }
    }

}
