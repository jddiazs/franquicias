package com.nequi.franquicias.subsidiary.mapper;

import com.nequi.franquicias.commos.dto.SubsidiaryDTO;
import com.nequi.franquicias.commos.entity.Subsidiary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubsidiaryMapper {
    Subsidiary getEntity(SubsidiaryDTO dto);
}
