package yu.sentinel.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yu.api.StockFeignApi;

@RestController
public class BaseOrderController {

	@Autowired
	private StockFeignApi stockFeignApi;

	@GetMapping("/feign/product/timeout")
	public String testWithTimeOut(@RequestParam String productId) {

		String stock = stockFeignApi.getStockWithTimeOut(productId);

		return stock;
	}
}
