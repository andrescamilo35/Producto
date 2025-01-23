package co.com.seti.usecase.product;

import co.com.seti.model.product.Product;
import co.com.seti.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductUseCaseTest {
    private ProductRepository productRepository;
    private ProductUseCase productUseCase;
    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productUseCase = new ProductUseCase(productRepository);
    }
    @Test
    public void saveTest(){
        Product product = Product.builder().build();
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productUseCase.save(product);
        Assertions.assertEquals(product, savedProduct);
    }
    @Test
    public void findByIdWhenProductDontExistsTest(){
        when(productRepository.existById(1L)).thenReturn(false);
        Assertions.assertThrows(Exception.class, () -> productUseCase.findById(1L));
    }
    @Test
    public void findByIdWhenProductExistsTest() throws Exception{
        when(productRepository.existById(1L)).thenReturn(true);
        when(productRepository.findById(1L)).thenReturn(Product.builder().name("coca-cola").build());
        Product product = productUseCase.findById(1L);
        Assertions.assertEquals("coca-cola", product.getName());
    }
    @Test
    public void findAllTest(){
        List<Product> products = List.of(Product.builder().name("coca-cola").build());
        when(productRepository.findAll()).thenReturn(products);
        List<Product> allProducts = productUseCase.findAll();
        Assertions.assertEquals(products, allProducts);
    }
    @Test
    public void updateProductWhenProductExistsTest() throws Exception{
        Product product = Product.builder()
                .name("coca-cola")
                .productId(1L)
                .price(4000.00)
                .build();
        when(productRepository.findById(1L)).thenReturn(product);
        when(productRepository.existById(1L)).thenReturn(true);
        when(productRepository.update(product)).thenReturn(product);
        Product resultProduct = productUseCase.updateProduct(product);
        Assertions.assertEquals(product, resultProduct);
    }
    @Test
    public void updateProductWhenProductDontExistsTest() throws Exception{
        Product product = Product.builder()
                .name("coca-cola")
                .productId(1L)
                .price(4000.00)
                .build();
        when(productRepository.existById(1L)).thenReturn(false);
        Assertions.assertThrows(Exception.class, () -> productUseCase.updateProduct(product));
    }
    @Test
    public void deleteProductWhenProductExistsTest() throws Exception{
        when(productRepository.existById(1L)).thenReturn(true);
        when(productRepository.findById(1L)).thenReturn(Product.builder().name("coca-cola").build());
        String productId = productUseCase.deleteProduct(1L);
        Assertions.assertEquals("El producto con el id " + 1L +" fue eliminado correctamente.", productId);
    }
    @Test
    public void deleteProductWhenProductDontExistsTest(){
        when(productRepository.existById(1L)).thenReturn(false);
        Assertions.assertThrows(Exception.class, () -> productUseCase.deleteProduct(1L));

    }
}