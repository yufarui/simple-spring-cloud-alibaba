package yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderGatewayApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OrderGatewayApp.class, args);
	}
}
