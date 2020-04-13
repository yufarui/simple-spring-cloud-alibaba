package yu.sentinel.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelStockController {

	@GetMapping("/product/stock/sentinel/{productId}")
	public String getStockWithSentinel(@PathVariable("productId") String productId) {
		return productId;
	}
}
