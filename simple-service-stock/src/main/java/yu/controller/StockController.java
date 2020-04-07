package yu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	@GetMapping("/product/stock/{productId}")
	public String getStock(@PathVariable("productId") String productId) {

		return productId;
	}
}
