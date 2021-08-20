package gov.va.starter.facilityvisit.service.provider.jpa.facilityvisit;

import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntityRepository;
import gov.va.starter.facilityvisit.service.provider.jpa.facilityvisit.mapper.FacilityVisitEntityMapper;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.FacilityVisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JpaFacilityVisitServiceConfig {

  @Bean
  @Autowired
  @ConditionalOnProperty(name = "provider", havingValue = "jpa")
  FacilityVisitService service(
      FacilityVisitEntityRepository repository, FacilityVisitEntityMapper mapper) {
    log.info("Enabling JPA facility visit service");
    return new JpaFacilityVisitService(repository, mapper);
  }
}
