package gov.va.starter.facilityvisit.subfacilityvisit.factory;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.subfacilityvisit.model.SubFacilityVisitData;

import java.util.Arrays;

public class SubFacilityVisitFactory extends NamedDataFactory<SubFacilityVisitData> {
    public SubFacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new SubFacilityVisitData( "defaultSubId", "defaultSubFacilityId", "defaultSubPii", "defaultSubVisitorIcn", "defaultSubLastName"));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new SubFacilityVisitData("defaultSubId","defaultSubFacilityId", "defaultSubPii", "defaultSubVisitorIcn", "defaultSubLastName"),
                new SubFacilityVisitData("defaultSubId2","defaultSubFacilityId2", "defaultSubPii2", "defaultSubVisitorIcn2", "defaultSubLastName2"),
                new SubFacilityVisitData("defaultSubId3","defaultSubFacilityId3", "defaultSubPii3", "defaultSubVisitorIcn3", "defaultSubLastName3"))
        );

        data.put("bogus", new SubFacilityVisitData("bogusSubId", "bogusSubFacilityId", "bogusSubPii","bogusSubVisitorIcn", "bogusSubLastName"));
        collections.put("bogus", Arrays.asList(
                new SubFacilityVisitData("bogusSubId", "bogusSubFacilityId", "bogusSubPii","bogusSubVisitorIcn", "bogusSubLastName"),
                new SubFacilityVisitData("bogusSubId2", "bogusSubFacilityId2", "bogusSubPii2","bogusSubVisitorIcn2", "bogusSubLastName2"),
                new SubFacilityVisitData("bogusSubId3", "bogusSubFacilityId3", "bogusSubPii3","bogusSubVisitorIcn3", "bogusSubLastName3"))
        );

        data.put("duplicateLastName", new SubFacilityVisitData("dupSubId","dupSubFacilityId", "dupSubPii", "dupSubVisitorIcn", "Smith"));
        collections.put("duplicateLastName", Arrays.asList(
                new SubFacilityVisitData("dupSubId","dupSubFacilityId", "dupSubPii", "dupSubVisitorIcn", "Smith"),
                new SubFacilityVisitData("dupSubId2","dupSubFacilityId2", "dupSubPii2", "dupSubVisitorIcn2", "Smith"),
                new SubFacilityVisitData("dupSubId3","dupSubFacilityId3", "dupSubPii3", "dupSubVisitorIcn3", "dupSubLastName3"))

        );
    }
}