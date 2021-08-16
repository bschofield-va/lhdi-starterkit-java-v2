package gov.va.starter.facilityvisit.service.provider.facilityvisit;

import gov.va.starter.boot.exception.RequestValidationException;
import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntityRepository;
import gov.va.starter.facilityvisit.service.provider.facilityvisit.mapper.FacilityVisitEntityMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FacilityVisitServiceImpl implements FacilityVisitService {

  private FacilityVisitEntityRepository repository;
  private FacilityVisitEntityMapper mapper;

  FacilityVisitServiceImpl(
      FacilityVisitEntityRepository repository,
      FacilityVisitEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  /**
   * add a new FacilityVisit entity.
   *
   * @param resource resource info to add (id should be null)
   * @return new resource object with valid id
   */
  @Override
  public FacilityVisit add(FacilityVisit resource)
      throws RequestValidationException {
    FacilityVisit saved = mapper.toModel(repository.save(mapper.toEntity(resource)));
    return saved;
  }

  /**
   * find all resources matching last name.
   *
   * @param lastName criteria for match
   * @return list of matching FacilityVisit records
   */
  @Override
  public Page<FacilityVisit> findByLastName(String lastName, Pageable pageable) {
    log.info("looking up by lastname of:{}", lastName);
    Page<FacilityVisit> responseList =
        mapper.toModelPage(repository.findByLastName(lastName, pageable));
    log.info("Response list size:{}", responseList.getContent().size());
    return responseList;
  }

  /**
   * find resource by user name.
   *
   * @param userName username criteria to match
   * @return matching record, or null
   */
  @Override
  public Optional<FacilityVisit> findByUserName(String userName) {
    log.info("looking up by username:{}", userName);
    Optional<FacilityVisit> resource = mapper.toModel(repository.findByUserName(userName));
    return resource;
  }

  @Override
  public Optional<FacilityVisit> findById(String id) {
    Optional<FacilityVisit> resource = mapper.toModel(repository.findById(id));
    return resource;
  }

  @Override
  public Page<FacilityVisit> findAll(Pageable pageable) {
    Page<FacilityVisit> resource = mapper.toModelPage(repository.findAll(pageable));
    return resource;
  }

  @Override
  // CSOFF: LineLength
  public Optional<FacilityVisit> updateById(String id, FacilityVisit record)
      // CSON: LineLength
      throws RequestValidationException {
    Optional<FacilityVisit> resource =
        mapper.toModel(
            repository
                .findById(id)
                .map((obj) -> mapper.updateMetadata(record, obj))
                .map((obj) -> repository.save(obj)));

    return resource;
  }

  @Override
  public Optional<FacilityVisit> deleteById(String id) {
    Optional<FacilityVisit> resource = findById(id);
    repository.deleteById(id);
    return resource;
  }
}
