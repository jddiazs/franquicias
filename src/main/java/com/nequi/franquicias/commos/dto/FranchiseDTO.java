package com.nequi.franquicias.commos.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FranchiseDTO {
    private Integer id;
    @NotEmpty(message="campo nombre es requerido")
    private String name;
}
