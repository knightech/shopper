package net.knightech.shopper.controller;

import net.knightech.shopper.domain.LineItem;
import net.knightech.shopper.domain.Product;
import net.knightech.shopper.domain.ShoppingCart;
import net.knightech.shopper.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    public void getShoppingCartList() throws Exception {

        given(shoppingCartService.getCarts()).willReturn(Collections.singletonList(new ShoppingCart(buildLineItemList())));

        mockMvc.perform(MockMvcRequestBuilders.get("/carts"))
                .andExpect(status().isOk());
    }

    @Test
    public void getShoppingCart() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart(buildLineItemList());
        given(shoppingCartService.getCart(anyLong())).willReturn(shoppingCart);

        mockMvc.perform(MockMvcRequestBuilders.get("/carts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkout() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart(buildLineItemList());
        given(shoppingCartService.checkOut(anyLong())).willReturn(shoppingCart.getLineItemList());

        mockMvc.perform(MockMvcRequestBuilders.get("/carts/1/checkout"))
                .andExpect(status().isOk());

    }

    private List<LineItem> buildLineItemList() {

        Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
        Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

        List<LineItem> lineItemListArranged = new ArrayList<>();

        lineItemListArranged.add(new LineItem(speakers, 1));
        lineItemListArranged.add(new LineItem(aaaBatteries, 5));
        lineItemListArranged.add(new LineItem(proteinBarsBox, 2));

        return lineItemListArranged;
    }
}