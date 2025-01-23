package co.com.seti.api.product;

import co.com.seti.api.product.requests.CreateProductRequest;
import co.com.seti.api.product.requests.UpdateProductRequest;
import co.com.seti.model.common.MessageDTO;
import co.com.seti.model.product.Product;
import co.com.seti.usecase.product.ProductUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;

    @GetMapping(path = "/product/all")
    public ResponseEntity<List<Product>> getProductAll() {
        return ResponseEntity.ok(productUseCase.findAll());
    }

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(productUseCase.findById(id));
    }

    @PostMapping(path = "/product/save")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        Product savedProduct = productUseCase.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping(path = "/product/update")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody UpdateProductRequest request) throws Exception {
        Product product = Product.builder()
                .productId(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build();
        Product savedProduct = productUseCase.updateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<MessageDTO<String>> deleteProduct(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(new MessageDTO<>(productUseCase.deleteProduct(id)));
    }
}
