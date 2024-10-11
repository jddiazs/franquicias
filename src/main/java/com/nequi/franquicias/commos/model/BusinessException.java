package com.nequi.franquicias.commos.model;


import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException{
    private String message;
    private HttpStatus code;
}
