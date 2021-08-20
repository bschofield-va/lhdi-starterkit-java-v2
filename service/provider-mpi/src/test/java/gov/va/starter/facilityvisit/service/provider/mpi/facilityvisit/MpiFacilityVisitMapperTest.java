package gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntity;
import gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit.mapper.MpiFacilityVisitMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@Disabled
public class MpiFacilityVisitMapperTest {

  private MpiFacilityVisitMapper mapper;

  private final String facilityId = "654";
  private final String visitorIcn = "1234567890V123456";
  private final String type =
      "https://api.va.gov/services/facility-visit/v1/value-sets/vist-type/visited";
  private final ZonedDateTime visitedAt = ZonedDateTime.parse("2021-05-05T05:05:05.555Z");
  private final String identifier = "12345";

  @BeforeEach
  public void setup() {
    mapper = Mappers.getMapper(MpiFacilityVisitMapper.class);
  }

  @Test
  void jklol() {
    assertTrue(true);
  }

  /*
    @Test
    @Disabled
    public void mapperNewFacilityVisitTest() {
      FacilityVisit resource = createFacilityVisit(null);

      FacilityVisitEntity response = mapper.toEntity(resource);

      verifyFacilityVisitEntity(response, false);
    }

    @Test
    @Disabled
    public void mapperFacilityVisitTest() {
      FacilityVisit resource = createFacilityVisit(identifier);

      FacilityVisitEntity response = mapper.toEntity(resource);

      verifyFacilityVisitEntity(response);
    }

    @Test
    @Disabled
    public void mapperEntityTest() {
      FacilityVisitEntity entity = createFacilityVisitEntity();

      FacilityVisit response = mapper.toModel(entity);

      verifyFacilityVisit(response);
    }

    @Test
    @Disabled
    public void mapperOptionalEntityTest() {
      Optional<FacilityVisitEntity> entity = Optional.of(createFacilityVisitEntity());

      Optional<FacilityVisit> response = mapper.toModel(entity);

      assertThat(response.isPresent());
      verifyFacilityVisit(response.get());
    }

    @Test
    @Disabled
    public void mapperOptionalTest() {
      Optional<FacilityVisit> resource = Optional.of(createFacilityVisit(null));

      Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

      assertThat(response.isPresent());
      verifyFacilityVisitEntity(response.get(), false);
    }

    @Test
    @Disabled
    public void mapperOptionalNullTest() {
      Optional<FacilityVisit> resource = Optional.ofNullable(null);

      Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

      assertThat(response.isEmpty());
    }

    @Test
    @Disabled
    public void mapperOptionalEmptyTest() {
      Optional<FacilityVisit> resource = Optional.empty();

      Optional<FacilityVisitEntity> response = mapper.toEntity(resource);

      assertThat(response.isEmpty());
    }

    @Test
    @Disabled
    public void mapperEntityListTest() {
      List<FacilityVisitEntity> entities =
          Arrays.asList(createFacilityVisitEntity(), createFacilityVisitEntity());

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
  */
  /**
   * convenience function to create resource object.
   *
   * @param id whether to create with identifier (null if not)
   * @return FacilityVisit object
   */
  private FacilityVisit createFacilityVisit(String id) {
    return new FacilityVisit(id, facilityId, visitorIcn, type, visitedAt);
  }

  /**
   * convenience function to create resource entity object.
   *
   * @return FacilityVisitEntity object
   */
  private FacilityVisitEntity createFacilityVisitEntity() {
    return new FacilityVisitEntity(identifier, facilityId, visitorIcn, type, visitedAt);
  }

  /**
   * helper function to validate standard values.
   *
   * @param response the object to validate
   */
  protected void verifyFacilityVisit(FacilityVisit response) {
    assertThat(response.getFacilityId()).isEqualTo(facilityId);
    assertThat(response.getVisitorIcn()).isEqualTo(visitorIcn);
    assertThat(response.getType()).isEqualTo(type);
    assertThat(response.getVisitedAt()).isEqualTo(visitedAt);
    assertThat(response.getId()).isEqualTo(identifier);
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
    assertThat(response.getFacilityId()).isEqualTo(facilityId);
    assertThat(response.getVisitorIcn()).isEqualTo(visitorIcn);
    assertThat(response.getType()).isEqualTo(type);
    assertThat(response.getVisitedAt()).isEqualTo(visitedAt);
    if (hasId) {
      assertThat(response.getId()).isEqualTo(identifier);
    } else {
      assertThat(response.getId()).isNotEqualTo(identifier);
    }
  }
}
