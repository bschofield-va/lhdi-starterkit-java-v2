package gov.va.starter.facilityvisit.controller.facilityvisit.mapper;

import gov.va.starter.facilityvisit.facilityvisit.factory.FacilityVisitFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import gov.va.starter.facilityvisit.api.facilityvisit.requests.FacilityVisitRequest;
import gov.va.starter.facilityvisit.api.facilityvisit.responses.FacilityVisitResponse;
import gov.va.starter.facilityvisit.api.responses.PagedResponse;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static gov.va.starter.boot.test.data.provider.NamedDataFactory.DEFAULT_SPEC;
import static org.assertj.core.api.Assertions.assertThat;

public class FacilityVisitRequestMapperTest {

  private FacilityVisitRequestMapper mapper;
  private FacilityVisitFactory facilityVisitFactory = new FacilityVisitFactory();
  private FacilityVisitData defaultFacilityVisitData = facilityVisitFactory.createBySpec(DEFAULT_SPEC);

  @BeforeEach
  public void setup() {
    mapper = Mappers.getMapper(FacilityVisitRequestMapper.class);
  }

  @Test
  public void mapperNewFacilityVisitTest() {
    FacilityVisitRequest resource = createFacilityVisitRequest();

    FacilityVisit response = mapper.toModel(resource);

    verifyFacilityVisit(response);
  }

  @Test
  public void mapperFacilityVisitResponseTest() {
    FacilityVisit resource = createFacilityVisit(defaultFacilityVisitData.getId());

    FacilityVisitResponse response = mapper.toFacilityVisitResponse(resource);

    verifyFacilityVisitResponse(response);
  }

  @Test
  public void mapperOptionalTest() {
    Optional<FacilityVisit> resource = Optional.of(createFacilityVisit(defaultFacilityVisitData.getId()));

    FacilityVisitResponse response = mapper.toFacilityVisitResponse(resource);

    assertThat(response).isNotNull();
    verifyFacilityVisitResponse(response);
  }

  @Test
  public void mapperOptionalNullTest() {
    Optional<FacilityVisit> resource = Optional.ofNullable(null);

    FacilityVisitResponse response = mapper.toFacilityVisitResponse(resource);

    assertThat(response).isNull();
  }

  @Test
  public void mapperOptionalEmptyTest() {
    Optional<FacilityVisit> resource = Optional.empty();

    FacilityVisitResponse response =
        mapper.toFacilityVisitResponse(resource);

    assertThat(response).isNull();
  }

  @Test
  public void mapperEntityListTest() {
    List<FacilityVisit> resources = Arrays.asList(
        createFacilityVisit(defaultFacilityVisitData.getId()),
        createFacilityVisit(defaultFacilityVisitData.getId()));

    List<FacilityVisitResponse> response =
        mapper.toFacilityVisitResponseList(resources);

    assertThat(response.size()).isEqualTo(2);
    verifyFacilityVisitResponse(response.get(0));
    verifyFacilityVisitResponse(response.get(1));
  }

  @Test
  public void mapperEntityPageTest() {
    Pageable pageable = PageRequest.of(0, 1);
    Page<FacilityVisit> resources =
        new PageImpl<>(Arrays.asList(createFacilityVisit(defaultFacilityVisitData.getId())), pageable, 100);
    PagedResponse<FacilityVisitResponse> response =
        mapper.toFacilityVisitResponsePage(resources);

    assertThat(response.getItems().size()).isEqualTo(1);
    assertThat(response.getTotalItems()).isEqualTo(100);
    assertThat(response.getPageNumber()).isEqualTo(0);
    assertThat(response.getPageSize()).isEqualTo(1);
    assertThat(response.getTotalPages()).isEqualTo(100);
    verifyFacilityVisitResponse(response.getItems().get(0));
  }

  /**
   * convenience function to create resource object.
   *
   * @param id whether to create with identifier (null if not)
   * @return FacilityVisit object
   */
  private FacilityVisit createFacilityVisit(String id) {
    return new FacilityVisit(id, defaultFacilityVisitData.getFacilityId(), defaultFacilityVisitData.getType(), defaultFacilityVisitData.getVisitorIcn(), defaultFacilityVisitData.getVisitedAt());
  }

  /**
   * convenience function to create resource request object.
   *
   * @return FacilityVisitRequest object
   */
  private FacilityVisitRequest createFacilityVisitRequest() {
    return new FacilityVisitRequest(defaultFacilityVisitData.getFacilityId(), defaultFacilityVisitData.getType(), defaultFacilityVisitData.getVisitorIcn(), defaultFacilityVisitData.getVisitedAt());
  }

  /**
   * helper function to validate standard values.
   *
   * @param resource the object to validate
   */
  protected void verifyFacilityVisit(FacilityVisit resource) {
    assertThat(resource.getFacilityId().equals(defaultFacilityVisitData.getFacilityId()));
    assertThat(resource.getType().equals(defaultFacilityVisitData.getType()));
    assertThat(resource.getVisitorIcn().equals(defaultFacilityVisitData.getVisitorIcn()));
    assertThat(resource.getVisitedAt().equals(defaultFacilityVisitData.getVisitedAt()));
    assertThat(resource.getId()).isNotEqualTo(defaultFacilityVisitData.getId());
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  private void verifyFacilityVisitResponse(FacilityVisitResponse response) {
    assertThat(response.getFacilityId().equals(defaultFacilityVisitData.getFacilityId()));
    assertThat(response.getType().equals(defaultFacilityVisitData.getType()));
    assertThat(response.getId()).isEqualTo(defaultFacilityVisitData.getId());
  }
}
