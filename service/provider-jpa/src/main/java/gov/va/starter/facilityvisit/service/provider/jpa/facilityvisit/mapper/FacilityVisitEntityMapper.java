package gov.va.starter.facilityvisit.service.provider.jpa.facilityvisit.mapper;

import gov.va.starter.facilityvisit.persistence.model.FacilityVisitEntity;
import gov.va.starter.facilityvisit.service.spi.facilityvisit.model.FacilityVisit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface FacilityVisitEntityMapper {

  FacilityVisitEntity toEntity(FacilityVisit src);

  default Optional<FacilityVisitEntity> toEntity(Optional<FacilityVisit> src) {
    return Optional.ofNullable(toEntity(src.orElse(null)));
  }

  List<FacilityVisitEntity> toEntityList(List<FacilityVisit> src);

  FacilityVisit toModel(FacilityVisitEntity src);

  default Optional<FacilityVisit> toModel(Optional<FacilityVisitEntity> src) {
    return Optional.ofNullable(toModel(src.orElse(null)));
  }

  default Page<FacilityVisit> toModelPage(Page<FacilityVisitEntity> src) {
    return src.map(this::toModel);
  }

  List<FacilityVisit> toModelList(Iterable<FacilityVisitEntity> src);

  @Mapping(target = "id", ignore = true)
  FacilityVisitEntity updateMetadata(FacilityVisit src, @MappingTarget FacilityVisitEntity dst);
}
