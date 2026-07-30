package com.ativosti.repository;

import com.ativosti.model.EstoqueInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueInsumoRepository extends JpaRepository<EstoqueInsumo, Long> {
    Optional<EstoqueInsumo> findByNomeItem(String nomeItem);
    List<EstoqueInsumo> findByOrdemCompraId(Long ordemCompraId);
}