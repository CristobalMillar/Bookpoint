package com.bookpoint.catalog.repository;

import org.springframework.stereotype.Repository;
import com.bookpoint.catalog.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
