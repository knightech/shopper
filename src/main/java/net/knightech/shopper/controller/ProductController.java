package net.knightech.shopper.controller;

import net.knightech.shopper.domain.ProductList;
import net.knightech.shopper.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ProductList getProducts(){
        return new ProductList(productService.getProducts());
    }


}
