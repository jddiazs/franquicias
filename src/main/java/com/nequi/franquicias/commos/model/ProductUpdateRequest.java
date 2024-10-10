package com.nequi.franquicias.commos.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductUpdateRequest {
    private Integer id;
    private Integer subsidiaryId;
    private String name;
    private Integer stock;
}
