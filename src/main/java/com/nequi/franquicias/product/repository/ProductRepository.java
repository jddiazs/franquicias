package com.nequi.franquicias.product.repository;

import com.nequi.franquicias.commos.dto.ProductoMaxStockDTO;
import com.nequi.franquicias.commos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p.nombre as producto, s.nombre, MAX(p.stock) " +
            "FROM productos p " +
            "join sucursales s ON (p.id_sucursal = s.id) " +
            "WHERE s.id_franquicia = :idFranchise " +
            "AND p.stock = (" +
                 "SELECT MAX(p2.stock) "+
                 "FROM productos p2 " +
                 "WHERE p2.id_sucursal = p.id_sucursal )" +
            "GROUP BY s.id, p.nombre " +
            "ORDER BY s.id", nativeQuery = true)
    Optional<List<Object>> findMaxStockByFranchise(@Param("idFranchise") Integer id);
}
