package gov.va.starter.facilityvisit.facilityvisit.factory;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;

import java.util.Arrays;

public class FacilityVisitFactory extends NamedDataFactory<FacilityVisitData> {
    public FacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new FacilityVisitData("defaultId","defaultFacilityId", "defaultType", "defaultVisitorIcn", "defaultVisitedAt"));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new FacilityVisitData("defaultId","defaultFacilityId", "defaultType", "defaultVisitorIcn", "defaultVisitedAt"),
                new FacilityVisitData("defaultId2","defaultFacilityId2", "defaultType2", "defaultVisitorIcn2", "defaultVisitedAt2"),
                new FacilityVisitData("defaultId3","defaultFacilityId3", "defaultType3", "defaultVisitorIcn3", "defaultVisitedAt3"))

        );

        data.put("bogus", new FacilityVisitData("bogusId","bogusFacilityId", "bogusType", "bogusVisitorIcn", "bogusVisitedAt"));
        collections.put("bogus", Arrays.asList(
                new FacilityVisitData("bogusId","bogusFacilityId", "bogusType", "bogusVisitorIcn", "bogusVisitedAt"),
                new FacilityVisitData("bogusId2","bogusFacilityId2", "bogusType2", "bogusVisitorIcn2", "bogusVisitedAt2"),
                new FacilityVisitData("bogusId3","bogusFacilityId3", "bogusType3", "bogusVisitorIcn3", "bogusVisitedAt3"))

        );

        data.put("duplicateVisitedAt", new FacilityVisitData("dupId","dupFacilityId", "dupType", "dupVisitorIcn", "Smith"));
        collections.put("duplicateVisitedAt", Arrays.asList(
                new FacilityVisitData("dupId","dupFacilityId", "dupType", "dupVisitorIcn", "Smith"),
                new FacilityVisitData("dupId2","dupFacilityId2", "dupType2", "dupVisitorIcn2", "Smith"),
                new FacilityVisitData("dupId3","dupFacilityId3", "dupType3", "dupVisitorIcn3", "dupVisitedAt3"))

        );
    }
}







