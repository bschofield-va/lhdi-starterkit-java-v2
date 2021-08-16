package gov.va.starter.facilityvisit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j //lombok creates our logger as 'log' for us
@SpringBootApplication(scanBasePackages = {"gov.va.starter.facilityvisit", "gov.va.starter.boot"})
public class FacilityVisitApplication {

  /** main function.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    new SpringApplication(FacilityVisitApplication.class).run(args);
    log.info("\n\n\n\n\n---------------FacilityVisit API Started.----------------\n\n\n\n\n");
  }
}
