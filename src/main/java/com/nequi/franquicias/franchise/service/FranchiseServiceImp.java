package com.nequi.franquicias.franchise.service;

import com.nequi.franquicias.commos.dto.FranchiseDTO;
import com.nequi.franquicias.commos.entity.Franchise;
import com.nequi.franquicias.commos.model.BusinessException;
import com.nequi.franquicias.franchise.mapper.FranchiseMapper;
import com.nequi.franquicias.franchise.repository.FranchiseRepository;
import com.nequi.franquicias.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseServiceImp implements FranchiseService{

    @Autowired
    private FranchiseRepository repository;

    @Autowired
    private FranchiseMapper mapper;


    @Override
    public ResponseEntity<Object> create(FranchiseDTO request) {
        try{
            return new ResponseEntity<>(repository.save(mapper.getEntity(request)), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> update(Integer id, FranchiseDTO request) {
        Franchise entity = findById(id);;
        Utilities.actualizarSiCambio(request.getName(), entity::setName);
        return ResponseEntity.ok(repository.save(entity));
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) {
        Franchise entity = findById(id);
        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }

    private Franchise findById(Integer id) {
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
