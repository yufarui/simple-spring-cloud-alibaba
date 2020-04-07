package yu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yu.mapper.StoreMapper;
import yu.model.Order;
import yu.model.Store;

@Service
public class StoreService {

	@Autowired
	private StoreMapper storeMapper;

	public void update(Order order) {
		storeMapper.update(order);
	}
}
