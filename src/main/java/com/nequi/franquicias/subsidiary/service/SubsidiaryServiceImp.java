package com.nequi.franquicias.subsidiary.service;

import com.nequi.franquicias.commos.dto.SubsidiaryDTO;

import com.nequi.franquicias.commos.entity.Subsidiary;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.commos.model.SubsidiaryUpdateRequest;
import com.nequi.franquicias.subsidiary.mapper.SubsidiaryMapper;
import com.nequi.franquicias.subsidiary.repository.SubsidiaryRepository;
import com.nequi.franquicias.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsidiaryServiceImp implements SubsidiaryService {

    @Autowired
    private SubsidiaryRepository repository;

    @Autowired
    private SubsidiaryMapper mapper;


    @Override
    public ResponseEntity<Object> create(SubsidiaryDTO request) {
        try{
            return new ResponseEntity<>(repository.save(mapper.getEntity(request)), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> update(Integer id, SubsidiaryUpdateRequest request) {
        Subsidiary entity = findById(id);;
        Utilities.actualizarSiCambio(request.getName(), entity::setName);
        Utilities.actualizarSiCambio(request.getFranchiseId(), entity::setFranchiseId);
        return ResponseEntity.ok(repository.save(entity));
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) {
        Subsidiary entity = findById(id);
        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }

    private Subsidiary findById(Integer id) {
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
}
