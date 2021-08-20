package gov.va.starter.facilityvisit.service.provider.mpi;

import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import java.util.List;

public interface FacilityVisitProvider {
  List<FacilityVisit> getForIcn(String icn);
}
