package maestro.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct {

    @EmbeddedId
    private OrderedProductId pk = new OrderedProductId();

    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;


    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;


    public OrderedProduct() {
    }

    public OrderedProductId getPk() {
        return pk;
    }

    public void setPk(OrderedProductId pk) {
        this.pk = pk;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
