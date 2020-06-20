package maestro.model;

import javax.persistence.*;

@Entity
@Table(name = "order_delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "delivery_phone", nullable = false)
    private String deliveryPhone;

    @Column(name = "delivery_name")
    private String deliveryName;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

    public Delivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
