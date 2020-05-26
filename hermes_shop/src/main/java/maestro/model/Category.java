package maestro.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Collection<Product> productSet) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
