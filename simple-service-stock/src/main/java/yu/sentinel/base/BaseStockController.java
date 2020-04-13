package yu.sentinel.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseStockController {

	@GetMapping("/product/stock/timeOut/{productId}")
	public String getStockWithTimeOut(@PathVariable("productId") String productId) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
