package yu.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yu.model.Order;
import yu.service.OrderService;
import yu.service.StoreFeignApi;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/seata/order")
	public void createOrder() {

		Order order = new Order();
		order.setProductId(1);
		order.setNum(2);

		orderService.createOrder(order);
	}
}
