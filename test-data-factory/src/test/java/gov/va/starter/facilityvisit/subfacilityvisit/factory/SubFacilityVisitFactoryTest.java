package gov.va.starter.facilityvisit.subfacilityvisit.factory;


import gov.va.starter.facilityvisit.subfacilityvisit.model.SubFacilityVisitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubFacilityVisitFactoryTest {
    private SubFacilityVisitFactory dataFactory;
    @BeforeEach
    public void setup() {
        dataFactory = new SubFacilityVisitFactory();
    }

    @Test
    public void testSubFacilityVisitFactoryDefaultRecords() {
        SubFacilityVisitData defaultSubFacilityVisit = dataFactory.create();

        assertThat(defaultSubFacilityVisit.getId()).isEqualTo("defaultSubId");
        assertThat(defaultSubFacilityVisit.getUserName()).isEqualTo("defaultSubUserName");
        assertThat(defaultSubFacilityVisit.getPii()).isEqualTo("defaultSubPii");
        assertThat(defaultSubFacilityVisit.getFirstName()).isEqualTo("defaultSubFirstName");
        assertThat(defaultSubFacilityVisit.getLastName()).isEqualTo("defaultSubLastName");
    }

    @Test
    public void testSubFacilityVisitFactoryDefaultCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollection();

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("defaultSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getUserName()).isEqualTo("defaultSubUserName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getPii()).isEqualTo("defaultSubPii");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFirstName()).isEqualTo("defaultSubFirstName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getLastName()).isEqualTo("defaultSubLastName");
    }

    @Test
    public void testSubFacilityVisitFactoryBogusRecords() {
        SubFacilityVisitData defaultSubFacilityVisit = dataFactory.createBySpec("bogus");

        assertThat(defaultSubFacilityVisit.getId()).isEqualTo("bogusSubId");
        assertThat(defaultSubFacilityVisit.getUserName()).isEqualTo("bogusSubUserName");
        assertThat(defaultSubFacilityVisit.getPii()).isEqualTo("bogusSubPii");
        assertThat(defaultSubFacilityVisit.getFirstName()).isEqualTo("bogusSubFirstName");
        assertThat(defaultSubFacilityVisit.getLastName()).isEqualTo("bogusSubLastName");
    }

    @Test
    public void testSubFacilityVisitFactoryBogusCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollectionBySpec("bogus");

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("bogusSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getUserName()).isEqualTo("bogusSubUserName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getPii()).isEqualTo("bogusSubPii");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFirstName()).isEqualTo("bogusSubFirstName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getLastName()).isEqualTo("bogusSubLastName");
    }

    @Test
    public void testSubFacilityVisitFactoryDupLastNameRecords() {
        SubFacilityVisitData defaultSubFacilityVisit = dataFactory.createBySpec("duplicateLastName");

        assertThat(defaultSubFacilityVisit.getId()).isEqualTo("dupSubId");
        assertThat(defaultSubFacilityVisit.getUserName()).isEqualTo("dupSubUserName");
        assertThat(defaultSubFacilityVisit.getPii()).isEqualTo("dupSubPii");
        assertThat(defaultSubFacilityVisit.getFirstName()).isEqualTo("dupSubFirstName");
        assertThat(defaultSubFacilityVisit.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void testSubFacilityVisitFactoryDupLastNameCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollectionBySpec("duplicateLastName");

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("dupSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getUserName()).isEqualTo("dupSubUserName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getPii()).isEqualTo("dupSubPii");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFirstName()).isEqualTo("dupSubFirstName");
        assertThat(defaultSubFacilityVisitCollection.get(0).getLastName()).isEqualTo("Smith");
    }
}
