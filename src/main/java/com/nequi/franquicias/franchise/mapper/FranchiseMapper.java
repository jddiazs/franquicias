package com.nequi.franquicias.franchise.mapper;

import com.nequi.franquicias.commos.dto.FranchiseDTO;
import com.nequi.franquicias.commos.entity.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FranchiseMapper {

    Franchise getEntity(FranchiseDTO dto);
}
