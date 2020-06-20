package maestro.model;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "executed")
    private boolean isExecuted;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(name = "with_delivery")
    private boolean withDelivery;

    @Column(name = "delivery_cost")
    private double deliveryCost;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = OrderedProduct.class, mappedBy = "order")
    private Set<OrderedProduct> orderedProductList = new HashSet<>(0);

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isWithDelivery() {
        return withDelivery;
    }

    public void setWithDelivery(boolean withDelivery) {
        this.withDelivery = withDelivery;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Set<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(Set<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, isExecuted, totalCost, withDelivery, deliveryCost, orderedProductList, bill, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(isExecuted, order.isExecuted) &&
                Objects.equals(totalCost, order.totalCost) &&
                Objects.equals(withDelivery, order.withDelivery) &&
                Objects.equals(deliveryCost, order.deliveryCost) &&
                Objects.equals(orderedProductList, order.orderedProductList) &&
                Objects.equals(bill, order.bill) &&
                Objects.equals(user, order.user);
    }

    public static class Builder {
        private Long id;
        private Date orderDate;
        private boolean isExecuted;
        private double totalCost;
        private double deliveryCost;
        private Set<OrderedProduct> orderedProducts;
        private Bill bill;
        private User user;

        public Builder() {
        }

        public Order build() {
            Order order = new Order();
            order.id = id;
            order.orderDate = orderDate;
            order.isExecuted = isExecuted;
            order.totalCost = totalCost;
            order.deliveryCost = deliveryCost;
            order.orderedProductList = orderedProducts;
            order.bill = bill;
            order.user = user;
            return order;
        }

        public Builder withDate(Date date){
            this.orderDate = date;
            return this;
        }

        public Builder withExecution(boolean ex){
            this.isExecuted = ex;
            return this;
        }

        public Builder withTotalCost(double totalCost){
            this.totalCost = totalCost;
            return this;
        }

        public Builder withDeliveryCost(double cost){
            this.deliveryCost = cost;
            return this;
        }

        public Builder withOrderedProducts(Set<OrderedProduct> op){
            this.orderedProducts = op;
            return this;
        }

        public Builder withBill(Bill bill){
            this.bill = bill;
            return this;
        }
        public Builder withUser(User user){
            this.user = user;
            return this;
        }


    }


}
