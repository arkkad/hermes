package maestro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable {
    private static final long serialVersionUID = -3995571478236070123L;

    @EmbeddedId
    private CartItemId pk = new CartItemId();

    @MapsId("cartId")
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {
    }

    public CartItem(ShoppingCart shoppingCart, Product product, int quantity) {
        this.pk = new CartItemId(shoppingCart.getId(), product.getId());
        this.shoppingCart = shoppingCart;
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
        return shoppingCart;
    }

    public void setOrder(ShoppingCart cart) {
        this.shoppingCart = cart;
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
        CartItem that = (CartItem) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return (pk != null ? pk.hashCode() : 0);
    }
}