package com.nequi.franquicias.commos.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SubsidiaryDTO {
    private Integer id;
    @NotNull(message="id de franquicia es requerido")
    private Integer franchiseId;
    @NotEmpty(message="campo nombre es requerido")
    private String name;
}
