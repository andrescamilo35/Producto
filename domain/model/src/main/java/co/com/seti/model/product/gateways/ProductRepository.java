package co.com.seti.model.product.gateways;

import co.com.seti.model.product.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    Product update(Product product);

    boolean existById(Long id);
}
