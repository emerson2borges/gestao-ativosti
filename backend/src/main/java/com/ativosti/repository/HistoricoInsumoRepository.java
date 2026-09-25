package com.ativosti.repository;

import com.ativosti.model.HistoricoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoInsumoRepository extends JpaRepository<HistoricoInsumo, Long> {
    List<HistoricoInsumo> findByInsumoId(Long insumoId);
    List<HistoricoInsumo> findByInsumoIdOrderByDataMovimentacaoDesc(Long insumoId);
}