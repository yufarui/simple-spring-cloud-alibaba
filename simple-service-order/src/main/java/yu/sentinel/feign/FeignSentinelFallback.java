package yu.sentinel.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignSentinelFallback implements StockFeignSentinelApi {

	@Override
	public String getStockWithSentinel(String productId) {
		log.info("限流");
		return "限流产品";
	}
}
