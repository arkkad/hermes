package maestro.dto;

public class CartItemDTO {
    private NewProductDTO product;
    private int quantity;
    private double total;

    public CartItemDTO(NewProductDTO product, int quantity, double total) {
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    public CartItemDTO() {
    }

    public CartItemDTO(NewProductDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public NewProductDTO getProduct() {
        return product;
    }

    public void setProduct(NewProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
