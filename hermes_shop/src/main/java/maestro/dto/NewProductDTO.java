package maestro.dto;

import java.util.HashSet;
import java.util.Set;

public class NewProductDTO {
    private String name;
    private String description;
    private double price;
    private int storageCount;
    private Set<String> categories = new HashSet<>();

    public NewProductDTO() {
    }

    public NewProductDTO(String name, String description, double price, int storageCount, String pic_path, Set<String> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.storageCount = storageCount;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
