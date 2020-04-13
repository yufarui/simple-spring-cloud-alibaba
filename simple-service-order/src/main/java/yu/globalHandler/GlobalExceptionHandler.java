package yu.globalHandler;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class GlobalExceptionHandler {

	public static SentinelClientHttpResponse handleException(HttpRequest request,
	                                                         byte[] body, ClientHttpRequestExecution execution, BlockException ex) {

		return new SentinelClientHttpResponse("限流");
	}

	public static SentinelClientHttpResponse fallback(HttpRequest request,
	                                                  byte[] body, ClientHttpRequestExecution execution, BlockException ex) {

		return new SentinelClientHttpResponse("降级");
	}
}
