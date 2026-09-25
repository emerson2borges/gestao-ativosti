package com.ativosti.repository;

import com.ativosti.model.OrdemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrdemCompraRepository extends JpaRepository<OrdemCompra, Long> {
    Optional<OrdemCompra> findByNumeroOc(String numeroOc);
}