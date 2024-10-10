package com.nequi.franquicias.subsidiary.repository;

import com.nequi.franquicias.commos.entity.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Integer>, JpaSpecificationExecutor<Subsidiary> {
}
