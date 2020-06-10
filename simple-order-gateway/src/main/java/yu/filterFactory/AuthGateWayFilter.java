package yu.filterFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局过滤器校验请求头中的token
 */
@Component
@Slf4j
public class AuthGateWayFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		List<String> token = exchange.getRequest().getHeaders().get("token");
		if (StringUtils.isEmpty(token)) {
			log.info("token is null");
		}

		log.info("token:{}", token);
		return chain.filter(exchange);

	}

	@Override
	public int getOrder() {
		return 0;
	}
}
