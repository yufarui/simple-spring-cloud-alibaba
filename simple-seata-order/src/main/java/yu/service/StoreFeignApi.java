package yu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import yu.model.Order;

@FeignClient(name = "nacos-seata-store")
public interface StoreFeignApi {

	@PostMapping("/seata/store")
	void reduceStore(@RequestBody Order order);
}
