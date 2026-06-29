package com.ativosti.repository;

import com.ativosti.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    List<Localizacao> findByCidadeId(Long cidadeId);
    Optional<Localizacao> findByCidadeIdAndTipoPontoAndNomePonto(Long cidadeId, String tipoPonto, String nomePonto);

}