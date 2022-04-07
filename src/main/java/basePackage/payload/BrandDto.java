package basePackage.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandDto {
    @NotBlank(message = "Brand name is required")
    private String name;

    @NotBlank(message = "Brand description is required")
    private String description;
}
