package net.knightech.shopper.data;

import net.knightech.shopper.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void findAll() {

        List<Product> productListArranged = getProductList();
        productListArranged.forEach(entityManager::persistFlushFind);

        List<Product> productList = productRepository.findAll();

        assertThat(productList.size()).isEqualTo(4);
    }

    @Test
    public void findByItemName() {

        List<Product> productListArranged = getProductList();
        productListArranged.forEach(entityManager::persistFlushFind);

        Product headphones = productRepository.findByItemName("Headphones");
        assertThat(headphones.getItemName()).isEqualTo("Headphones");
    }

    private List<Product> getProductList(){

        Product headphones = new Product("Headphones", Product.Types.AUDIO, 150.00D);
        Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
        Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
        Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

        return Arrays.asList(headphones, speakers, aaaBatteries, proteinBarsBox);
    }
}