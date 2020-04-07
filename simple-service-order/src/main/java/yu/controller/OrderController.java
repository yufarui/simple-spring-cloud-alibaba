package yu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yu.api.StockFeignApi;

import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private StockFeignApi stockFeignApi;

	@GetMapping("/product")
	public void baseTest(@RequestParam String productId) {
		List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("stock");
		String targetUri = serviceInstanceList.get(0).getUri().toString();

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(targetUri + "/product/stock/" + productId, String.class);

		System.out.println(responseEntity.getBody());
	}

	@GetMapping("/ribbon/product")
	public void testWithRibbon(@RequestParam String productId) {

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://stock/product/stock/" + productId, String.class);

		System.out.println(responseEntity.getBody());
	}

	@GetMapping("/feign/product")
	public void testWithFeign(@RequestParam String productId) {
		String stock = stockFeignApi.getStock(productId);
		System.out.println(stock);
	}
}
