package gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit;

import gov.va.starter.facilityvisit.service.provider.mpi.FacilityVisitProvider;
import gov.va.starter.facilityvisit.service.provider.mpi.facilityvisit.mapper.MpiFacilityVisitMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MpiFacilityVisitServiceConfig {

  @Bean
  @Autowired
  @ConditionalOnProperty(name = "provider", havingValue = "mpi")
  FacilityVisitService service(FacilityVisitProvider provider, MpiFacilityVisitMapper mapper) {
    log.info("Enabling MPI facility visit service");
    return new MpiFacilityVisitService(provider, mapper);
  }
}
