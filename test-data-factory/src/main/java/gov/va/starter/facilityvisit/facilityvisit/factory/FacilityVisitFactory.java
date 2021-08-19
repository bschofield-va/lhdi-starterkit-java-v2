package gov.va.starter.facilityvisit.facilityvisit.factory;

import static gov.va.starter.facilityvisit.ZonedDateTimes.visitedAt;

import gov.va.starter.boot.test.data.provider.NamedDataFactory;
import gov.va.starter.facilityvisit.facilityvisit.model.FacilityVisitData;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class FacilityVisitFactory extends NamedDataFactory<FacilityVisitData> {

    public FacilityVisitFactory() {
        data.put(DEFAULT_SPEC, new FacilityVisitData("defaultId","defaultFacilityId", "defaultType", "defaultVisitorIcn", visitedAt(1)));
        collections.put(DEFAULT_SPEC, Arrays.asList(
                new FacilityVisitData("defaultId","defaultFacilityId", "defaultType", "defaultVisitorIcn", visitedAt(1)),
                new FacilityVisitData("defaultId2","defaultFacilityId2", "defaultType2", "defaultVisitorIcn2", visitedAt(2)),
                new FacilityVisitData("defaultId3","defaultFacilityId3", "defaultType3", "defaultVisitorIcn3", visitedAt(3)))

        );

        data.put("bogus", new FacilityVisitData("bogusId","bogusFacilityId", "bogusType", "bogusVisitorIcn", visitedAt(11)));
        collections.put("bogus", Arrays.asList(
                new FacilityVisitData("bogusId","bogusFacilityId", "bogusType", "bogusVisitorIcn", visitedAt(11)),
                new FacilityVisitData("bogusId2","bogusFacilityId2", "bogusType2", "bogusVisitorIcn2", visitedAt(12)),
                new FacilityVisitData("bogusId3","bogusFacilityId3", "bogusType3", "bogusVisitorIcn3", visitedAt(13)))

        );

        data.put("duplicateVisitorIcn", new FacilityVisitData("dupId","dupFacilityId", "dupType", "dupVisitorIcn", visitedAt(1)));
        collections.put("duplicateVisitorIcn", Arrays.asList(
                new FacilityVisitData("dupId","dupFacilityId", "dupType", "dupVisitorIcn", visitedAt(1)),
                new FacilityVisitData("dupId2","dupFacilityId2", "dupType2", "dupVisitorIcn", visitedAt(2)),
                new FacilityVisitData("dupId3","dupFacilityId3", "dupType3", "dupVisitorIcn3", visitedAt(3)))

        );
    }
}







