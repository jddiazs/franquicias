package com.nequi.franquicias.franchise.service;

import com.nequi.franquicias.commos.dto.FranchiseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FranchiseService {

    ResponseEntity<Object> create(FranchiseDTO request);
    ResponseEntity<Object> update(Integer id, FranchiseDTO request);
    ResponseEntity<Object> delete(Integer id);
    ResponseEntity<List<Object>> list();

}
