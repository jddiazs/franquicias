package com.nequi.franquicias.product.mapper;

import com.nequi.franquicias.commos.dto.ProductDTO;
import com.nequi.franquicias.commos.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product getEntity(ProductDTO dto);
}
