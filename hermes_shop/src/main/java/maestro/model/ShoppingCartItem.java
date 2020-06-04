package maestro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem implements Serializable {
    private static final long serialVersionUID = -3995571478236070123L;

    @EmbeddedId
    private ShoppingCartItemId pk = new ShoppingCartItemId();

    @MapsId("shopping_cart_id")
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShoppingCart shopping_cart;

    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(ShoppingCart shoppingCart, Product product, int quantity) {
        this.pk = new ShoppingCartItemId(shoppingCart.getId(), product.getId());
        this.shopping_cart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateCost() {
        return quantity * product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getOrder() {
        return shopping_cart;
    }

    public void setOrder(ShoppingCart cart) {
        this.shopping_cart = cart;
        pk.setCart(cart.getId());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        pk.setProduct(product.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShoppingCartItem that = (ShoppingCartItem) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return (pk != null ? pk.hashCode() : 0);
    }
}