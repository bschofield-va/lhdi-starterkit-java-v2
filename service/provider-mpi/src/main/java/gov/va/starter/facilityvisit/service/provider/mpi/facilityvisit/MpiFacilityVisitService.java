package gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit;

import gov.va.starter.boot.exception.RequestValidationException;
import gov.va.starter.facilityvisit.service.provider.mpi.FacilityVisitProvider;
import gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit.mapper.MpiFacilityVisitMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class MpiFacilityVisitService implements FacilityVisitService {

  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public static class NotSupported extends RuntimeException {
    public NotSupported(String message) {
      super(message);
    }
  }

  private FacilityVisitProvider provider;
  private MpiFacilityVisitMapper mapper;

  MpiFacilityVisitService(FacilityVisitProvider provider, MpiFacilityVisitMapper mapper) {
    this.provider = provider;
    this.mapper = mapper;
  }

  /**
   * add a new FacilityVisit entity.
   *
   * @param resource resource info to add (id should be null)
   * @return new resource object with valid id
   */
  @Override
  public FacilityVisit add(FacilityVisit resource) throws RequestValidationException {
    throw new NotSupported("add");
  }

  /**
   * find all resources matching last name.
   *
   * @param visitorIcn criteria for match
   * @return list of matching FacilityVisit records
   */
  @Override
  public Page<FacilityVisit> findByVisitorIcn(String visitorIcn, Pageable pageable) {
    log.info("looking up by lastname of:{}", visitorIcn);
    // TODO: do this better
    var visit = provider.getForIcn(visitorIcn);
    Page<FacilityVisit> responseList = new PageImpl(visit);
    log.info("Response list size:{}", responseList.getContent().size());
    return responseList;
  }

  @Override
  public Optional<FacilityVisit> findById(String id) {
    throw new NotSupported("findById");
  }
  public Optional<FacilityVisit> findByFacilityId(String id) {
    throw new NotSupported("findById");
  }

  @Override
  public Page<FacilityVisit> findAll(Pageable pageable) {
    throw new NotSupported("findAll");
  }

  @Override
  public Optional<FacilityVisit> updateById(String id, FacilityVisit record)
      throws RequestValidationException {
    throw new NotSupported("updateById");
  }

  @Override
  public Optional<FacilityVisit> deleteById(String id) {
    throw new NotSupported("deleteById");
  }
}
