package gov.va.starter.facilityvisit.controller.facilityvisit.mapper;

import gov.va.starter.facilityvisit.api.facilityvisit.requests.FacilityVisitRequest;
import gov.va.starter.facilityvisit.api.facilityvisit.responses.FacilityVisitResponse;
import gov.va.starter.facilityvisit.api.responses.PagedResponse;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface FacilityVisitRequestMapper {

  @Mapping(constant = "UNKNOWN_ID", target = "id")
  FacilityVisit toModel(FacilityVisitRequest request);

  FacilityVisitResponse toFacilityVisitResponse(FacilityVisit src);

  default FacilityVisitResponse toFacilityVisitResponse(Optional<FacilityVisit> src) {
    return toFacilityVisitResponse(src.orElse(null));
  }

  /**
   * convert to PagedResponse<>.
   *
   * @param src Page<> object
   * @return PagedResponse<>
   */
  default PagedResponse<FacilityVisitResponse> toFacilityVisitResponsePage(Page<FacilityVisit> src) {
    return new PagedResponse<>(
        toFacilityVisitResponseList(src.getContent()),
        src.getTotalPages(),
        src.getTotalElements(),
        src.getNumber(),
        src.getNumberOfElements());
  }

  List<FacilityVisitResponse> toFacilityVisitResponseList(List<FacilityVisit> src);
}
