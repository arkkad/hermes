package maestro.dto;

import javax.persistence.Column;

public class DeliveryDTO {
    private String deliveryAddress;
    private String deliveryPhone;
    private String deliveryName;

    public DeliveryDTO() {
    }

    public DeliveryDTO(String deliveryAddress, String deliveryPhone, String deliveryName) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhone = deliveryPhone;
        this.deliveryName = deliveryName;
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
}
