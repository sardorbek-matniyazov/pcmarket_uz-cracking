package basePackage.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class ProductDto {
    @NotBlank(message = "product name is required")
    private String name;
    @NotNull(message = "product category is required")
    private Long categoryId;
    @NotNull(message = "product brand is required")
    private Long brandId;
    @NotNull(message = "product price is required")
    private double price;
    @NotNull(message = "product information is required")
    private List<Map<String, String>> infos;
}
