package net.knightech.shopper;

import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.data.ProductRepository;
import net.knightech.shopper.data.ShoppingCartRepository;
import net.knightech.shopper.domain.*;
import net.knightech.shopper.service.ShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemPromotionRepository itemPromotionRepository;

    @Autowired
    private ItemTypePromotionRepository itemTypePromotionRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    public void getProducts_returnsProducts() {

        //arrange
        List<Product> productListArranged = getProductList();
        productRepository.saveAll(productListArranged);

        //act
        ResponseEntity<ProductList> productList = restTemplate.getForEntity("/products", ProductList.class);

        //assert
        assertThat(productList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productList.getBody().getProductList().size()).isEqualTo(productListArranged.size());

    }

    @Test
    public void getPromotions_returnsAllPromotions() {

        List<Product> productListArranged = getProductList();
        productRepository.saveAll(productListArranged);

        //arrange
        ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
        itemTypePromotionRepository.save(allAution30PercentOff);


        ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2", productRepository.findByItemName("AAA Batteries"));
        itemPromotionRepository.save(three42aaaBatteries);

        //act
        ResponseEntity<ItemPromotionList> itemPromotionList = restTemplate.getForEntity("/promotions/items", ItemPromotionList.class);
        ResponseEntity<ItemTypePromotionList> itemTypePromotionList = restTemplate.getForEntity("/promotions/types", ItemTypePromotionList.class);

        //assert
        assertThat(itemPromotionList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(itemPromotionList.getBody().getItemPromotionList().size()).isEqualTo(1);

        assertThat(itemTypePromotionList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(itemTypePromotionList.getBody().getItemTypePromotionList().size()).isEqualTo(1);



    }


    @Test
    public void getShoppingCart_returnsShoppingCart() {

        //arrange
        List<Product> productListArranged = getProductList();
        productRepository.saveAll(productListArranged);

        List<LineItem> lineItemListArranged = new ArrayList<>();

        Product speakers = productRepository.findByItemName("Speakers");
        lineItemListArranged.add(new LineItem(speakers, 1));

        Product aaaBatteries = productRepository.findByItemName("AAA Batteries");
        lineItemListArranged.add(new LineItem(aaaBatteries, 5));

        Product proteinBarsBox = productRepository.findByItemName("Protein Bars (Box)");
        lineItemListArranged.add(new LineItem(proteinBarsBox, 2));

        ShoppingCart shoppingCartArranged = shoppingCartRepository.save(new ShoppingCart(lineItemListArranged));

        //act
        ResponseEntity<ShoppingCart> shoppingCart = restTemplate.getForEntity("/carts/"+shoppingCartArranged.getId(), ShoppingCart.class );

        //assert
        assertThat(shoppingCart.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<LineItem> lineItemList = shoppingCart.getBody().getLineItemList();
        assertThat(lineItemList.size()).isEqualTo(3);

    }

    @Test
    public void checkOut_displaysCheckoutForShoppingCart() {

        //arrange
        List<Product> productListArranged = getProductList();
        productRepository.saveAll(productListArranged);

        List<LineItem> lineItemListArranged = new ArrayList<>();

        Product speakers = productRepository.findByItemName("Speakers");
        lineItemListArranged.add(new LineItem(speakers, 1));

        Product aaaBatteries = productRepository.findByItemName("AAA Batteries");
        lineItemListArranged.add(new LineItem(aaaBatteries, 5));

        Product proteinBarsBox = productRepository.findByItemName("Protein Bars (Box)");
        lineItemListArranged.add(new LineItem(proteinBarsBox, 2));

        ShoppingCart shoppingCartArranged = shoppingCartRepository.save(new ShoppingCart(lineItemListArranged));

        //arrange
        ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
        itemTypePromotionRepository.save(allAution30PercentOff);

        ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2", productRepository.findByItemName("AAA Batteries"));
        itemPromotionRepository.save(three42aaaBatteries);

        //act
        ResponseEntity<LineItemList> summary = restTemplate.getForEntity("/carts/"+shoppingCartArranged.getId()+ "/checkout", LineItemList.class );

        summary.getBody().getLineItemList().forEach(System.out::println);

    }

    @Before
    public void setup(){
        shoppingCartRepository.deleteAll();
        itemPromotionRepository.deleteAll();
        productRepository.deleteAll();
        itemTypePromotionRepository.deleteAll();
    }

    private List<Product> getProductList(){

        Product headphones = new Product("Headphones", Product.Types.AUDIO, 150.00D);
        Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
        Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

        return Arrays.asList(headphones, speakers, aaaBatteries, proteinBarsBox);
    }
}
