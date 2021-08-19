package gov.va.starter.facilityvisit.controller.facilityvisit;

import gov.va.starter.boot.exception.ResourceNotFoundException;
import gov.va.starter.boot.notifier.EntityLifecycleNotifier;
import gov.va.starter.boot.notifier.MemoizedTimestampProvider;
import gov.va.starter.boot.notifier.NoopEntityLifecycleNotifier;
import gov.va.starter.facilityvisit.api.facilityvisit.requests.FacilityVisitRequest;
import gov.va.starter.facilityvisit.api.facilityvisit.responses.FacilityVisitResponse;
import gov.va.starter.facilityvisit.api.responses.PagedResponse;
import gov.va.starter.facilityvisit.controller.facilityvisit.mapper.FacilityVisitRequestMapper;
import gov.va.starter.facilityvisit.facilityvisit.factory.FacilityVisitFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static gov.va.starter.boot.test.data.provider.NamedDataFactory.DEFAULT_SPEC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FacilityVisitControllerTest {

  private FacilityVisitController controller;

  @Mock private FacilityVisitService manager;
  @Mock private FacilityVisitRequestMapper mapper;
  private EntityLifecycleNotifier notifier =
      new NoopEntityLifecycleNotifier(new MemoizedTimestampProvider(ZonedDateTime.now()));

  private FacilityVisit resource;
  private FacilityVisit output;
  private FacilityVisitData defaultFacilityVisit;
  private FacilityVisitData bogusFacilityVisit;
  private FacilityVisitFactory facilityVisitFactory = new FacilityVisitFactory();
  private FacilityVisitRequest request;
  private FacilityVisitResponse response;
  private Optional<FacilityVisit> emptyFacilityVisit = Optional.empty();
  private Optional<FacilityVisitResponse> emptyResponse = Optional.empty();
  private Optional<FacilityVisitResponse> optionalResponse;
  private Optional<FacilityVisit> optionalOutput;
  private List<FacilityVisitResponse> responseList;
  private List<FacilityVisit> outputList;
  private List<FacilityVisitResponse> emptyResponseList = Arrays.asList();
  private List<FacilityVisit> emptyOutputList = Arrays.asList();
  private PagedResponse<FacilityVisitResponse> responsePage;
  private PagedResponse<FacilityVisitResponse> emptyResponsePage;
  private Page<FacilityVisit> outputPage;
  private Page<FacilityVisit> emptyOutputPage;
  private Pageable pageable = Pageable.unpaged();

  /** setup data for each test. */
  @BeforeEach
  public void setup() {

    controller = new FacilityVisitController(manager, mapper, notifier);

    // use the real mapper to generate consistent objects to use in mapper stubs
    FacilityVisitRequestMapper real = Mappers.getMapper(FacilityVisitRequestMapper.class);

    defaultFacilityVisit = facilityVisitFactory.createBySpec(DEFAULT_SPEC);
    bogusFacilityVisit = facilityVisitFactory.createBySpec("bogus");

    request = new FacilityVisitRequest(defaultFacilityVisit.getUserName(), defaultFacilityVisit.getPii(), defaultFacilityVisit.getFirstName(), defaultFacilityVisit.getLastName());
    resource = real.toModel(request);
    output =
        new FacilityVisit(
            defaultFacilityVisit.getId(),
            resource.getUserName(),
            resource.getPii(),
            resource.getFirstName(),
            resource.getLastName());
    response = real.toFacilityVisitResponse(output);
    optionalResponse = Optional.of(response);
    optionalOutput = Optional.of(output);
    responseList = Arrays.asList(response, response);
    outputList = Arrays.asList(output, output);
    responsePage = new PagedResponse<>(responseList, 10, (long) 100, 1, 10);
    emptyResponsePage = new PagedResponse<>(emptyResponseList, 0, (long) 0, 0, 0);
    outputPage = new PageImpl<>(outputList);
    emptyOutputPage = new PageImpl<>(emptyOutputList);
  }

  private void createMapperStubs() {
    Mockito.when(mapper.toModel(request)).thenReturn(resource);
  }

  private void createResponseMapperStubs() {
    Mockito.when(mapper.toFacilityVisitResponse(output)).thenReturn(response);
  }

  private void createOptionalMapperStubs() {
    Mockito.when(mapper.toFacilityVisitResponse(optionalOutput)).thenReturn(response);
  }

  private void createListMapperStubs() {
    Mockito.when(mapper.toFacilityVisitResponsePage(outputPage)).thenReturn(responsePage);
  }

  private void createEmptyListMapperStubs() {
    Mockito.when(mapper.toFacilityVisitResponsePage(emptyOutputPage)).thenReturn(emptyResponsePage);
  }

  @Test
  public void findByFacilityVisitIdFailTest() throws Exception {

    Mockito.when(manager.findById(bogusFacilityVisit.getId())).thenReturn(emptyFacilityVisit);

    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          ResponseEntity<FacilityVisitResponse> response = controller.findEntityById(bogusFacilityVisit.getId());
        });
  }

  @Test
  public void addFacilityVisitTest() throws Exception {

    createMapperStubs();
    createResponseMapperStubs();
    Mockito.when(manager.add(resource)).thenReturn(output);

    ResponseEntity<FacilityVisitResponse> response =
        controller.addEntity(request);

    assertThat(response.getStatusCodeValue()).isEqualTo(201);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getPii()).isEqualTo(defaultFacilityVisit.getPii());
    assertThat(response.getBody().getId()).isEqualTo(defaultFacilityVisit.getId());
  }

  @Test
  public void findByIdTest() throws Exception {

    createResponseMapperStubs();
    Mockito.when(manager.findById(defaultFacilityVisit.getId())).thenReturn(optionalOutput);

    ResponseEntity<FacilityVisitResponse> response =
        controller.findEntityById(defaultFacilityVisit.getId());

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getPii()).isEqualTo(defaultFacilityVisit.getPii());
    assertThat(response.getBody().getId()).isEqualTo(defaultFacilityVisit.getId());
  }

  @Test
  public void findByIdFailedTest() throws Exception {

    Mockito.when(manager.findById(bogusFacilityVisit.getId())).thenReturn(emptyFacilityVisit);

    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          ResponseEntity<FacilityVisitResponse> response =
              controller.findEntityById(bogusFacilityVisit.getId());
        });
  }

  @Test
  public void findAllTest() throws Exception {

    createListMapperStubs();
    Mockito.when(manager.findAll(pageable)).thenReturn(outputPage);

    ResponseEntity<PagedResponse<FacilityVisitResponse>> response =
        controller.findEntities(pageable);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getItems().size()).isEqualTo(2);
    // Todo: check contents of the list objects
  }

  @Test
  public void findAllEmptyTest() throws Exception {

    createEmptyListMapperStubs();
    Mockito.when(manager.findAll(pageable)).thenReturn(emptyOutputPage);

    ResponseEntity<PagedResponse<FacilityVisitResponse>> response =
        controller.findEntities(pageable);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getItems().size()).isEqualTo(0);
  }

  @Test
  public void updateTest() throws Exception {

    createMapperStubs();
    createResponseMapperStubs();
    Mockito.when(manager.updateById(defaultFacilityVisit.getId(), resource)).thenReturn(optionalOutput);

    ResponseEntity<FacilityVisitResponse> response =
        controller.updateEntityById(defaultFacilityVisit.getId(), request);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getPii()).isEqualTo(defaultFacilityVisit.getPii());
    assertThat(response.getBody().getId()).isEqualTo(defaultFacilityVisit.getId());
  }

  @Test
  public void updateFailedTest() throws Exception {

    createMapperStubs();
    Mockito.when(manager.updateById(bogusFacilityVisit.getId(), resource)).thenReturn(emptyFacilityVisit);

    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          ResponseEntity<FacilityVisitResponse> response =
              controller.updateEntityById(bogusFacilityVisit.getId(), request);
        });
  }

  @Test
  public void deleteTest() throws Exception {

    createResponseMapperStubs();
    Mockito.when(manager.deleteById(defaultFacilityVisit.getId())).thenReturn(optionalOutput);

    ResponseEntity<FacilityVisitResponse> response =
        controller.deleteEntityById(defaultFacilityVisit.getId());

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getPii()).isEqualTo(defaultFacilityVisit.getPii());
    assertThat(response.getBody().getId()).isEqualTo(defaultFacilityVisit.getId());
  }

  @Test
  public void deleteFailedTest() throws Exception {

    Mockito.when(manager.deleteById(bogusFacilityVisit.getId())).thenReturn(emptyFacilityVisit);

    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          ResponseEntity<FacilityVisitResponse> response =
              controller.deleteEntityById(bogusFacilityVisit.getId());
        });
  }

  /**
   * helper function to validate standard values.
   *
   * @param resource the object to validate
   */
  protected void verifyFacilityVisit(FacilityVisit resource) {
    assertThat(resource.getUserName().equals(defaultFacilityVisit.getUserName()));
    assertThat(resource.getPii().equals(defaultFacilityVisit.getPii()));
    assertThat(resource.getFirstName().equals(defaultFacilityVisit.getFirstName()));
    assertThat(resource.getLastName().equals(defaultFacilityVisit.getLastName()));
    assertThat(resource.getId()).isNotEqualTo(defaultFacilityVisit.getId());
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  private void verifyFacilityVisitResponse(FacilityVisitResponse response) {
    assertThat(response.getUserName().equals(defaultFacilityVisit.getUserName()));
    assertThat(response.getPii().equals(defaultFacilityVisit.getPii()));
    assertThat(response.getId()).isEqualTo(defaultFacilityVisit.getId());
  }
}
