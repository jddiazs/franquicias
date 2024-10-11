package com.nequi.franquicias.product.controller;

import com.nequi.franquicias.commos.dto.ProductDTO;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.commos.model.ProductUpdateRequest;
import com.nequi.franquicias.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "CreateProduct", description = "crear producto")
    @Operation(summary = "", description = "")
    @PostMapping(value = "/api/v1/producto")
    public ResponseEntity<Object> create(@Valid @RequestBody ProductDTO request){
        return service.create(request);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "ListProducts", description = "lista de productos")
    @Operation(summary = "", description = "")
    @GetMapping(value = "/api/v1/producto")
    public ResponseEntity<List<Object>> list(){
        try {
            return service.list();
        } catch (Exception e) {
            return new  ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "ListProductsMaxStock", description = "lista de productos con maximo stock")
    @Operation(summary = "", description = "")
    @GetMapping(value = "/api/v1/producto/franquicia/{id}")
    public ResponseEntity<List<Object>> listProductByFranchies(@PathVariable Integer id){
        try {
            return service.listMaxStockByFranchise(id);
        } catch (Exception e) {
            return new  ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "EditProduct", description = "editar producto")
    @Operation(summary = "", description = "")
    @PutMapping(value = "/api/v1/producto/{id}")
    public ResponseEntity<Object> edit(@PathVariable Integer id, @Valid @RequestBody ProductUpdateRequest request){
        try {
            return service.update(id, request);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Tag(name = "deleteProduct", description = "eliminar producto")
    @Operation(summary = "", description = "")
    @DeleteMapping(value = "/api/v1/producto/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            return new ResponseEntity<Object>(e, e.getCode());
        }
    }
}
