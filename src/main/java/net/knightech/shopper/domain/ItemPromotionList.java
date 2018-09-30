package net.knightech.shopper.domain;

import java.util.List;

public class ItemPromotionList {

    private List<ItemPromotion> itemPromotionList;

    public ItemPromotionList() {
    }

    public ItemPromotionList(List<ItemPromotion> itemPromotionList) {
        this.itemPromotionList = itemPromotionList;
    }

    public List<ItemPromotion> getItemPromotionList() {
        return itemPromotionList;
    }

    public void setItemPromotionList(List<ItemPromotion> itemPromotionList) {
        this.itemPromotionList = itemPromotionList;
    }
}
