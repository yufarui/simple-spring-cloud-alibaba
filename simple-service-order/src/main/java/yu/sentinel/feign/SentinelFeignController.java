package yu.sentinel.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SentinelFeignController {

	@Autowired
	private StockFeignSentinelApi stockFeignSentinelApi;

	@GetMapping("/sentinel/product")
	public Map<String, String> testWithFeign(@RequestParam String productId) {
		String stock = stockFeignSentinelApi.getStockWithSentinel(productId);
		Map<String, String> map = new HashMap<>();
		map.put("stock", stock);
		return map;
	}
}
