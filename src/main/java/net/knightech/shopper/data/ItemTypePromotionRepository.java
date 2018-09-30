package net.knightech.shopper.data;

import net.knightech.shopper.domain.ItemTypePromotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTypePromotionRepository extends CrudRepository<ItemTypePromotion, Long> {

    List<ItemTypePromotion> findAll();
}
