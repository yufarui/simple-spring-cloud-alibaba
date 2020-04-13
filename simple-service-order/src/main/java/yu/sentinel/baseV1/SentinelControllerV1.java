package yu.sentinel.baseV1;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
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
@RequestMapping("/v1")
public class SentinelControllerV1 {

	/**
	 * 定义 SentinelV1 受保护的资源的规则
	 */
	@PostConstruct
	public void init() {

		List<FlowRule> flowRules = new ArrayList<>();

		//创建流控规则对象
		FlowRule flowRule = new FlowRule();
		//设置流控规则 QPS
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//设置受保护的资源
		flowRule.setResource("/v1/sentinel");
		//设置受保护的资源的阈值
		flowRule.setCount(1);

		flowRules.add(flowRule);

		FlowRuleManager.loadRules(flowRules);
	}

	@GetMapping("/sentinel")
	public String testSentinelV1() {

		Entry entity = null;
		//关联受保护的资源
		try {
			entity = SphU.entry("/v1/sentinel");
			//开始执行 自己的业务方法
			doBusi();
			//结束执行自己的业务方法
		} catch (BlockException e) {
			log.info("sentinelV1方法被流控了", e);
			return "sentinelV1方法被流控了";
		} finally {
			if (entity != null) {
				entity.exit();
			}
		}

		return "OK";
	}

	public void doBusi() {
		System.out.println("do busi1");
	}
}
