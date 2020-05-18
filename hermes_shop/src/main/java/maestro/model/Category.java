package maestro.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> productSet = new HashSet<>();

    public String getName() {
        return name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
