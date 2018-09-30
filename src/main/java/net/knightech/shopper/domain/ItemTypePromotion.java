package net.knightech.shopper.domain;

import net.knightech.shopper.domain.Product.Types;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemTypePromotion {

    @Id
    @GeneratedValue
    private Long id;

    private String discount;
    private String description;
    private Types type;


    public ItemTypePromotion() {
    }

    public ItemTypePromotion(String discount, String description, Types type) {

        this.discount = discount;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
