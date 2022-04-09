package basePackage.service;

import basePackage.entity.Info;
import basePackage.entity.Product;
import basePackage.payload.ProductDto;
import basePackage.payload.Status;
import basePackage.repository.BrandRepository;
import basePackage.repository.CategoryRepository;
import basePackage.repository.InfoRepository;
import basePackage.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public record ProductService (ProductRepository repository,
                              BrandRepository brandRepository,
                              CategoryRepository categoryRepository,
                              InfoRepository infoRepository)
        implements BaseService<ProductDto, Product> {
    @Override
    public Status add(ProductDto dto) {

        if(!categoryRepository.existsById(dto.getCategoryId()))
            return Status.builder()
                    .active(false)
                    .message("there is no any category with this id")
                    .build();

        if(!brandRepository.existsById(dto.getBrandId()))
            return Status.builder()
                    .active(false)
                    .message("there is no any brand with this id")
                    .build();
        Set<Info> infos = saveInformation(dto.getInfos());

        repository.save(
                new Product(
                        dto.getName(),
                        categoryRepository.getById(dto.getCategoryId()),
                        brandRepository.getById(dto.getBrandId()),
                        infos
                )
        );
        return Status.builder()
                .message("product successfully added")
                .build();
    }

    private Set<Info> saveInformation(List<Map<String, String>> infos) {
        Set<Info> info = new HashSet<>();
        for (Map<String, String> inf : infos) {
            inf.forEach((k, v) -> info.add(
                    infoRepository.save(
                            new Info(k, v)
                    )
            ));
        }
        return info;
    }

    @Override
    public Status update(Long id, ProductDto dto) {
        if (!repository.existsById(id))
            return Status.builder()
                    .message("there is no any product with this id")
                    .active(false)
                    .build();

        if(!categoryRepository.existsById(dto.getCategoryId()))
            return Status.builder()
                    .active(false)
                    .message("there is no any category with this id")
                    .build();

        if(!brandRepository.existsById(dto.getBrandId()))
            return Status.builder()
                    .active(false)
                    .message("there is no any brand with this id")
                    .build();
        repository.deleteAllInfosByProductId(id);
        Set<Info> infos = saveInformation(dto.getInfos());

        repository.save(
                new Product(
                        id,
                        dto.getName(),
                        categoryRepository.getById(dto.getCategoryId()),
                        brandRepository.getById(dto.getBrandId()),
                        infos
                )
        );
        return Status.builder()
                .message("product successfully edited")
                .build();
    }

    @Override
    public Status delete(Long id) {
        if (!repository.existsById(id))
            return Status.builder()
                    .message("there is no any product with this id")
                    .active(false)
                    .build();
        repository.deleteAllInfosByProductId(id);
        repository.deleteById(id);
        return Status.builder()
                .message("product successfully edited")
                .build();
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product get(Long id) {
        return repository.findById(id).orElse(null);
    }

}
