package com.nequi.franquicias.subsidiary.controller;

import com.nequi.franquicias.commos.dto.SubsidiaryDTO;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.commos.model.SubsidiaryUpdateRequest;
import com.nequi.franquicias.subsidiary.service.SubsidiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubsidiaryController {

    @Autowired
    SubsidiaryService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "CreateSubsidiary", description = "crear sucursal")
    @Operation(summary = "", description = "")
    @PostMapping(value = "/api/v1/sucursal")
    public ResponseEntity<Object> create(@Valid @RequestBody SubsidiaryDTO request){
        return service.create(request);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "ListSubsidiaries", description = "lista de sucursales")
    @Operation(summary = "", description = "")
    @GetMapping(value = "/api/v1/sucursal")
    public ResponseEntity<List<Object>> list(){
        try {
            return service.list();
        } catch (Exception e) {
            return new  ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "EditSubsidiary", description = "editar sucursal")
    @Operation(summary = "", description = "")
    @PutMapping(value = "/api/v1/sucursal/{id}")
    public ResponseEntity<Object> edit(@PathVariable Integer id, @Valid @RequestBody SubsidiaryUpdateRequest request){
        try {
            return service.update(id, request);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "EditSubsidiary", description = "eliminar sucursal")
    @Operation(summary = "", description = "")
    @DeleteMapping(value = "/api/v1/sucursal/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }
}
