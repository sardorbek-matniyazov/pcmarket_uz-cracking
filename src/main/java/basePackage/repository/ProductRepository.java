package basePackage.repository;

import basePackage.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_infos where product_id=?1", nativeQuery = true)
    void deleteAllInfosByProductId(Long id);
}
