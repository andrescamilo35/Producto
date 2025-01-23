package co.com.seti.jpa.product;

import co.com.seti.jpa.product.entities.ProductEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductDataRepository extends CrudRepository<ProductEntity, Long>, QueryByExampleExecutor<ProductEntity> {
    @NonNull
    @Query(
            """
            select p from ProductEntity p where p.productId = :id and p.isActive = true
            """
    )
    Optional<ProductEntity> findById(@NonNull @Param("id") Long id);

    @NonNull
    @Query(
            """
            select p from ProductEntity p where p.isActive = true
            """
    )
    List<ProductEntity> findAll();
}
