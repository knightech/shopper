package net.knightech.shopper.controller;

import net.knightech.shopper.domain.ItemPromotionList;
import net.knightech.shopper.domain.ItemTypePromotionList;
import net.knightech.shopper.service.PromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionController {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/promotions/types")
    public ItemTypePromotionList getItemTypePromotions(){
        return new ItemTypePromotionList(promotionService.getItemTypePromotions());
    }

    @GetMapping("/promotions/items")
    public ItemPromotionList getItemPromotions(){
        return new ItemPromotionList(promotionService.getItemPromotions());
    }
}
