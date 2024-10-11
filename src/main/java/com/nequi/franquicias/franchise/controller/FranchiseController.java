package com.nequi.franquicias.franchise.controller;

import com.nequi.franquicias.commos.dto.FranchiseDTO;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.franchise.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FranchiseController {

    @Autowired
    FranchiseService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "CreateFranchise", description = "crear franquicia")
    @Operation(summary = "", description = "")
    @PostMapping(value = "/api/v1/franquicia")
    public ResponseEntity<Object> create(@Valid @RequestBody FranchiseDTO request){
        return service.create(request);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "ListFranchise", description = "lista de franquicias")
    @Operation(summary = "", description = "")
    @GetMapping(value = "/api/v1/franquicia")
    public ResponseEntity<List<Object>> list(){
        try {
            return service.list();
        } catch (Exception e) {
            return new  ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "EditFranchise", description = "editar franquicia")
    @Operation(summary = "", description = "")
    @PutMapping(value = "/api/v1/franquicia/{id}")
    public ResponseEntity<Object> edit(@PathVariable Integer id, @Valid @RequestBody FranchiseDTO request){
        try {
            return service.update(id, request);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "EditFranchise", description = "eliminar franquicia")
    @Operation(summary = "", description = "")
    @DeleteMapping(value = "/api/v1/franquicia/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }
}
