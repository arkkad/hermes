package maestro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = ShoppingCartItem.class, mappedBy = "shopping_cart")
    private List<ShoppingCartItem> cartItems = new ArrayList<>(0);

    @Column(name = "witDelivery", nullable = false)
    private boolean withDelivery = false;

    @Transient
    private double itemsCost;

    public ShoppingCart() {
        this(null);
    }

    public ShoppingCart(User uc) {
        this.user = uc;
        itemsCost = calculateItemsCost();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public ShoppingCartItem update(Product product, int newQuantity) {
        if (product == null)
            return null;
        ShoppingCartItem updateItem = null;
        if (newQuantity > 0) {
            ShoppingCartItem existingItem = findItem(product.getId());
            if (existingItem == null) {
                ShoppingCartItem newItem = new ShoppingCartItem(this, product, newQuantity);
                cartItems.add(newItem);
                updateItem = newItem;
            } else {
                existingItem.setQuantity(newQuantity);
                updateItem = existingItem;
            }
        } else {
            removeItem(product.getId());
        }
        itemsCost = calculateItemsCost();
        return updateItem;
    }

    private void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    private ShoppingCartItem findItem(Long productId) {
        for (ShoppingCartItem existingItem : cartItems) {
            if (existingItem.getProduct().getId() == productId)
                return existingItem;
        }
        return null;
    }

    private double calculateItemsCost() {
        return cartItems.stream()
                .mapToDouble(ShoppingCartItem::calculateCost)
                .sum();
    }

    public void clear() {
        cartItems.clear();
        itemsCost = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUserContact(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public void setCartItems(List<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
        itemsCost = calculateItemsCost();
    }

    public boolean isWithDelivery() {
        return withDelivery;
    }

    public void setWithDelivery(boolean withDelivery) {
        this.withDelivery = withDelivery;
    }

    public double getItemsCost() {
        return itemsCost;
    }

    public int getItemsCount() {
        return cartItems.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart shoppingCart = (ShoppingCart) o;
        return withDelivery == shoppingCart.withDelivery &&
                Objects.equals(id, shoppingCart.id) &&
                Objects.equals(cartItems, shoppingCart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItems, withDelivery);
    }

    public class Builder {
        private long id;
        private User user;
        private List<ShoppingCartItem> cartItems = new ArrayList<>(0);
        private boolean withDelivery = false;
        private double itemsCost;


        public Builder() {
        }

        public  Builder(ShoppingCart shoppingCart){
            id = shoppingCart.id;
            user = shoppingCart.user;
            cartItems = shoppingCart.cartItems;
            withDelivery = shoppingCart.withDelivery;
            itemsCost = shoppingCart.itemsCost;
        }

        public ShoppingCart build(){
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.id = id;
            shoppingCart.user = user;
            shoppingCart.cartItems = cartItems;
            shoppingCart.withDelivery = withDelivery;
            shoppingCart.itemsCost = itemsCost;
            return shoppingCart;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCartItems(List<ShoppingCartItem> cartItems) {
            this.cartItems = cartItems;
            return this;
        }

        public Builder setWithDelivery(boolean withDelivery) {
            this.withDelivery = withDelivery;
            return this;
        }

        public Builder setItemsCost(double itemsCost) {
            this.itemsCost = itemsCost;
            return this;
        }
    }
}
