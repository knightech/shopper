package net.knightech.shopper.service;

import net.knightech.shopper.data.ItemPromotionRepository;
import net.knightech.shopper.data.ItemTypePromotionRepository;
import net.knightech.shopper.domain.ItemPromotion;
import net.knightech.shopper.domain.ItemTypePromotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private ItemPromotionRepository itemPromotionRepository;

    private ItemTypePromotionRepository itemTypePromotionRepository;

    public PromotionService(ItemPromotionRepository itemPromotionRepository, ItemTypePromotionRepository itemTypePromotionRepository) {
        this.itemPromotionRepository = itemPromotionRepository;
        this.itemTypePromotionRepository = itemTypePromotionRepository;
    }

    public List<ItemPromotion> getItemPromotions(){
        return itemPromotionRepository.findAll();
    }

    public List<ItemTypePromotion> getItemTypePromotions(){ return itemTypePromotionRepository.findAll();}

}
