package com.nequi.franquicias.franchise.repository;

import com.nequi.franquicias.commos.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer>, JpaSpecificationExecutor<Franchise> {
}
