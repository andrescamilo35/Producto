package co.com.seti.api.product.requests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateProductRequestTest {
    @Test
    public void testUpdateProductRequest() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setId(1L);
        updateProductRequest.setName("coca-cola");
        updateProductRequest.setPrice(4000.00);
        Assertions.assertEquals(1L, updateProductRequest.getId());
        Assertions.assertEquals("coca-cola", updateProductRequest.getName());
        Assertions.assertEquals(4000.00, updateProductRequest.getPrice());
    }
}