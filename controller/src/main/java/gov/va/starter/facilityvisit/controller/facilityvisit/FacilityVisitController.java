package gov.va.starter.facilityvisit.controller.facilityvisit;

import gov.va.starter.boot.exception.RequestValidationException;
import gov.va.starter.boot.exception.ResourceNotFoundException;
import gov.va.starter.boot.notifier.EntityLifecycleNotifier;
import gov.va.starter.facilityvisit.api.facilityvisit.requests.FacilityVisitRequest;
import gov.va.starter.facilityvisit.api.facilityvisit.resources.FacilityVisitResource;
import gov.va.starter.facilityvisit.api.facilityvisit.responses.FacilityVisitResponse;
import gov.va.starter.facilityvisit.api.responses.PagedResponse;
import gov.va.starter.facilityvisit.controller.facilityvisit.mapper.FacilityVisitRequestMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
public class FacilityVisitController implements FacilityVisitResource {

  private final FacilityVisitService manager;
  private final FacilityVisitRequestMapper mapper;
  private final EntityLifecycleNotifier notifier;
  // TODO: Need to find a better way to determine version of entity
  private final String entityVersion = "0.0.1";

  /**
   * constructor.
   *
   * @param manager instance of FacilityVisit manager
   * @param mapper instance of FacilityVisit request mappper
   */
  public FacilityVisitController(
      FacilityVisitService manager, FacilityVisitRequestMapper mapper, EntityLifecycleNotifier notifier) {
    this.manager = manager;
    this.mapper = mapper;
    this.notifier = notifier;
  }

  @Override
  public ResponseEntity<FacilityVisitResponse> addEntity(FacilityVisitRequest addEntityRequest)
      throws RequestValidationException {

    log.info("username->{}", addEntityRequest.getFacilityId());
    FacilityVisit resource = mapper.toModel(addEntityRequest);
    FacilityVisit saved = manager.add(resource);
    FacilityVisitResponse response = mapper.toFacilityVisitResponse(saved);
    notifier.created(saved, entityVersion, URI.create("user:anonymous"));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<FacilityVisitResponse> findEntityById(String id)
      throws ResourceNotFoundException {

    log.info("id->{}", id);
    Optional<FacilityVisit> found = manager.findById(id);
    return new ResponseEntity<>(
        found
            .map(r -> mapper.toFacilityVisitResponse(r))
            .orElseThrow(() -> new ResourceNotFoundException(id)),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PagedResponse<FacilityVisitResponse>> findEntities(Pageable pageable) {
    Page<FacilityVisit> resources = manager.findAll(pageable);

    return new ResponseEntity<>(mapper.toFacilityVisitResponsePage(resources), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FacilityVisitResponse> updateEntityById(String id, FacilityVisitRequest request)
      throws ResourceNotFoundException, RequestValidationException {

    log.info("id->{}", id);
    Optional<FacilityVisit> found = manager.updateById(id, mapper.toModel(request));
    if (found.isPresent()) {
      notifier.updated(found.get(), entityVersion, URI.create("user:anonymous"));
    }
    return new ResponseEntity<>(
        found
            .map(r -> mapper.toFacilityVisitResponse(r))
            .orElseThrow(() -> new ResourceNotFoundException(id)),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FacilityVisitResponse> deleteEntityById(String id)
      throws ResourceNotFoundException {

    log.info("id->{}", id);
    Optional<FacilityVisit> found = manager.deleteById(id);
    if (found.isPresent()) {
      notifier.deleted(found.get(), entityVersion, URI.create("user:anonymous"));
    }
    return new ResponseEntity<>(
        found
            .map(r -> mapper.toFacilityVisitResponse(r))
            .orElseThrow(() -> new ResourceNotFoundException(id)),
        HttpStatus.OK);
  }
}
