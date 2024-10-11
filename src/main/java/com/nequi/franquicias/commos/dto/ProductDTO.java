package com.nequi.franquicias.commos.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private Integer id;
    @NotNull(message="id de sucursal es requerido")
    private Integer subsidiaryId;
    @NotEmpty(message="campo nombre es requerido")
    private String name;
    @NotNull(message="numero de stock es requerido")
    private Integer stock;
}
