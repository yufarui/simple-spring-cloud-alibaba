package yu.routePredicateFactory;

import lombok.Data;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
public class MyTimeBetweenConfig {

    private LocalTime startTime;

    private LocalTime endTime;

}
