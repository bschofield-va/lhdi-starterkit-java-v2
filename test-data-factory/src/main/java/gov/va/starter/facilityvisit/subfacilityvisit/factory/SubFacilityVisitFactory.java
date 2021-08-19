package gov.va.starter.facilityvisit.subfacilityvisit.factory;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.subfacilityvisit.model.SubFacilityVisitData;

import java.util.Arrays;

public class SubFacilityVisitFactory extends NamedDataFactory<SubFacilityVisitData> {
    public SubFacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new SubFacilityVisitData( "defaultSubId", "defaultSubFacilityId", "defaultSubType", "defaultSubVisitorIcn", "defaultSubLastName"));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new SubFacilityVisitData("defaultSubId","defaultSubFacilityId", "defaultSubType", "defaultSubVisitorIcn", "defaultSubLastName"),
                new SubFacilityVisitData("defaultSubId2","defaultSubFacilityId2", "defaultSubType2", "defaultSubVisitorIcn2", "defaultSubLastName2"),
                new SubFacilityVisitData("defaultSubId3","defaultSubFacilityId3", "defaultSubType3", "defaultSubVisitorIcn3", "defaultSubLastName3"))
        );

        data.put("bogus", new SubFacilityVisitData("bogusSubId", "bogusSubFacilityId", "bogusSubType","bogusSubVisitorIcn", "bogusSubLastName"));
        collections.put("bogus", Arrays.asList(
                new SubFacilityVisitData("bogusSubId", "bogusSubFacilityId", "bogusSubType","bogusSubVisitorIcn", "bogusSubLastName"),
                new SubFacilityVisitData("bogusSubId2", "bogusSubFacilityId2", "bogusSubType2","bogusSubVisitorIcn2", "bogusSubLastName2"),
                new SubFacilityVisitData("bogusSubId3", "bogusSubFacilityId3", "bogusSubType3","bogusSubVisitorIcn3", "bogusSubLastName3"))
        );

        data.put("duplicateLastName", new SubFacilityVisitData("dupSubId","dupSubFacilityId", "dupSubType", "dupSubVisitorIcn", "Smith"));
        collections.put("duplicateLastName", Arrays.asList(
                new SubFacilityVisitData("dupSubId","dupSubFacilityId", "dupSubType", "dupSubVisitorIcn", "Smith"),
                new SubFacilityVisitData("dupSubId2","dupSubFacilityId2", "dupSubType2", "dupSubVisitorIcn2", "Smith"),
                new SubFacilityVisitData("dupSubId3","dupSubFacilityId3", "dupSubType3", "dupSubVisitorIcn3", "dupSubLastName3"))

        );
    }
}