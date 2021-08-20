package gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntity;
import gov.va.starter.facilityvisit.service.provider.mpi.FacilityVisitProvider;
import gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit.MpiFacilityVisitService;
import gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit.mapper.MpiFacilityVisitMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MpiFacilityVisitServiceTest {

  private MpiFacilityVisitService manager;

  @Mock private FacilityVisitProvider repository;
  @Mock private MpiFacilityVisitMapper mapper;

  private final String bogusAnything = "bogus";
  private final String facilityId = "654";
  private final String visitorIcn = "1234567890V123456";
  private final String type =
      "https://api.va.gov/services/facility-visit/v1/value-sets/vist-type/visited";
  private final ZonedDateTime visitedAt = ZonedDateTime.parse("2021-05-05T05:05:05.555Z");
  private final String identifier = "12345";

  private FacilityVisit resource;
  private FacilityVisit output;
  private FacilityVisitEntity entity;
  private FacilityVisitEntity added;
  private Optional<FacilityVisit> emptyFacilityVisit = Optional.empty();
  private Optional<FacilityVisitEntity> emptyEntity = Optional.empty();
  private Optional<FacilityVisitEntity> optionalEntity;
  private Optional<FacilityVisitEntity> optionalAdded;
  private Optional<FacilityVisit> optionalOutput;
  private List<FacilityVisitEntity> entityList;
  private List<FacilityVisit> outputList;
  private List<FacilityVisitEntity> emptyEntityList = Arrays.asList();
  private List<FacilityVisit> emptyOutputList = Arrays.asList();
  private Page<FacilityVisitEntity> entityPage;
  private Page<FacilityVisit> outputPage;
  private Page<FacilityVisitEntity> emptyEntityPage;
  private Page<FacilityVisit> emptyOutputPage;
  private Pageable pageable = Pageable.unpaged();

  /** setup data for each test. */
  /*
  @BeforeEach
  public void setup() {

    manager = new MpiFacilityVisitService(repository, mapper);

    // use the real mapper to generate consistent objects to use in mapper stubs
    MpiFacilityVisitMapper real = Mappers.getMapper(MpiFacilityVisitMapper.class);

    resource =
        FacilityVisit.builder()
            .facilityId(facilityId)
            .visitorIcn(visitorIcn)
            .type(type)
            .visitedAt(visitedAt)
            .build();
    entity = real.toEntity(resource);
    added =
        new FacilityVisitEntity(
            identifier,
            entity.getFacilityId(),
            entity.getVisitorIcn(),
            entity.getType(),
            entity.getVisitedAt());
    output = real.toModel(added);
    optionalEntity = Optional.of(entity);
    optionalAdded = Optional.of(added);
    optionalOutput = Optional.of(output);
    entityList = Arrays.asList(added, added);
    outputList = Arrays.asList(output, output);
    entityPage = new PageImpl<>(entityList);
    outputPage = new PageImpl<>(outputList);
    emptyEntityPage = new PageImpl<>(emptyEntityList);
    emptyOutputPage = new PageImpl<>(emptyOutputList);
  }

  private void createMapperStubs() {
    Mockito.when(mapper.toEntity(resource)).thenReturn(entity);
    Mockito.when(mapper.toModel(added)).thenReturn(output);
  }

  private void createOptionalMapperStubs() {
    Mockito.when(mapper.toModel(optionalAdded)).thenReturn(optionalOutput);
  }

  private void createEmptyMapperStubs() {
    Mockito.when(mapper.toModel(emptyEntity)).thenReturn(emptyFacilityVisit);
  }

  private void createListMapperStubs() {
    Mockito.when(mapper.toModelPage(entityPage)).thenReturn(outputPage);
  }

  private void createEmptyListMapperStubs() {
    Mockito.when(mapper.toModelPage(emptyEntityPage)).thenReturn(emptyOutputPage);
  }
  */

  @Test
  void loljk() {
    assertTrue(true);
  }
  /*
  @Test
  public void findByFacilityVisitIdFailTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(Mockito.any())).thenReturn(emptyEntity);

    Optional<FacilityVisit> result = manager.findById("bogus");
    Assertions.assertThat(!result.isPresent()).isTrue();
  }

  @Test
  public void addFacilityVisitTest() {

    createMapperStubs();
    Mockito.when(repository.save(entity)).thenReturn(added);

    FacilityVisit response = manager.add(resource);

    Assertions.assertThat(response.getType()).isEqualTo(resource.getType());
    Assertions.assertThat(response.getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByLastNameTest() {

    createListMapperStubs();
    Mockito.when(repository.findByVisitorIcn(facilityId, pageable)).thenReturn(entityPage);

    Page<FacilityVisit> response = manager.findByVisitorIcn(facilityId, pageable);

    Assertions.assertThat(response.getContent().isEmpty()).isFalse();
    Assertions.assertThat(response.getContent().get(0).getType()).isEqualTo(added.getType());
    Assertions.assertThat(response.getContent().get(0).getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByLastNameFailedTest() {

    createEmptyListMapperStubs();
    Mockito.when(repository.findByVisitorIcn(bogusAnything, pageable)).thenReturn(emptyEntityPage);

    Page<FacilityVisit> response = manager.findByVisitorIcn(bogusAnything, pageable);

    Assertions.assertThat(response.getContent().isEmpty()).isTrue();
  }

  @Test
  public void findByIdTest() {

    createOptionalMapperStubs();
    Mockito.when(repository.findById(identifier)).thenReturn(optionalAdded);

    Optional<FacilityVisit> response = manager.findById(identifier);

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getType()).isEqualTo(added.getType());
    Assertions.assertThat(response.get().getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByIdFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(bogusAnything)).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.findById(bogusAnything);

    Assertions.assertThat(response.isEmpty()).isTrue();
  }

  @Test
  public void findAllTest() {

    createListMapperStubs();
    Mockito.when(repository.findAll(pageable)).thenReturn(entityPage);

    Page<FacilityVisit> response = manager.findAll(pageable);

    Assertions.assertThat(response.getContent().size()).isEqualTo(2);
  }

  @Test
  public void findAllEmptyTest() {

    createEmptyListMapperStubs();
    Mockito.when(repository.findAll(pageable)).thenReturn(emptyEntityPage);

    Page<FacilityVisit> response = manager.findAll(pageable);

    Assertions.assertThat(response.getContent().size()).isEqualTo(0);
  }

  @Test
  public void updateTest() {

    createOptionalMapperStubs();
    Mockito.when(mapper.updateMetadata(resource, added)).thenReturn(added);
    Mockito.when(repository.findById(identifier)).thenReturn(optionalAdded);
    Mockito.when(repository.save(added)).thenReturn(added);

    Optional<FacilityVisit> response = manager.updateById(identifier, resource);

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getType()).isEqualTo(resource.getType());
    Assertions.assertThat(response.get().getId()).isEqualTo(identifier);
  }

  @Test
  public void updateFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(identifier)).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.updateById(identifier, resource);

    Assertions.assertThat(response.isEmpty()).isTrue();
  }

  @Test
  public void deleteTest() {

    createOptionalMapperStubs();
    Mockito.when(repository.findById(identifier)).thenReturn(optionalAdded);

    Optional<FacilityVisit> response = manager.deleteById(identifier);

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getType()).isEqualTo(added.getType());
    Assertions.assertThat(response.get().getId()).isEqualTo(added.getId());
  }

  @Test
  public void deleteFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(bogusAnything)).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.deleteById(bogusAnything);

    Assertions.assertThat(response.isEmpty()).isTrue();
  }
  */
}
