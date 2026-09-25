package com.ativosti.repository;

import com.ativosti.model.HistoricoAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoAtivoRepository extends JpaRepository<HistoricoAtivo, Long> {
    List<HistoricoAtivo> findByAtivoIdOrderByDataAlteracaoDesc(Long ativoId);
}