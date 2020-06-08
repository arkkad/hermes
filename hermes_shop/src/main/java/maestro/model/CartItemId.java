package maestro.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemId implements Serializable {

    private long cartItemId;
    private long productId;

    public CartItemId() {
    }

    public CartItemId(long cartId, long productId) {
        this.cartItemId = cartId;
        this.productId = productId;
    }

    public long getCart() {
        return cartItemId;
    }

    public void setCart(long orderId) {
        this.cartItemId = orderId;
    }

    public long getProduct() {
        return productId;
    }

    public void setProduct(long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemId that = (CartItemId) o;
        return cartItemId == that.cartItemId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemId, productId);
    }
}