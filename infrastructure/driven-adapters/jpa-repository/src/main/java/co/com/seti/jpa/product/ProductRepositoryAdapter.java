package co.com.seti.jpa.product;

import co.com.seti.jpa.helper.AdapterOperations;
import co.com.seti.jpa.product.entities.ProductEntity;
import co.com.seti.model.product.Product;
import co.com.seti.model.product.gateways.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductEntity, Long, ProductDataRepository>
 implements ProductRepository
{

    public ProductRepositoryAdapter(ProductDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Product update(Product product) {
        return this.save(product);
    }

    @Override
    public boolean existById(Long id) {
        return this.repository.existsById(id);
    }
}
