package basePackage.repository;

import basePackage.entity.Brand;
import basePackage.projection.BrandProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "brand", excerptProjection = BrandProjection.class)
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
