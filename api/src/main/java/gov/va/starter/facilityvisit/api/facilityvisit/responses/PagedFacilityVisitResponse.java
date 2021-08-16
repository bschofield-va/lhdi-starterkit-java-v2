package gov.va.starter.facilityvisit.api.facilityvisit.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import gov.va.starter.facilityvisit.api.responses.PagedResponse;
import lombok.NonNull;

import java.util.List;

// this class is necessary for easier documentation in springdoc
// current versions cannot directly determine the type of the list in
// PagedResponse<FacilityVisitResponse>
@Schema(
    name = "PagedAccountResponse",
    description = "Bundled list of resources with paging metadata")
public class PagedFacilityVisitResponse extends PagedResponse<FacilityVisitResponse> {

  public PagedFacilityVisitResponse(
      @NonNull List<FacilityVisitResponse> items,
      @NonNull Integer totalPages,
      @NonNull Long totalItems,
      @NonNull Integer pageNumber,
      @NonNull Integer pageSize) {
    super(items, totalPages, totalItems, pageNumber, pageSize);
  }
}
