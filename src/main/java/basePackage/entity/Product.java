package basePackage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends ParentEntity{
    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Brand brand;

    @Column(nullable = false)
    private double price;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Info> infos;

    public Product(String name, Category byId, Brand byId1, Set<Info> infos) {
        super(name);
        this.category = byId;
        this.brand = byId1;
        this.infos = infos;
    }

    public Product(Long id, String name, Category byId, Brand byId1, Set<Info> infos) {
        super(id, name);
        this.category = byId;
        this.brand = byId1;
        this.infos = infos;
    }
}
