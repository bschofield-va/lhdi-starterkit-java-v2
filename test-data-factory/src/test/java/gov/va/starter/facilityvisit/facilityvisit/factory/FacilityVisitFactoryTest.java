package gov.va.starter.facilityvisit.facilityvisit.factory;

import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


public class FacilityVisitFactoryTest {
    private FacilityVisitFactory dataFactory;
    @BeforeEach
    public void setup() {
        dataFactory = new FacilityVisitFactory();
    }

    @Test
    public void testFacilityVisitFactoryDefaultRecords() {
        FacilityVisitData defaultFacilityVisit = dataFactory.create();

        assertThat(defaultFacilityVisit.getId()).isEqualTo("defaultId");
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("defaultFacilityId");
        assertThat(defaultFacilityVisit.getType()).isEqualTo("defaultType");
        assertThat(defaultFacilityVisit.getVisitorIcn()).isEqualTo("defaultVisitorIcn");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("defaultLastName");
    }

    @Test
    public void testFacilityVisitFactoryDefaultCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollection();

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("defaultId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("defaultFacilityId");
        assertThat(defaultFacilityVisitCollection.get(0).getType()).isEqualTo("defaultType");
        assertThat(defaultFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("defaultVisitorIcn");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("defaultLastName");
    }

    @Test
    public void testFacilityVisitFactoryBogusRecords() {
        FacilityVisitData defaultFacilityVisit = dataFactory.createBySpec("bogus");

        assertThat(defaultFacilityVisit.getId()).isEqualTo("bogusId");
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("bogusFacilityId");
        assertThat(defaultFacilityVisit.getType()).isEqualTo("bogusType");
        assertThat(defaultFacilityVisit.getVisitorIcn()).isEqualTo("bogusVisitorIcn");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("bogusLastName");
    }

    @Test
    public void testFacilityVisitFactoryBogusCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollectionBySpec("bogus");

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("bogusId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("bogusFacilityId");
        assertThat(defaultFacilityVisitCollection.get(0).getType()).isEqualTo("bogusType");
        assertThat(defaultFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("bogusVisitorIcn");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("bogusLastName");
    }

    @Test
    public void testFacilityVisitFactoryDupLastNameRecords() {
        FacilityVisitData defaultFacilityVisit = dataFactory.createBySpec("duplicateLastName");

        assertThat(defaultFacilityVisit.getId()).isEqualTo("dupId");
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("dupFacilityId");
        assertThat(defaultFacilityVisit.getType()).isEqualTo("dupType");
        assertThat(defaultFacilityVisit.getVisitorIcn()).isEqualTo("dupVisitorIcn");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void testFacilityVisitFactoryDupLastNameCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollectionBySpec("duplicateLastName");

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("dupId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("dupFacilityId");
        assertThat(defaultFacilityVisitCollection.get(0).getType()).isEqualTo("dupType");
        assertThat(defaultFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("dupVisitorIcn");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("Smith");
    }
}