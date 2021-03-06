package gov.va.starter.facilityvisit.service.spi.facilityvisit.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FacilityVisit {

  private String id;

  @NonNull
  private String facilityId;
  @NonNull
  private String type;
  @NonNull
  private String visitorIcn;
  @NonNull
  private ZonedDateTime visitedAt;
}
