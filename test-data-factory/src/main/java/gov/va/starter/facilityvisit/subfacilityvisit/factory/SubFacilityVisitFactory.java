package gov.va.starter.facilityvisit.subfacilityvisit.factory;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.subfacilityvisit.model.SubFacilityVisitData;

import java.util.Arrays;

public class SubFacilityVisitFactory extends NamedDataFactory<SubFacilityVisitData> {
    public SubFacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new SubFacilityVisitData( "defaultSubId", "defaultSubUserName", "defaultSubPii", "defaultSubFirstName", "defaultSubLastName"));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new SubFacilityVisitData("defaultSubId","defaultSubUserName", "defaultSubPii", "defaultSubFirstName", "defaultSubLastName"),
                new SubFacilityVisitData("defaultSubId2","defaultSubUserName2", "defaultSubPii2", "defaultSubFirstName2", "defaultSubLastName2"),
                new SubFacilityVisitData("defaultSubId3","defaultSubUserName3", "defaultSubPii3", "defaultSubFirstName3", "defaultSubLastName3"))
        );

        data.put("bogus", new SubFacilityVisitData("bogusSubId", "bogusSubUserName", "bogusSubPii","bogusSubFirstName", "bogusSubLastName"));
        collections.put("bogus", Arrays.asList(
                new SubFacilityVisitData("bogusSubId", "bogusSubUserName", "bogusSubPii","bogusSubFirstName", "bogusSubLastName"),
                new SubFacilityVisitData("bogusSubId2", "bogusSubUserName2", "bogusSubPii2","bogusSubFirstName2", "bogusSubLastName2"),
                new SubFacilityVisitData("bogusSubId3", "bogusSubUserName3", "bogusSubPii3","bogusSubFirstName3", "bogusSubLastName3"))
        );

        data.put("duplicateLastName", new SubFacilityVisitData("dupSubId","dupSubUserName", "dupSubPii", "dupSubFirstName", "Smith"));
        collections.put("duplicateLastName", Arrays.asList(
                new SubFacilityVisitData("dupSubId","dupSubUserName", "dupSubPii", "dupSubFirstName", "Smith"),
                new SubFacilityVisitData("dupSubId2","dupSubUserName2", "dupSubPii2", "dupSubFirstName2", "Smith"),
                new SubFacilityVisitData("dupSubId3","dupSubUserName3", "dupSubPii3", "dupSubFirstName3", "dupSubLastName3"))

        );
    }
}