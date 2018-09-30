package net.knightech.shopper.data;

import net.knightech.shopper.domain.ItemPromotion;
import net.knightech.shopper.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemPromotionRepositoryTest {

    @Autowired
    private ItemPromotionRepository itemPromotionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll() {

        Product aaa_batteries = entityManager.persistFlushFind(new Product("AAA Batteries", Product.Types.POWER, 0.85D));

        ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2", aaa_batteries);
        entityManager.persistFlushFind(three42aaaBatteries);

        List<ItemPromotion> itemPromotionList = itemPromotionRepository.findAll();
        Assertions.assertThat(itemPromotionList.size()).isEqualTo(1);

    }
}