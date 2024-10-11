package com.nequi.franquicias.commos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductoMaxStockDTO {
    private String producto;
    private String sucursal;
    private Integer stock;
}
