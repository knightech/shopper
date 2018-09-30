package net.knightech.shopper.controller;

import net.knightech.shopper.domain.LineItemList;
import net.knightech.shopper.domain.ShoppingCart;
import net.knightech.shopper.domain.ShoppingCartList;
import net.knightech.shopper.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/carts")
    public ShoppingCartList getShoppingCartList(){
        return new ShoppingCartList(shoppingCartService.getCarts());
    }

    @GetMapping("/carts/{shoppingCartId}")
    public ShoppingCart getShoppingCart(@PathVariable long shoppingCartId){
        return shoppingCartService.getCart(shoppingCartId);
    }

    @GetMapping("/carts/{shoppingCartId}/checkout")
    public LineItemList checkout(@PathVariable long shoppingCartId){
        return new LineItemList(shoppingCartService.checkOut(shoppingCartId));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void cartNotFoundHandler(CartNotFoundException ex){}
}
