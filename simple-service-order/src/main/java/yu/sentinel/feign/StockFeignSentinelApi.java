package yu.sentinel.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock", fallback = FeignSentinelFallback.class)
public interface StockFeignSentinelApi {

	@GetMapping("/product/stock/sentinel/{productId}")
	String getStockWithSentinel(@PathVariable("productId") String productId);

}
