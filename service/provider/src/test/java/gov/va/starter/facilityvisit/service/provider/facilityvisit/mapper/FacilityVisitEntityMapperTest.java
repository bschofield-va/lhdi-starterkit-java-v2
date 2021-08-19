package gov.va.starter.facilityvisit.service.provider.facilityvisit.mapper;

import gov.va.starter.facilityvisit.facilityvisit.factory.FacilityVisitFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntity;
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

public class FacilityVisitEntityMapperTest {

  private FacilityVisitFactory facilityVisitFactory = new FacilityVisitFactory();
  private FacilityVisitData facilityVisitData = facilityVisitFactory.createBySpec(DEFAULT_SPEC);
  private FacilityVisitEntityMapper mapper;

  @BeforeEach
  public void setup() {
    mapper = Mappers.getMapper(FacilityVisitEntityMapper.class);
  }

  @Test
  public void mapperNewFacilityVisitTest() {
    FacilityVisit resource = createFacilityVisit(null);

    FacilityVisitEntity response = mapper.toEntity(resource);

    verifyFacilityVisitEntity(response, false);
  }

  @Test
  public void mapperFacilityVisitTest() {
    FacilityVisit resource = createFacilityVisit(facilityVisitData.getId());

    FacilityVisitEntity response = mapper.toEntity(resource);

    verifyFacilityVisitEntity(response);
  }

  @Test
  public void mapperEntityTest() {
    FacilityVisitEntity entity = createFacilityVisitEntity();

    FacilityVisit response = mapper.toModel(entity);

    verifyFacilityVisit(response);
  }

  @Test
  public void mapperOptionalEntityTest() {
    Optional<FacilityVisitEntity> entity = Optional.of(createFacilityVisitEntity());

    Optional<FacilityVisit> response = mapper.toModel(entity);

    assertThat(response.isPresent());
    verifyFacilityVisit(response.get());
  }

  @Test
  public void mapperOptionalTest() {
    Optional<FacilityVisit> resource = Optional.of(createFacilityVisit(null));

    Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

    assertThat(response.isPresent());
    verifyFacilityVisitEntity(response.get(), false);
  }

  @Test
  public void mapperOptionalNullTest() {
    Optional<FacilityVisit> resource = Optional.ofNullable(null);

    Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

    assertThat(response.isEmpty());
  }

  @Test
  public void mapperOptionalEmptyTest() {
    Optional<FacilityVisit> resource = Optional.empty();

    Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

    assertThat(response.isEmpty());
  }

  @Test
  public void mapperEntityListTest() {
    List<FacilityVisitEntity> entities = Arrays.asList(
        createFacilityVisitEntity(),
        createFacilityVisitEntity());

    List<FacilityVisit> response = mapper.toModelList(entities);

    assertThat(response.size()).isEqualTo(2);
    verifyFacilityVisit(response.get(0));
    verifyFacilityVisit(response.get(1));
  }

  @Test
  public void mapperEntityPageTest() {
    Pageable pageable = PageRequest.of(0, 3);
    Page<FacilityVisitEntity> entities =
        new PageImpl<>(
            Arrays.asList(
                createFacilityVisitEntity(),
                createFacilityVisitEntity(),
                createFacilityVisitEntity()),
            pageable,
            100);

    Page<FacilityVisit> response = mapper.toModelPage(entities);

    assertThat(response.getContent().size()).isEqualTo(3);
    assertThat(response.getTotalElements()).isEqualTo(100);
    assertThat(response.getNumber()).isEqualTo(0);
    assertThat(response.getNumberOfElements()).isEqualTo(3);

    verifyFacilityVisit(response.toList().get(0));
    verifyFacilityVisit(response.toList().get(1));
    verifyFacilityVisit(response.toList().get(2));
  }

  /**
   * convenience function to create resource object.
   *
   * @param id whether to create with identifier (null if not)
   * @return FacilityVisit object
   */
  private FacilityVisit createFacilityVisit(String id) {
    return new FacilityVisit(id, facilityVisitData.getFacilityId(), facilityVisitData.getType(), facilityVisitData.getVisitorIcn(), facilityVisitData.getLastName());
  }

  /**
   * convenience function to create resource entity object.
   *
   * @return FacilityVisitEntity object
   */
  private FacilityVisitEntity createFacilityVisitEntity() {
    return new FacilityVisitEntity(facilityVisitData.getId(), facilityVisitData.getFacilityId(), facilityVisitData.getType(), facilityVisitData.getVisitorIcn(), facilityVisitData.getLastName());
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  protected void verifyFacilityVisit(FacilityVisit response) {
    assertThat(response.getFacilityId()).isEqualTo(facilityVisitData.getFacilityId());
    assertThat(response.getType()).isEqualTo(facilityVisitData.getType());
    assertThat(response.getVisitorIcn()).isEqualTo(facilityVisitData.getVisitorIcn());
    assertThat(response.getLastName()).isEqualTo(facilityVisitData.getLastName());
    assertThat(response.getId()).isEqualTo(facilityVisitData.getId());
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  private void verifyFacilityVisitEntity(FacilityVisitEntity response) {
    verifyFacilityVisitEntity(response, true);
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  // CSOFF: LineLength
  private void verifyFacilityVisitEntity(FacilityVisitEntity response, boolean hasId) {
    // CSON: LineLength
    assertThat(response.getFacilityId()).isEqualTo(facilityVisitData.getFacilityId());
    assertThat(response.getType()).isEqualTo(facilityVisitData.getType());
    assertThat(response.getVisitorIcn()).isEqualTo(facilityVisitData.getVisitorIcn());
    assertThat(response.getLastName()).isEqualTo(facilityVisitData.getLastName());
    if (hasId) {
      assertThat(response.getId()).isEqualTo(facilityVisitData.getId());
    } else {
      assertThat(response.getId()).isNotEqualTo(facilityVisitData.getId());
    }
  }
}
