package net.knightech.shopper.service;

import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.data.ShoppingCartRepository;
import net.knightech.shopper.domain.LineItem;
import net.knightech.shopper.domain.Product;
import net.knightech.shopper.domain.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {


    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ItemPromotionRepository itemPromotionRepository;

    @Mock
    private ItemTypePromotionRepository itemTypePromotionRepository;

    private ShoppingCartService shoppingCartService;

    @Before
    public void setup(){
        shoppingCartService = new ShoppingCartService(shoppingCartRepository, itemPromotionRepository, itemTypePromotionRepository);
    }

    @Test
    public void getCart() {

        ShoppingCart cart = new ShoppingCart();
        given(shoppingCartRepository.findById(anyLong())).willReturn(Optional.of(cart));

        ShoppingCart shoppingCart = shoppingCartService.getCart(1);

        assertThat(shoppingCart).isNotNull();
    }

    @Test
    public void getCarts() {

        ShoppingCart cart = new ShoppingCart();
        given(shoppingCartRepository.findAll()).willReturn(Collections.singletonList(cart));
        List<ShoppingCart> shoppingCartList = shoppingCartService.getCarts();
        assertThat(shoppingCartList.size()).isEqualTo(1);
    }

    @Test
    public void checkOut() {

        ShoppingCart cart = new ShoppingCart(buildLineItemList());
        given(shoppingCartRepository.findById(anyLong())).willReturn(Optional.of(cart));

        List<LineItem> lineItems = shoppingCartService.checkOut(1L);
        assertThat(lineItems.size()).isEqualTo(3);
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