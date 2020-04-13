package yu.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yu.sentinel.feign.FeignSentinelFallback;

@FeignClient(name = "stock")
public interface StockFeignApi {

	@GetMapping("/product/stock/{productId}")
	String getStock(@PathVariable("productId") String productId);

	@GetMapping("/product/stock/timeOut/{productId}")
	String getStockWithTimeOut(@PathVariable("productId") String productId);
}
