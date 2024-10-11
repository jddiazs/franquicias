package com.nequi.franquicias.product.service;

import com.nequi.franquicias.commos.dto.ProductDTO;
import com.nequi.franquicias.commos.model.ProductUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Object> create(ProductDTO request);
    ResponseEntity<Object> update(Integer id, ProductUpdateRequest request);
    ResponseEntity<Object> delete(Integer id);
    ResponseEntity<List<Object>> list();
    ResponseEntity<List<Object>> listMaxStockByFranchise(Integer id);
}
