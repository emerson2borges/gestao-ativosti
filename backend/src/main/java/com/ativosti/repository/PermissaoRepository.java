package com.ativosti.repository;

import com.ativosti.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Optional<Permissao> findByChave(String chave);
}
