package maestro.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "product_price", nullable = false)
    private double price;

    @Column(name = "storage_count", nullable = false)
    private int storageCount;

    @Column(name = "product_filename")
    private String filename;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> categories = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, storageCount, filename, categories);
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
                Objects.equals(filename, product.filename) &&
                Objects.equals(categories, product.categories);
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
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

    public String getFilename() {
        return filename;
    }

    public Set<String> getCategoriesSet() {
        return categories;
    }

    public void setCategoriesSet(Set<String> categoriesSet) {
        this.categories = categoriesSet;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public boolean isAvailable() {
        return this.getStorageCount() > 0;
    }

    public static class Builder {
        private Product product;

        public Builder() {
            product = new Product();
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

        public Builder withFilename(String filename) {
            product.filename = filename;
            return this;
        }

        public Builder withCategories(Set<String> categories) {
            product.categories = categories;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}