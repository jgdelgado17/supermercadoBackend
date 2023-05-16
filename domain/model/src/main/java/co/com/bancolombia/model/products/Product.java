package co.com.bancolombia.model.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private Integer id;
    private String name;
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;
}
