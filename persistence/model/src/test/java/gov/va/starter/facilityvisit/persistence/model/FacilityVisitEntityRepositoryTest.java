package gov.va.starter.facilityvisit.persistence.model;

import gov.va.starter.facilityvisit.facilityvisit.factory.FacilityVisitFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FacilityVisitEntityRepositoryTest {

  @Autowired private FacilityVisitEntityRepository modelEntityRepository;

  private FacilityVisitEntity entity;

  private FacilityVisitFactory facilityVisitFactory = new FacilityVisitFactory();
  private List<FacilityVisitData> defaultFacilityVisitDataCollection = facilityVisitFactory.createCollectionBySpec("duplicateLastName");

  @BeforeEach
  public void setup() {
    entity = new FacilityVisitEntity(defaultFacilityVisitDataCollection.get(0).getFacilityId(), defaultFacilityVisitDataCollection.get(0).getPii(), defaultFacilityVisitDataCollection.get(0).getVisitorIcn(), defaultFacilityVisitDataCollection.get(0).getLastName());
  }

  /**
   * populate the tables with some tests data.
   *
   * @return one of the saved entities
   */
  public FacilityVisitEntity populate() {
    FacilityVisitEntity result = modelEntityRepository.save(entity);
    FacilityVisitEntity agentSmith = new FacilityVisitEntity(defaultFacilityVisitDataCollection.get(1).getFacilityId(), defaultFacilityVisitDataCollection.get(1).getPii(), defaultFacilityVisitDataCollection.get(1).getVisitorIcn(), defaultFacilityVisitDataCollection.get(1).getLastName());
    modelEntityRepository.save(agentSmith);
    FacilityVisitEntity maryQuiteContrary =
        new FacilityVisitEntity(defaultFacilityVisitDataCollection.get(2).getFacilityId(), defaultFacilityVisitDataCollection.get(2).getPii(), defaultFacilityVisitDataCollection.get(2).getVisitorIcn(), defaultFacilityVisitDataCollection.get(2).getLastName());
    modelEntityRepository.save(maryQuiteContrary);

    return result;
  }

  @Test
  public void setupValid() {
    assertThat(modelEntityRepository != null);
  }

  @Test
  public void createAndGetTest() {
    modelEntityRepository.save(entity);

    Optional<FacilityVisitEntity> retrievedEntity = modelEntityRepository.findByFacilityId(defaultFacilityVisitDataCollection.get(0).getFacilityId());

    assertThat(retrievedEntity.isPresent());
    assertThat(retrievedEntity.get().getVisitorIcn()).isEqualTo(defaultFacilityVisitDataCollection.get(0).getVisitorIcn());
  }

  @Test
  public void testFindByLastName() {
    populate();

    Page<FacilityVisitEntity> retrievedSmiths =
        modelEntityRepository.findByLastName(defaultFacilityVisitDataCollection.get(0).getLastName(), Pageable.unpaged());

    assertThat(retrievedSmiths.getContent().size()).isEqualTo(2);
  }

  @Test
  public void testFindByLastNamePaged() {
    populate();

    Pageable pageable = PageRequest.of(0, 1);
    Page<FacilityVisitEntity> retrievedSmiths =
        modelEntityRepository.findByLastName(defaultFacilityVisitDataCollection.get(0).getLastName(), pageable);

    assertThat(retrievedSmiths.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testUpdateRecord() {
    final String newName = "Contrary";
    FacilityVisitEntity saved = modelEntityRepository.save(entity);
    saved.setLastName(newName);

    FacilityVisitEntity updated = modelEntityRepository.save(saved);

    Page<FacilityVisitEntity> retrievedSmiths =
        modelEntityRepository.findByLastName(defaultFacilityVisitDataCollection.get(0).getLastName(), Pageable.unpaged());
    assertThat(retrievedSmiths.getContent().size()).isEqualTo(0);
    Page<FacilityVisitEntity> retrievedContrarians =
        modelEntityRepository.findByLastName(newName, Pageable.unpaged());
    assertThat(retrievedContrarians.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testDeleteRecord() {
    FacilityVisitEntity saved = populate();

    modelEntityRepository.deleteById(saved.getId());

    Page<FacilityVisitEntity> retrievedSmiths =
        modelEntityRepository.findByLastName(defaultFacilityVisitDataCollection.get(0).getLastName(), Pageable.unpaged());
    assertThat(retrievedSmiths.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testFindAll() {
    populate();
    Page<FacilityVisitEntity> retrieved = modelEntityRepository.findAll(Pageable.unpaged());
    assertThat(retrieved.getContent().size()).isEqualTo(3);
  }

  @Test
  public void testFindAllPaged() {
    populate();
    Pageable pageable = PageRequest.of(0, 2);
    Page<FacilityVisitEntity> retrieved = modelEntityRepository.findAll(pageable);
    assertThat(retrieved.getContent().size()).isEqualTo(2);
  }
}
