package yu.routePredicateFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
@Slf4j
public class MyTimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<MyTimeBetweenConfig> {

	public MyTimeBetweenRoutePredicateFactory() {
		super(MyTimeBetweenConfig.class);
	}

	@Override
	public Predicate<ServerWebExchange> apply(MyTimeBetweenConfig config) {

		LocalTime startTime = config.getStartTime();

		LocalTime endTime = config.getEndTime();

		return new Predicate<ServerWebExchange>() {
			@Override
			public boolean test(ServerWebExchange serverWebExchange) {
				LocalTime now = LocalTime.now();

				return now.isAfter(startTime) && now.isBefore(endTime);
			}
		};

	}

	public List<String> shortcutFieldOrder() {
		return Arrays.asList("startTime", "endTime");
	}

	public static void main(String[] args) {
		DateTimeFormatter formatterToUse = DateTimeContextHolder.getFormatter(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT),
			LocaleContextHolder.getLocale());

		LocalTime parse = LocalTime.parse("上午7:00", formatterToUse);
		System.out.println(parse);
	}

}
