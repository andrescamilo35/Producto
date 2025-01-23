package co.com.seti.api.product.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProductRequest {
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    private String name;

    private Double price;
}
