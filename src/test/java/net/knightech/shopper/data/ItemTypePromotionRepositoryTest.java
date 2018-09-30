package net.knightech.shopper.data;

import net.knightech.shopper.domain.ItemTypePromotion;
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
public class ItemTypePromotionRepositoryTest {

    @Autowired
    private ItemTypePromotionRepository itemTypePromotionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll() {

        ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
        entityManager.persistFlushFind(allAution30PercentOff);

        List<ItemTypePromotion> itemPromotionList = itemTypePromotionRepository.findAll();
        Assertions.assertThat(itemPromotionList.size()).isEqualTo(1);

    }
}