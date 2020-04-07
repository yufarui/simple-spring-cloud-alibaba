package yu.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yu.mapper.OrderMapper;
import yu.model.Order;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private StoreFeignApi storeFeignApi;

	@GlobalTransactional(name = "prex-create-order",rollbackFor = Exception.class)
	public void createOrder(Order order) {
		orderMapper.insert(order);
		storeFeignApi.reduceStore(order);
		System.out.println(1 / 0);
	}
}
