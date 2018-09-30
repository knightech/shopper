package net.knightech.shopper.controller;

import net.knightech.shopper.domain.Product;
import net.knightech.shopper.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProducts() throws Exception {

        List<Product> productListArranged = getProductList();
        given(productService.getProducts()).willReturn(productListArranged);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk());
    }

    private List<Product> getProductList(){

        Product headphones = new Product("Headphones", Product.Types.AUDIO, 150.00D);
        Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
        Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

        return Arrays.asList(headphones, speakers, aaaBatteries, proteinBarsBox);
    }

}