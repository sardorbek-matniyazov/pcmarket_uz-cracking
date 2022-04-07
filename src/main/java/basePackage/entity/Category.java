package basePackage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends ParentEntity{
    @ManyToOne
    private Category parentCategory;

    public Category(String name, Category parentCategory) {
        super(name);
        this.parentCategory = parentCategory;
    }

    public Category(String name) {
        super(name);
    }

    public Category(Long id, String name, Category byId) {
        super(id, name);
        parentCategory = byId;
    }

    public Category(Long id, String name) {
        super(id, name);
    }
}
