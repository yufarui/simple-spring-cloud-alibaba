package yu.sentinel.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SentinelRibbonController {

	@Autowired
	private RestTemplate sentinelRestTemplate;

	@GetMapping("/sentinel/ribbon/product")
	public Map<String, String> testWithFeign(@RequestParam String productId) {

		ResponseEntity<String> responseEntity = sentinelRestTemplate.getForEntity("http://stock/product/stock/" + productId, String.class);

		Map<String, String> map = new HashMap<>();
		map.put("stock", responseEntity.getBody());
		return map;
	}
}
