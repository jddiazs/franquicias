package com.nequi.franquicias.subsidiary.service;

import com.nequi.franquicias.commos.dto.SubsidiaryDTO;
import com.nequi.franquicias.commos.model.SubsidiaryUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubsidiaryService {
    ResponseEntity<Object> create(SubsidiaryDTO request);
    ResponseEntity<Object> update(Integer id, SubsidiaryUpdateRequest request);
    ResponseEntity<Object> delete(Integer id);
    ResponseEntity<List<Object>> list();
}
