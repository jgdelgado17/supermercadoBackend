package co.com.bancolombia.model.nested;

import java.time.LocalDateTime;
import java.util.List;

import co.com.bancolombia.model.productbuy.ProductBuy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Nested {
    private Integer id;
    private String document;
    private LocalDateTime date;
    private String idType;
    private String clientName;
    private List<ProductBuy> products;
}
