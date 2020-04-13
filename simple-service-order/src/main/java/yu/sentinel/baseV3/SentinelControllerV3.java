package yu.sentinel.baseV3;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("/v3")
public class SentinelControllerV3 {
	private final int[] list = {20, 100};

	@PostConstruct
	public void init() {

		DegradeRule degradeRule = new DegradeRule();
		degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
		degradeRule.setCount(1);
		degradeRule.setTimeWindow(10);
		degradeRule.setResource("testRt");

		DegradeRuleManager.loadRules(Arrays.asList(degradeRule));
	}

	@GetMapping("/sentinel")
	@SentinelResource(value = "testRt", blockHandler = "sentinelBlockMethod"
		, fallback = "sentinelFallbackMethod")
	public String testRt() {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
		int nextInt = threadLocalRandom.nextInt(100);

		int i = Arrays.binarySearch(list, nextInt);
		boolean isOk;
		if (i >= 0) {
			isOk = i == 0;
		} else {
			int j = -i - 1;
			isOk = (-i - 1) == 0;
		}

		if (isOk) {
			throw new RuntimeException("异常");
		}

		return "sentinelV3 Ok";
	}

	public String sentinelFallbackMethod(Throwable e) {
		log.info("sentinelV3降级", e);
		return "sentinelV3降级";
	}

	public String sentinelBlockMethod(BlockException e) {
		log.info("sentinelV3方法被流控了", e);
		return "sentinelV3方法被流控了";
	}
}
