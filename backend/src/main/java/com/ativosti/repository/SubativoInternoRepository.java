package com.ativosti.repository;

import com.ativosti.model.SubativoInterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubativoInternoRepository extends JpaRepository<SubativoInterno, Long> {
    List<SubativoInterno> findByAtivoId(Long ativoId);
}