package gov.va.starter.facilityvisit;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZonedDateTimes {
  public static ZonedDateTime visitedAt(int day) {
    return ZonedDateTime.of(2005, 1, day, 7, 57, 0, 0, ZoneId.of("America/New_York"));
  }

}
