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
        assertThat(defaultSubFacilityVisit.getFacilityId()).isEqualTo("defaultSubFacilityId");
        assertThat(defaultSubFacilityVisit.getType()).isEqualTo("defaultSubType");
        assertThat(defaultSubFacilityVisit.getVisitorIcn()).isEqualTo("defaultSubVisitorIcn");
        assertThat(defaultSubFacilityVisit.getVisitedAt()).isEqualTo("defaultSubVisitedAt");
    }

    @Test
    public void testSubFacilityVisitFactoryDefaultCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollection();

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("defaultSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("defaultSubFacilityId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getType()).isEqualTo("defaultSubType");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("defaultSubVisitorIcn");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitedAt()).isEqualTo("defaultSubVisitedAt");
    }

    @Test
    public void testSubFacilityVisitFactoryBogusRecords() {
        SubFacilityVisitData defaultSubFacilityVisit = dataFactory.createBySpec("bogus");

        assertThat(defaultSubFacilityVisit.getId()).isEqualTo("bogusSubId");
        assertThat(defaultSubFacilityVisit.getFacilityId()).isEqualTo("bogusSubFacilityId");
        assertThat(defaultSubFacilityVisit.getType()).isEqualTo("bogusSubType");
        assertThat(defaultSubFacilityVisit.getVisitorIcn()).isEqualTo("bogusSubVisitorIcn");
        assertThat(defaultSubFacilityVisit.getVisitedAt()).isEqualTo("bogusSubVisitedAt");
    }

    @Test
    public void testSubFacilityVisitFactoryBogusCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollectionBySpec("bogus");

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("bogusSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("bogusSubFacilityId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getType()).isEqualTo("bogusSubType");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("bogusSubVisitorIcn");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitedAt()).isEqualTo("bogusSubVisitedAt");
    }

    @Test
    public void testSubFacilityVisitFactoryDupVisitedAtRecords() {
        SubFacilityVisitData defaultSubFacilityVisit = dataFactory.createBySpec("duplicateVisitedAt");

        assertThat(defaultSubFacilityVisit.getId()).isEqualTo("dupSubId");
        assertThat(defaultSubFacilityVisit.getFacilityId()).isEqualTo("dupSubFacilityId");
        assertThat(defaultSubFacilityVisit.getType()).isEqualTo("dupSubType");
        assertThat(defaultSubFacilityVisit.getVisitorIcn()).isEqualTo("dupSubVisitorIcn");
        assertThat(defaultSubFacilityVisit.getVisitedAt()).isEqualTo("Smith");
    }

    @Test
    public void testSubFacilityVisitFactoryDupVisitedAtCollection() {
        List<SubFacilityVisitData> defaultSubFacilityVisitCollection = dataFactory.createCollectionBySpec("duplicateVisitedAt");

        assertThat(defaultSubFacilityVisitCollection.size()).isEqualTo(3);
        assertThat(defaultSubFacilityVisitCollection.get(0).getId()).isEqualTo("dupSubId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getFacilityId()).isEqualTo("dupSubFacilityId");
        assertThat(defaultSubFacilityVisitCollection.get(0).getType()).isEqualTo("dupSubType");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitorIcn()).isEqualTo("dupSubVisitorIcn");
        assertThat(defaultSubFacilityVisitCollection.get(0).getVisitedAt()).isEqualTo("Smith");
    }
}
