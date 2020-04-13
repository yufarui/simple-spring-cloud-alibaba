package yu.globalHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import yu.exception.BussinessException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class BaseControllerAdvice {

	@ResponseBody
	@ExceptionHandler(value = BussinessException.class)
	public Map<String, String> errorHandler(Exception ex) {

		Throwable causeUsingPlainJava = findCauseUsingPlainJava(ex);

		Map<String, String> map = new HashMap<>();
		map.put("code", "20");
		map.put("msg", causeUsingPlainJava.getMessage());
		return map;
	}

	public Throwable findCauseUsingPlainJava(Throwable throwable) {
		Objects.requireNonNull(throwable);
		Throwable rootCause = throwable;
		while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
			rootCause = rootCause.getCause();
		}
		return rootCause;
	}
}
