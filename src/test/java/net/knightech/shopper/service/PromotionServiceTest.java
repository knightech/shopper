package net.knightech.shopper.service;

import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.domain.ItemPromotion;
import net.knightech.shopper.domain.ItemTypePromotion;
import net.knightech.shopper.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PromotionServiceTest {

    @Mock
    private ItemPromotionRepository itemPromotionRepository;

    @Mock
    private ItemTypePromotionRepository itemTypePromotionRepository;

    private PromotionService promotionService;

    @Before
    public void setup(){
        promotionService = new PromotionService(itemPromotionRepository, itemTypePromotionRepository);
    }

    @Test
    public void getItemPromotions() {

        Product aaa_batteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2", aaa_batteries);
        given(itemPromotionRepository.findAll()).willReturn(Collections.singletonList(three42aaaBatteries));

        List<ItemPromotion> itemPromotionList = promotionService.getItemPromotions();
        assertThat(itemPromotionList.size()).isEqualTo(1);
    }

    @Test
    public void getItemTypePromotions() {

        ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
        given(itemTypePromotionRepository.findAll()).willReturn(Collections.singletonList(allAution30PercentOff));

        List<ItemTypePromotion> itemTypePromotionList = promotionService.getItemTypePromotions();
        assertThat(itemTypePromotionList.size()).isEqualTo(1);

    }
}