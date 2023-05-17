package co.com.bancolombia.model.detailbuy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Detailbuy {
    private Integer id;
    private Integer product;
    private Integer buy;
}
