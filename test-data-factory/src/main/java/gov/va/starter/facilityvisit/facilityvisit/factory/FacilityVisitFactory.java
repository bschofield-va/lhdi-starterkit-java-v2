package gov.va.starter.facilityvisit.facilityvisit.factory;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;

import java.util.Arrays;

public class FacilityVisitFactory extends NamedDataFactory<FacilityVisitData> {
    public FacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new FacilityVisitData("defaultId","defaultFacilityId", "defaultPii", "defaultVisitorIcn", "defaultLastName"));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new FacilityVisitData("defaultId","defaultFacilityId", "defaultPii", "defaultVisitorIcn", "defaultLastName"),
                new FacilityVisitData("defaultId2","defaultFacilityId2", "defaultPii2", "defaultVisitorIcn2", "defaultLastName2"),
                new FacilityVisitData("defaultId3","defaultFacilityId3", "defaultPii3", "defaultVisitorIcn3", "defaultLastName3"))

        );

        data.put("bogus", new FacilityVisitData("bogusId","bogusFacilityId", "bogusPii", "bogusVisitorIcn", "bogusLastName"));
        collections.put("bogus", Arrays.asList(
                new FacilityVisitData("bogusId","bogusFacilityId", "bogusPii", "bogusVisitorIcn", "bogusLastName"),
                new FacilityVisitData("bogusId2","bogusFacilityId2", "bogusPii2", "bogusVisitorIcn2", "bogusLastName2"),
                new FacilityVisitData("bogusId3","bogusFacilityId3", "bogusPii3", "bogusVisitorIcn3", "bogusLastName3"))

        );

        data.put("duplicateLastName", new FacilityVisitData("dupId","dupFacilityId", "dupPii", "dupVisitorIcn", "Smith"));
        collections.put("duplicateLastName", Arrays.asList(
                new FacilityVisitData("dupId","dupFacilityId", "dupPii", "dupVisitorIcn", "Smith"),
                new FacilityVisitData("dupId2","dupFacilityId2", "dupPii2", "dupVisitorIcn2", "Smith"),
                new FacilityVisitData("dupId3","dupFacilityId3", "dupPii3", "dupVisitorIcn3", "dupLastName3"))

        );
    }
}







