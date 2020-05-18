package maestro.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "product_price", nullable = false)
    private double price;

    @Column(name = "storage_count", nullable = false)
    private int storageCount;

    @Column(name = "product_pic")
    private String pic_path;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_categories",
            joinColumns = {@JoinColumn(name = "products_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, storageCount, pic_path, categories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(storageCount, product.storageCount) &&
                Objects.equals(pic_path, product.pic_path) &&
                Objects.equals(categories, product.categories);
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public String getPic_path() {
        return pic_path;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public static class Builder {
        private Product product;

        public Builder(Product product) {
            this.product = product;
        }

        public Builder withName(String name) {
            product.name = name;
            return this;
        }

        public Builder withDesc(String desc) {
            product.description = desc;
            return this;
        }

        public Builder withPrice(double price) {
            product.price = price;
            return this;
        }

        public Builder withStorageCount(int sc) {
            product.storageCount = sc;
            return this;
        }

        public Builder withPicPath(String picPath) {
            product.pic_path = picPath;
            return this;
        }

        public Builder withCategories(Set<Category> categories) {
            product.categories = categories;
            return this;
        }

        public Product build() {
            return product;
        }


    }
}