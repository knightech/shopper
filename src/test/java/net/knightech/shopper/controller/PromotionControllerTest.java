package net.knightech.shopper.controller;

import net.knightech.shopper.domain.ItemPromotion;
import net.knightech.shopper.domain.ItemTypePromotion;
import net.knightech.shopper.domain.Product;
import net.knightech.shopper.service.PromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PromotionController.class)
public class PromotionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromotionService promotionService;

    @Test
    public void getItemTypePromotions() throws Exception {

        ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
        given(promotionService.getItemTypePromotions()).willReturn(Collections.singletonList(allAution30PercentOff));

        mockMvc.perform(MockMvcRequestBuilders.get("/promotions/types"))
                .andExpect(status().isOk());
    }

    @Test
    public void getItemPromotions() throws Exception {

        ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2",
                new Product("AAA Batteries", Product.Types.POWER, 0.85D));

        given(promotionService.getItemPromotions()).willReturn(Collections.singletonList(three42aaaBatteries));

        mockMvc.perform(MockMvcRequestBuilders.get("/promotions/items"))
                .andExpect(status().isOk());
    }
}