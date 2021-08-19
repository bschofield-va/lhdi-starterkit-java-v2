package gov.va.starter.facilityvisit.persistence.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityVisitEntityRepository
    extends PagingAndSortingRepository<FacilityVisitEntity, String> {

  Optional<FacilityVisitEntity> findByUserName(String userName);

  Page<FacilityVisitEntity> findByLastName(String lastName, Pageable pageable);
}