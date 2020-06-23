package maestro.properties;

import org.springframework.beans.factory.annotation.Value;


public class PaginationProperties {
    private int countProduct;

    public PaginationProperties() {
    }

    public PaginationProperties(
            @Value("${pagination.count.product}") int backendProduct) {
        this.countProduct = backendProduct;
    }

    public int getBackendProduct() {
        return countProduct;
    }


    public static class Builder {
        private int countProduct;

        public PaginationProperties build() {
            return new PaginationProperties(countProduct);
        }

        public Builder setBackendProduct(int countProduct) {
            this.countProduct = countProduct;
            return this;
        }
    }
}