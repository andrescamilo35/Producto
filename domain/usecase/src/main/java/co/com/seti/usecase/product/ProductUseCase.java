package co.com.seti.usecase.product;

import co.com.seti.model.product.Product;
import co.com.seti.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        product.setIsActive(true);
        return this.productRepository.save(product);
    }

    public Product findById(Long id) throws Exception {
        boolean existingProduct = this.productRepository.existById(id);

        if (!existingProduct) {
            throw new Exception("El producto con el id " + id + " no existe.");
        }
        return this.productRepository.findById(id);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(Product productUpdate) throws Exception {
        boolean existingProduct = this.productRepository.existById(productUpdate.getProductId());

        if (!existingProduct) {
            throw new Exception("El producto con el id " + productUpdate.getProductId() + " no existe.");
        }

        Product product = this.productRepository.findById(productUpdate.getProductId());
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());

        return this.productRepository.update(product);
    }

    public String deleteProduct(Long id) throws Exception {
        boolean existingProduct = this.productRepository.existById(id);

        if (!existingProduct) {
            throw new Exception("El producto con el id " + id + " no existe");
        }
        Product product = this.productRepository.findById(id);
        product.setIsActive(false);
        this.productRepository.update(product);
        return "El producto con el id " + id +" fue eliminado correctamente.";
    }
}
