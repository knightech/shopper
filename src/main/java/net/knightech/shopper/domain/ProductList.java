package net.knightech.shopper.domain;

import java.util.List;

public class ProductList {

    private List<Product> productList;

    public ProductList() {
    }

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
