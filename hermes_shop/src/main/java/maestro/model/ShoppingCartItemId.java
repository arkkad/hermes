package maestro.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShoppingCartItemId implements Serializable {

    private long shoppingCartItemId;
    private long productId;

    public ShoppingCartItemId() {
    }

    public ShoppingCartItemId(long cartId, long productId) {
        this.shoppingCartItemId = cartId;
        this.productId = productId;
    }

    public long getCart() {
        return shoppingCartItemId;
    }

    public void setCart(long orderId) {
        this.shoppingCartItemId = orderId;
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
        ShoppingCartItemId that = (ShoppingCartItemId) o;
        return shoppingCartItemId == that.shoppingCartItemId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartItemId, productId);
    }
}