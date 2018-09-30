package net.knightech.shopper.service;

import net.knightech.shopper.data.ProductRepository;
import net.knightech.shopper.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setup(){
        productService = new ProductService(productRepository);
    }

    @Test
    public void test() {

        given(productRepository.findAll()).willReturn(getProductList());

        List<Product> productList = productService.getProducts();

        Assertions.assertThat(productList.size()).isEqualTo(4);

    }


    private List<Product> getProductList(){

        Product headphones = new Product("Headphones", Product.Types.AUDIO, 150.00D);
        Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
        Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

        return Arrays.asList(headphones, speakers, aaaBatteries, proteinBarsBox);
    }

}