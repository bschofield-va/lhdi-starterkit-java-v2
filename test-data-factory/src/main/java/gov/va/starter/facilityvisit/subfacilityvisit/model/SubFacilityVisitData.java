package gov.va.starter.facilityvisit.subfacilityvisit.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubFacilityVisitData {

    private String id;

    @NonNull
    private String facilityId;
    @NonNull
    private String type;
    @NonNull
    private String visitorIcn;
    @NonNull
    private String lastName;

}
