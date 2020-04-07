package yu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yu.model.Order;
import yu.service.StoreService;

@RestController
public class StoreController {

	@Autowired
	private StoreService storeService;

	@PostMapping("/seata/store")
	public void reduceStore(@RequestBody Order order) {
		storeService.update(order);
	}
}
