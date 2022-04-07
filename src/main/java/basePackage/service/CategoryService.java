package basePackage.service;

import basePackage.entity.Category;
import basePackage.payload.CategoryDto;
import basePackage.payload.Status;
import basePackage.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public record CategoryService (CategoryRepository repository)
        implements BaseService<CategoryDto, Category> {

    @Override
    public Status add(CategoryDto categoryDto) {
        if (repository.existsByName(categoryDto.getName()))
            return Status.builder()
                    .active(false)
                    .message("Category already exists")
                    .build();
        if (categoryDto.getParentCategoryId() != null) {
            if (!repository.existsById(categoryDto.getParentCategoryId())){
                return Status.builder()
                        .active(false)
                        .message("there is no parent category with id " + categoryDto.getParentCategoryId())
                        .build();
            }else {
                repository.save(
                        new Category(
                                categoryDto.getName(),
                                repository.getById(
                                        categoryDto.getParentCategoryId()
                                )
                        )
                );
                return Status.builder()
                        .message("Category added successfully")
                        .active(true)
                        .build();
                }
            }

        repository.save(new Category(categoryDto.getName()));

        return Status.builder()
                .message("Category added successfully")
                .active(true)
                .build();
    }

    @Override
    public Status update(Long id, CategoryDto categoryDto) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("there is no category with id " + categoryDto.getParentCategoryId())
                    .build();

        if (repository.existsByNameAndIdNot(categoryDto.getName(), id))
            return Status.builder()
                    .active(false)
                    .message("Category already exists")
                    .build();

        if (categoryDto.getParentCategoryId() != null) {
            if (!repository.existsById(categoryDto.getParentCategoryId())){
                return Status.builder()
                        .active(false)
                        .message("there is no parent category with id " + categoryDto.getParentCategoryId())
                        .build();
            }else {
                repository.save(
                        new Category(
                                id,
                                categoryDto.getName(),
                                repository.getById(
                                        categoryDto.getParentCategoryId()
                                )
                        )
                );
                return Status.builder()
                        .message("Category added successfully")
                        .active(true)
                        .build();
            }
        }

        repository.save(new Category(id, categoryDto.getName()));

        return Status.builder()
                .message("Category edited successfully")
                .active(true)
                .build();
    }

    @Override
    public Status delete(Long id) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("there is no category with id " + id)
                    .build();

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return Status.builder()
                    .message("Category can't be deleted, cause it has subcategories")
                    .active(false)
                    .build();
        }
        return Status.builder()
                .message("Category deleted successfully")
                .active(true)
                .build();
    }
}
// 7 april 23:57