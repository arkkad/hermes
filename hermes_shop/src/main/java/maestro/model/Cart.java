package maestro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(0);

    @Column(name = "witDelivery", nullable = false)
    private boolean withDelivery = false;

    @Transient
    private double itemsCost;

    public Cart() {
        this(null);
    }

    public Cart(User uc) {
        this.user = uc;
        itemsCost = calculateItemsCost();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public CartItem update(Product product, int newQuantity) {
        if (product == null)
            return null;
        CartItem updateItem = null;
        if (newQuantity > 0) {
            CartItem existingItem = findItem(product.getId());
            if (existingItem == null) {
                CartItem newItem = new CartItem(this, product, newQuantity);
                cartItems.add(newItem);
                updateItem = newItem;
            } else {
                existingItem.setQuantity(newQuantity);
                updateItem = existingItem;
            }
        } else {
            removeItem(product.getName());
        }
        itemsCost = calculateItemsCost();
        return updateItem;
    }

    public void removeItem(String productName) {
        cartItems.removeIf(item -> item.getProduct().getName().equals(productName));
    }

    private CartItem findItem(Long productId) {
        for (CartItem existingItem : cartItems) {
            if (existingItem.getProduct().getId().equals(productId))
                return existingItem;
        }
        return null;
    }

    private double calculateItemsCost() {
        return cartItems.stream()
                .mapToDouble(CartItem::calculateCost)
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

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public void setCartItems(List<CartItem> cartItems) {
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
        Cart shoppingCart = (Cart) o;
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
        private List<CartItem> cartItems = new ArrayList<>(0);
        private boolean withDelivery = false;
        private double itemsCost;


        public Builder() {
        }

        public Cart build() {
            Cart shoppingCart = new Cart();
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

        public Builder setCartItems(List<CartItem> cartItems) {
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
