package com.nequi.franquicias.commos.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SubsidiaryUpdateRequest {
    private Integer id;
    private Integer franchiseId;
    private String name;
}
