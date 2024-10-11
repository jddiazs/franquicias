package com.nequi.franquicias.product.service;

import com.nequi.franquicias.commos.dto.ProductDTO;
import com.nequi.franquicias.commos.dto.ProductoMaxStockDTO;
import com.nequi.franquicias.commos.entity.Product;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.commos.model.ProductUpdateRequest;
import com.nequi.franquicias.product.mapper.ProductMapper;
import com.nequi.franquicias.product.repository.ProductRepository;
import com.nequi.franquicias.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp  implements  ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;


    @Override
    public ResponseEntity<Object> create(ProductDTO request) {
        try{
            return new ResponseEntity<>(repository.save(mapper.getEntity(request)), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> update(Integer id, ProductUpdateRequest request) {
        Product entity = findById(id);;
        Utilities.actualizarSiCambio(request.getName(), entity::setName);
        Utilities.actualizarSiCambio(request.getStock(), entity::setStock);
        Utilities.actualizarSiCambio(request.getSubsidiaryId(), entity::setSubsidiaryId);
        return ResponseEntity.ok(repository.save(entity));
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) {
        Product entity = findById(id);
        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }

    private Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> BusinessException
                .builder()
                .message("Objeto no encontrado")
                .code(HttpStatus.NOT_FOUND)
                .build());
    }

    @Override
    public ResponseEntity<List<Object>> list() {
        ResponseEntity list = ResponseEntity.ok(repository.findAll());
        return list;
    }

    @Override
    public ResponseEntity<List<Object>> listMaxStockByFranchise(Integer id) {
            List listProduct =  repository.findMaxStockByFranchise(id).orElseThrow(() -> BusinessException
                    .builder()
                    .message("Sin datos")
                    .code(HttpStatus.NOT_FOUND)
                    .build());
            return ResponseEntity.ok(listProduct);
    }
}
