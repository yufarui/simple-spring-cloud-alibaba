package yu.sentinel.baseV2;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v2")
public class SentinelControllerV2 {

	/**
	 * 定义 SentinelV2 受保护的资源的规则
	 */
	@PostConstruct
	public void init() {

		List<FlowRule> flowRules = new ArrayList<>();

		//创建流控规则对象
		FlowRule flowRule = new FlowRule();
		//设置流控规则 QPS
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//设置受保护的资源
		flowRule.setResource("/v2/sentinel");
		//设置受保护的资源的阈值
		flowRule.setCount(1);

		flowRules.add(flowRule);

		FlowRuleManager.loadRules(flowRules);
	}

	@GetMapping("/sentinel")
	@SentinelResource(value = "/v2/sentinel", blockHandler = "sentinelV2BlockMethod")
	public String testSentinelV2() {
		doBusi();
		return "OK";
	}

	public void doBusi() {
		System.out.println("do busi2");
	}

	public String sentinelV2BlockMethod(BlockException e) {
		log.info("sentinelV2方法被流控了", e);
		return "sentinelV2方法被流控了";
	}
}
