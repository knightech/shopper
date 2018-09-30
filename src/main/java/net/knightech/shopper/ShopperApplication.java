package net.knightech.shopper;

import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.data.ProductRepository;
import net.knightech.shopper.data.ShoppingCartRepository;
import net.knightech.shopper.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ShopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopperApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "runner", havingValue = "prod")
	public CommandLineRunner demoData(ProductRepository productRepository,
									  ItemPromotionRepository itemPromotionRepository,
									  ItemTypePromotionRepository itemTypePromotionRepository,
									  ShoppingCartRepository shoppingCartRepository) {
		return args -> {

			// setup products
			productRepository.saveAll(getProductList());

			// setup promotions
			addPromotions(productRepository, itemPromotionRepository, itemTypePromotionRepository);

			// setup shopping cart
			shoppingCartRepository.save(new ShoppingCart(buildLineItemList(productRepository)));

		};
	}

	/**
	 * Add Promotions
	 *
	 * @param productRepository product data repo
	 * @param itemPromotionRepository item promo data repo
	 * @param itemTypePromotionRepository item type promo data repo
	 */
	private void addPromotions(ProductRepository productRepository,
							   ItemPromotionRepository itemPromotionRepository,
							   ItemTypePromotionRepository itemTypePromotionRepository) {

		ItemTypePromotion allAution30PercentOff = new ItemTypePromotion("thirtyPercentOff", "30% discount on price", Product.Types.AUDIO);
		itemTypePromotionRepository.save(allAution30PercentOff);

		ItemPromotion three42aaaBatteries = new ItemPromotion("three4two", "3 for the price of 2", productRepository.findByItemName("AAA Batteries"));
		itemPromotionRepository.save(three42aaaBatteries);
	}

	/**
	 * Build line item list
	 *
	 * @param productRepository product data repo
	 * @return list of line items
	 */
	private List<LineItem> buildLineItemList(ProductRepository productRepository) {

		List<LineItem> lineItemListArranged = new ArrayList<>();

		Product speakers = productRepository.findByItemName("Speakers");
		lineItemListArranged.add(new LineItem(speakers, 1));

		Product aaaBatteries = productRepository.findByItemName("AAA Batteries");
		lineItemListArranged.add(new LineItem(aaaBatteries, 5));

		Product proteinBarsBox = productRepository.findByItemName("Protein Bars (Box)");
		lineItemListArranged.add(new LineItem(proteinBarsBox, 2));

		return lineItemListArranged;
	}

	/**
	 * Get product list
	 *
	 * @return product list
	 */
	private List<Product> getProductList(){

		Product headphones = new Product("Headphones", Product.Types.AUDIO, 150.00D);
		Product speakers = new Product("Speakers", Product.Types.AUDIO, 85.00D);
		Product aaaBatteries = new Product("AAA Batteries", Product.Types.POWER, 0.85D);
		Product proteinBarsBox = new Product("Protein Bars (Box)", Product.Types.FOOD, 25.00D);

		return Arrays.asList(headphones, speakers, aaaBatteries, proteinBarsBox);
	}
}
