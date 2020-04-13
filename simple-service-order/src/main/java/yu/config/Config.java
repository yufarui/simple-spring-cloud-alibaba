package yu.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import yu.globalHandler.GlobalExceptionHandler;

@Configuration
public class Config {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(ClientHttpRequestFactory simpleRequestFactory) {
		return new RestTemplate(simpleRequestFactory);
	}

	@Bean
	public ClientHttpRequestFactory simpleRequestFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(10000);
		requestFactory.setConnectTimeout(10000);
		return requestFactory;
	}

	@Bean
	@LoadBalanced
	@SentinelRestTemplate(
		blockHandler = "handleException", blockHandlerClass = GlobalExceptionHandler.class,
		fallback = "fallback", fallbackClass = GlobalExceptionHandler.class
	)
	public RestTemplate sentinelRestTemplate(ClientHttpRequestFactory simpleRequestFactory) {
		return new RestTemplate(simpleRequestFactory);
	}

}
