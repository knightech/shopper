package net.knightech.shopper.data;

import net.knightech.shopper.domain.LineItem;
import net.knightech.shopper.domain.Product;
import net.knightech.shopper.domain.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll() {

        Product headphones = entityManager.persistFlushFind(new Product("Headphones", Product.Types.AUDIO, 150.00D));

        List<LineItem> lineItemListArranged = new ArrayList<>();

        lineItemListArranged.add(new LineItem(headphones, 1));

        ShoppingCart shoppingCart = new ShoppingCart(lineItemListArranged);
        entityManager.persistFlushFind(shoppingCart);

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();

        assertThat(shoppingCartList.size()).isEqualTo(1);
    }



}