package net.knightech.shopper.service;

import net.knightech.shopper.controller.CartNotFoundException;
import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.data.ShoppingCartRepository;
import net.knightech.shopper.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static net.knightech.shopper.service.Discounter.thirtyPercentOff;
import static net.knightech.shopper.service.Discounter.three4two;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    private ItemPromotionRepository itemPromotionRepository;

    private ItemTypePromotionRepository itemTypePromotionRepository;

    private Map<String, Discounter> discounterMap = new HashMap<>();

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               ItemPromotionRepository itemPromotionRepository,
                               ItemTypePromotionRepository itemTypePromotionRepository) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.itemPromotionRepository = itemPromotionRepository;
        this.itemTypePromotionRepository = itemTypePromotionRepository;

        discounterMap.put(Discounter.THREE_FOR_TWO, three4two());
        discounterMap.put(Discounter.THIRTY_PERCENT_OFF, thirtyPercentOff());
    }

    public ShoppingCart getCart(long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElseThrow(CartNotFoundException::new);
    }

    /**
     * Gets the line items for a cart at checkout with discounts applied
     *
     * @param shoppingCart the shopping cart
     * @return line items with discounts applied
     */
    public List<LineItem> checkOut(Long shoppingCart){

        return getCart(shoppingCart).getLineItemList()
                .stream()
                .map(this::applyDiscounts)
                .collect(toList());
    }

    /**
     * Get all shopping carts
     *
     * @return list of shopping carts
     */
    public List<ShoppingCart> getCarts() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Applies discounts for item type or item promotions
     *
     * @param lineItem the product details with item number
     * @return the product details and item number with discounts calculated
     */
    private LineItem applyDiscounts(LineItem lineItem){

        final Map<Product.Types, String> itemTypePromotionsMap = getItemTypePromotionLookupMap();
        final Map<Product, String> itemPromotionsMap = getProductPromotionLookupMap();

        Optional<String> itemTypePromotion = Optional.ofNullable(itemTypePromotionsMap.get(lineItem.getProduct().getType()));

        itemTypePromotion.ifPresent(key -> {
            Discounter discounter = discounterMap.get(key);
            BigDecimal bigDecimal = discounter.applyDiscount(BigDecimal.valueOf(lineItem.getProduct().getPrice()), lineItem.getNumber());
            lineItem.setDiscountTotal(bigDecimal.doubleValue());
        });

        Optional<String> itemPromotion = Optional.ofNullable(itemPromotionsMap.get(lineItem.getProduct()));

        itemPromotion.ifPresent(key -> {
            Discounter discounter = discounterMap.get(key);
            BigDecimal bigDecimal = discounter.applyDiscount(BigDecimal.valueOf(lineItem.getProduct().getPrice()), lineItem.getNumber());
            lineItem.setDiscountTotal(bigDecimal.doubleValue());
        });

        return lineItem;
    }


    private Map<Product, String> getProductPromotionLookupMap() {
        List<ItemPromotion> itemPromotions = itemPromotionRepository.findAll();

        return itemPromotions.stream()
                .collect(toMap(ItemPromotion::getProduct, ItemPromotion::getName));
    }

    private Map<Product.Types, String> getItemTypePromotionLookupMap() {
        List<ItemTypePromotion> itemTypePromotions = itemTypePromotionRepository.findAll();

        return itemTypePromotions.stream()
                .collect(toMap(ItemTypePromotion::getType, ItemTypePromotion::getDiscount));
    }
}
