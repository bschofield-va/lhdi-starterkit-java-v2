package gov.va.starter.facilityvisit.api.facilityvisit.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(name = "FacilityVisitRequest", description = "Metadata describing an FacilityVisit resource")
public class FacilityVisitRequest {

  @NonNull
  @Schema(description = "Facility ID of the FacilityVisit holder", example = "653")
  private final String facilityId;

  @NonNull
  @Schema(description = "Representative PII of the FacilityVisit holder", example = "123-45-6789")
  private final String pii;

  @NonNull
  @Schema(description = "Given name of the FacilityVisit holder", example = "Lucille")
  private final String firstName;

  @NonNull
  @Schema(description = "Family name of the FacilityVisit holder", example = "Van Pelt")
  private final String lastName;

  /**
   * Create object from json.
   *
   * @param facilityId ID of FacilityVisit holder
   * @param pii private information of FacilityVisit holder
   * @param firstName firstname of FacilityVisit holder
   * @param lastName lastname of FacilityVisit holder
   */
  @JsonCreator
  public FacilityVisitRequest(
      @NonNull @JsonProperty("facilityId") String facilityId,
      @NonNull @JsonProperty("pii") String pii,
      @NonNull @JsonProperty("firstName") String firstName,
      @NonNull @JsonProperty("lastName") String lastName) {

    this.facilityId = facilityId;
    this.pii = pii;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
