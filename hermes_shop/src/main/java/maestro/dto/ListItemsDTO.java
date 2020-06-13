package maestro.dto;

import java.util.ArrayList;
import java.util.List;

public class ListItemsDTO {

    private List<CartItemDTO> cartItemDTOList = new ArrayList<>();
    private double totalPrice = 0;

    public ListItemsDTO() {
    }

    public ListItemsDTO(List<CartItemDTO> cartItemDTOList, double totalPrice) {
        this.cartItemDTOList = cartItemDTOList;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
