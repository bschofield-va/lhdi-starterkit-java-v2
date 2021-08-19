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
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("defaultUserName");
        assertThat(defaultFacilityVisit.getPii()).isEqualTo("defaultPii");
        assertThat(defaultFacilityVisit.getFirstName()).isEqualTo("defaultFirstName");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("defaultLastName");
    }

    @Test
    public void testFacilityVisitFactoryDefaultCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollection();

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("defaultId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("defaultUserName");
        assertThat(defaultFacilityVisitCollection.get(0).getPii()).isEqualTo("defaultPii");
        assertThat(defaultFacilityVisitCollection.get(0).getFirstName()).isEqualTo("defaultFirstName");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("defaultLastName");
    }

    @Test
    public void testFacilityVisitFactoryBogusRecords() {
        FacilityVisitData defaultFacilityVisit = dataFactory.createBySpec("bogus");

        assertThat(defaultFacilityVisit.getId()).isEqualTo("bogusId");
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("bogusUserName");
        assertThat(defaultFacilityVisit.getPii()).isEqualTo("bogusPii");
        assertThat(defaultFacilityVisit.getFirstName()).isEqualTo("bogusFirstName");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("bogusLastName");
    }

    @Test
    public void testFacilityVisitFactoryBogusCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollectionBySpec("bogus");

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("bogusId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("bogusUserName");
        assertThat(defaultFacilityVisitCollection.get(0).getPii()).isEqualTo("bogusPii");
        assertThat(defaultFacilityVisitCollection.get(0).getFirstName()).isEqualTo("bogusFirstName");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("bogusLastName");
    }

    @Test
    public void testFacilityVisitFactoryDupLastNameRecords() {
        FacilityVisitData defaultFacilityVisit = dataFactory.createBySpec("duplicateLastName");

        assertThat(defaultFacilityVisit.getId()).isEqualTo("dupId");
        assertThat(defaultFacilityVisit.getFacilityId()).isEqualTo("dupUserName");
        assertThat(defaultFacilityVisit.getPii()).isEqualTo("dupPii");
        assertThat(defaultFacilityVisit.getFirstName()).isEqualTo("dupFirstName");
        assertThat(defaultFacilityVisit.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void testFacilityVisitFactoryDupLastNameCollection() {
        List<FacilityVisitData> defaultFacilityVisitCollection = dataFactory.createCollectionBySpec("duplicateLastName");

        assertThat(defaultFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultFacilityVisitCollection.get(0).getId()).isEqualTo("dupId");
        assertThat(defaultFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("dupUserName");
        assertThat(defaultFacilityVisitCollection.get(0).getPii()).isEqualTo("dupPii");
        assertThat(defaultFacilityVisitCollection.get(0).getFirstName()).isEqualTo("dupFirstName");
        assertThat(defaultFacilityVisitCollection.get(0).getLastName()).isEqualTo("Smith");
    }
}