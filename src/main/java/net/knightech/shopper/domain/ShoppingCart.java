package net.knightech.shopper.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
    private List<LineItem> lineItemList = new ArrayList();

    public ShoppingCart() {
    }

    public ShoppingCart(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public Long getId() {
        return id;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }
}
