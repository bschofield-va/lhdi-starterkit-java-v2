package gov.va.starter.facilityvisit.service.spi.facilityvisit;

import gov.va.starter.boot.exception.RequestValidationException;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FacilityVisitService {

  FacilityVisit add(FacilityVisit resource)
      throws RequestValidationException;

  Page<FacilityVisit> findByVisitorIcn(String visitorIcn, Pageable pageable);

  Optional<FacilityVisit> findByFacilityId(String facilityId);

  Optional<FacilityVisit> findById(String id);

  Page<FacilityVisit> findAll(Pageable pageable);

  Optional<FacilityVisit> updateById(String id, FacilityVisit record)
      throws RequestValidationException;

  Optional<FacilityVisit> deleteById(String id);
}
