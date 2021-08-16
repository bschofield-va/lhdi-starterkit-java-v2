package gov.va.starter.facilityvisit.service.provider.facilityvisit;

import gov.va.starter.facilityvisit.facilityvisit.factory.FacilityVisitFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntity;
import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntityRepository;
import gov.va.starter.facilityvisit.service.provider.facilityvisit.mapper.FacilityVisitEntityMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static gov.va.starter.boot.test.data.provider.NamedDataFactory.DEFAULT_SPEC;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FacilityVisitServiceImplTest {

  private FacilityVisitServiceImpl manager;

  @Mock private FacilityVisitEntityRepository repository;
  @Mock private FacilityVisitEntityMapper mapper;

  private FacilityVisitFactory facilityVisitFactory = new FacilityVisitFactory();
  private FacilityVisitData defaultFacilityVisitData;
  private FacilityVisitData bogusFacilityVisitData;
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
  @BeforeEach
  public void setup() {

    manager = new FacilityVisitServiceImpl(repository, mapper);

    // use the real mapper to generate consistent objects to use in mapper stubs
    FacilityVisitEntityMapper real = Mappers.getMapper(FacilityVisitEntityMapper.class);
    defaultFacilityVisitData = facilityVisitFactory.createBySpec(DEFAULT_SPEC);
    bogusFacilityVisitData = facilityVisitFactory.createBySpec("bogus");

    resource =
        FacilityVisit.builder()
            .userName(defaultFacilityVisitData.getUserName())
            .pii(defaultFacilityVisitData.getPii())
            .firstName(defaultFacilityVisitData.getFirstName())
            .lastName(defaultFacilityVisitData.getLastName())
            .build();
    entity = real.toEntity(resource);
    added =
        new FacilityVisitEntity(
            defaultFacilityVisitData.getId(),
            entity.getUserName(),
            entity.getPii(),
            entity.getFirstName(),
            entity.getLastName());
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

  @Test
  public void findByFacilityVisitIdFailTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(Mockito.any())).thenReturn(emptyEntity);

    Optional<FacilityVisit> result = manager.findById(bogusFacilityVisitData.getId());
    Assertions.assertThat(!result.isPresent()).isTrue();
  }

  @Test
  public void addFacilityVisitTest() {

    createMapperStubs();
    Mockito.when(repository.save(entity)).thenReturn(added);

    FacilityVisit response = manager.add(resource);

    Assertions.assertThat(response.getFirstName()).isEqualTo(resource.getFirstName());
    Assertions.assertThat(response.getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByUserNameTest() {

    createOptionalMapperStubs();
    Mockito.when(repository.findByUserName(defaultFacilityVisitData.getUserName())).thenReturn(optionalAdded);

    Optional<FacilityVisit> response = manager.findByUserName(defaultFacilityVisitData.getUserName());

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getFirstName()).isEqualTo(added.getFirstName());
    Assertions.assertThat(response.get().getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByUserNameFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findByUserName(bogusFacilityVisitData.getUserName())).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.findByUserName(bogusFacilityVisitData.getUserName());

    Assertions.assertThat(response.isEmpty()).isTrue();
  }

  @Test
  public void findByLastNameTest() {

    createListMapperStubs();
    Mockito.when(repository.findByLastName(defaultFacilityVisitData.getLastName(), pageable)).thenReturn(entityPage);

    Page<FacilityVisit> response = manager.findByLastName(defaultFacilityVisitData.getLastName(), pageable);

    Assertions.assertThat(response.getContent().isEmpty()).isFalse();
    Assertions.assertThat(response.getContent().get(0).getFirstName())
        .isEqualTo(added.getFirstName());
    Assertions.assertThat(response.getContent().get(0).getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByLastNameFailedTest() {

    createEmptyListMapperStubs();
    Mockito.when(repository.findByLastName(bogusFacilityVisitData.getLastName(), pageable)).thenReturn(emptyEntityPage);

    Page<FacilityVisit> response = manager.findByLastName(bogusFacilityVisitData.getLastName(), pageable);

    Assertions.assertThat(response.getContent().isEmpty()).isTrue();
  }

  @Test
  public void findByIdTest() {

    createOptionalMapperStubs();
    Mockito.when(repository.findById(defaultFacilityVisitData.getId())).thenReturn(optionalAdded);

    Optional<FacilityVisit> response = manager.findById(defaultFacilityVisitData.getId());

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getFirstName()).isEqualTo(added.getFirstName());
    Assertions.assertThat(response.get().getId()).isEqualTo(added.getId());
  }

  @Test
  public void findByIdFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(bogusFacilityVisitData.getId())).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.findById(bogusFacilityVisitData.getId());

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
    Mockito.when(repository.findById(defaultFacilityVisitData.getId())).thenReturn(optionalAdded);
    Mockito.when(repository.save(added)).thenReturn(added);

    Optional<FacilityVisit> response = manager.updateById(defaultFacilityVisitData.getId(), resource);

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getFirstName()).isEqualTo(resource.getFirstName());
    Assertions.assertThat(response.get().getId()).isEqualTo(defaultFacilityVisitData.getId());
  }

  @Test
  public void updateFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(defaultFacilityVisitData.getId())).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.updateById(defaultFacilityVisitData.getId(), resource);

    Assertions.assertThat(response.isEmpty()).isTrue();
  }

  @Test
  public void deleteTest() {

    createOptionalMapperStubs();
    Mockito.when(repository.findById(defaultFacilityVisitData.getId())).thenReturn(optionalAdded);

    Optional<FacilityVisit> response = manager.deleteById(defaultFacilityVisitData.getId());

    Assertions.assertThat(response.isPresent()).isTrue();
    Assertions.assertThat(response.get().getFirstName()).isEqualTo(added.getFirstName());
    Assertions.assertThat(response.get().getId()).isEqualTo(added.getId());
  }

  @Test
  public void deleteFailedTest() {

    createEmptyMapperStubs();
    Mockito.when(repository.findById(bogusFacilityVisitData.getId())).thenReturn(emptyEntity);

    Optional<FacilityVisit> response = manager.deleteById(bogusFacilityVisitData.getId());

    Assertions.assertThat(response.isEmpty()).isTrue();
  }
}
