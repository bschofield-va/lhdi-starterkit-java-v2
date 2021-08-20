package gov.va.starter.facilityvisit.service.provider.mpi;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;

import gov.va.api.lighthouse.mpi.MpiConfig;
import gov.va.api.lighthouse.mpi.PatientIdentifierSegment;
import gov.va.api.lighthouse.mpi.SoapMasterPatientIndexClient;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hl7.v3.II;
import org.hl7.v3.PRPAIN201310UV02;
import org.hl7.v3.PRPAIN201310UV02MFMIMT700711UV01Subject1;
import org.hl7.v3.PRPAMT201304UV02Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@Slf4j
public class MpiFacilityVisitProvider implements FacilityVisitProvider {

  private final MpiConfig mpiConfig;

  @Setter private Function<String, PRPAIN201310UV02> request1309;

  public MpiFacilityVisitProvider(
      @Autowired MpiConfig mpiConfig,
      @Autowired(required = false) Function<String, PRPAIN201310UV02> request1309) {
    this.mpiConfig = mpiConfig;
    this.request1309 = request1309;
  }

  public static class MpiFacilityVisitProviderException extends RuntimeException {
    public MpiFacilityVisitProviderException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public static class RequestFailed extends MpiFacilityVisitProviderException {
    public RequestFailed(String message, Throwable cause) {
      super(message, cause);
    }
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public static class WrongNumberOfPatientsFound extends MpiFacilityVisitProviderException {
    public WrongNumberOfPatientsFound(String message) {
      super(message, null);
    }
  }

  @Override
  public List<FacilityVisit> getForIcn(String icn) {
    return targetsForPatient(icn).stream()
        .map(
            site ->
                FacilityVisit.builder()
                    .id("whatever")
                    .visitorIcn(icn)
                    .facilityId(site)
                    .visitedAt(ZonedDateTime.now())
                    .type("http://whatever.com/whatever")
                    .build())
        .collect(toList());
  }

  /** Lazy Getter. */
  public Function<String, PRPAIN201310UV02> request1309() {
    if (request1309 == null) {
      return (icn) -> {
        try {
          return SoapMasterPatientIndexClient.of(mpiConfig).request1309ByIcn(icn);
        } catch (Exception e) {
          throw new RequestFailed("Failed to request 1309", e);
        }
      };
    }
    return request1309;
  }

  private List<String> targetsForPatient(String icn) {
    if (isBlank(icn)) {
      return List.of();
    }
    log.info("Searching for {}", icn);
    PRPAIN201310UV02 response = request1309().apply(icn);
    log.info("Response: {}", response);
    List<PRPAIN201310UV02MFMIMT700711UV01Subject1> maybePatients =
        response.getControlActProcess().getSubject();

    if (maybePatients.size() == 0) {
      log.warn("Patient is not known to MPI");
      return List.of();
    }
    /*
     * With a national icn, we only expect 1 patient. If MPI returns more, we need to kill the
     * response to avoid leaking PII
     */
    if (maybePatients.size() > 1) {
      throw new WrongNumberOfPatientsFound(
          "Expected one patient in response, got " + maybePatients.size());
    }
    PRPAMT201304UV02Patient patient =
        maybePatients.get(0).getRegistrationEvent().getSubject1().getPatient();
    List<String> stationIds =
        patient.getId().stream()
            .filter(Objects::nonNull)
            .map(II::getExtension)
            .filter(Objects::nonNull)
            .map(PatientIdentifierSegment::parse)
            .filter(PatientIdentifierSegment::isVistaSite)
            .map(PatientIdentifierSegment::assigningLocation)
            .collect(toList());
    log.info("Stations: {}", stationIds);
    return stationIds;
  }
}
