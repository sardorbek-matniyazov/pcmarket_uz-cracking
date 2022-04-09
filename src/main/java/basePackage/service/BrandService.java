package basePackage.service;

import basePackage.entity.Brand;
import basePackage.payload.BrandDto;
import basePackage.payload.Status;
import basePackage.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BrandService (BrandRepository repository) implements BaseService<BrandDto, Brand>{

    @Override
    public List<Brand> getAll() {
        return repository.findAll();
    }

    @Override
    public Brand get(Long id) {
        return repository.findById(id).orElse(new Brand());
    }

    @Override
    public Status add(BrandDto brandDto) {
        if (repository.existsByName(brandDto.getName()))
            return Status.builder()
                    .active(false)
                    .message("Brand already exists")
                    .build();
        repository.save(
                new Brand(
                        brandDto.getName(),
                        brandDto.getDescription()
                )
        );
        return Status.builder()
                .active(true)
                .message("Brand added successfully")
                .build();
    }

    @Override
    public Status update(Long id, BrandDto brandDto) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("Brand does not exist")
                    .build();

        if (repository.existsByNameAndIdNot(brandDto.getName(), id))
            return Status.builder()
                    .active(false)
                    .message("Brand already exists")
                    .build();

        repository.save(
                new Brand(
                        id,
                        brandDto.getName(),
                        brandDto.getDescription()
                )
        );

        return Status.builder()
                .active(true)
                .message("Brand edited successfully")
                .build();
    }

    @Override
    public Status delete(Long id) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("there is no brand with id " + id)
                    .build();

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return Status.builder()
                    .message("Brand can't be deleted, cause it has relationships")
                    .active(false)
                    .build();
        }

        return Status.builder()
                .message("Brand deleted successfully")
                .active(true)
                .build();
    }
}
// 8 april 00:15