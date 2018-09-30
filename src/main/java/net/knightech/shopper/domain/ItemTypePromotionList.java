package net.knightech.shopper.domain;

import java.util.List;

public class ItemTypePromotionList {

    private List<ItemTypePromotion> itemTypePromotionList;

    public ItemTypePromotionList() {
    }

    public ItemTypePromotionList(List<ItemTypePromotion> itemTypePromotionList) {
        this.itemTypePromotionList = itemTypePromotionList;
    }

    public List<ItemTypePromotion> getItemTypePromotionList() {
        return itemTypePromotionList;
    }

    public void setItemTypePromotionList(List<ItemTypePromotion> itemTypePromotionList) {
        this.itemTypePromotionList = itemTypePromotionList;
    }
}
