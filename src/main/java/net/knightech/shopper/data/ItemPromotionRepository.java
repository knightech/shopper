package net.knightech.shopper.data;

import net.knightech.shopper.domain.ItemPromotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPromotionRepository extends CrudRepository<ItemPromotion, Long> {

    List<ItemPromotion> findAll();
}
