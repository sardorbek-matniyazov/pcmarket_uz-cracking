package basePackage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Brand extends ParentEntity{

    @Column(nullable = false, length = 10000)
    private String description;

    public Brand(String name, String description) {
        super(name);
        this.description = description;
    }

    public Brand(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }
}
