package net.knightech.shopper.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;
    private Types type;
    private double price;

    public enum Types {AUDIO, POWER, FOOD;}

    public Product() {
    }

    public Product(String itemName, Types type, double price) {

        this.itemName = itemName;
        this.type = type;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public Types getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemName='" + itemName + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(itemName, product.itemName) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, type, price);
    }
}
