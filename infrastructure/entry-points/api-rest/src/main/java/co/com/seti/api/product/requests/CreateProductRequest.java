package co.com.seti.api.product.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    private Double price;
}
