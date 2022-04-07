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
public class Info extends ParentEntity{
    @Column(nullable = false)
    private String value;

    public Info(String name, String value) {
        super(name);
        this.value = value;
    }
}
