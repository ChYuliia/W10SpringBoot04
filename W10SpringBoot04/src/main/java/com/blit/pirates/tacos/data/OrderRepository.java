package com.blit.pirates.tacos.data;

//import java.util.Date;
//import java.util.List;

import org.springframework.data.repository.CrudRepository;

//import com.blit.pirates.tacos.Ingredient;
import com.blit.pirates.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

	// CRUD - create read update delete
	// save taco order with ID, delete taco order by ID,  update a taco order by ID
	// read ALL taco orders, read SPECIFIC taco order by ID
	
	@SuppressWarnings("unchecked")
	TacoOrder save(TacoOrder order);
	
	// List<TacoOrder> findByDeliveryZip(String deliveryZip);
	
	//List<TacoOrder> readOrderByDeliveryZipAndPlacedAtBetween(
	//		String deliveryZip, Date StartDate, Date endDate);
	
}
