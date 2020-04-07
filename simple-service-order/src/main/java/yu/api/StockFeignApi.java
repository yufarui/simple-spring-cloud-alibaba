package yu.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock")
public interface StockFeignApi {

	@GetMapping("/product/stock/{productId}")
	String getStock(@PathVariable("productId") String productId);
}
