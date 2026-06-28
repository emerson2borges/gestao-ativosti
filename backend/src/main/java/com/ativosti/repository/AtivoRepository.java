package com.ativosti.repository;

import com.ativosti.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    List<Ativo> findByStatus(String status);
    List<Ativo> findByLocalizacaoId(Long localizacaoId);
    List<Ativo> findByTipoId(Long tipoId);
}