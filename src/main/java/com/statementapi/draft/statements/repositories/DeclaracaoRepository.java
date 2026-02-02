package com.statementapi.draft.statements.repositories;

import com.statementapi.draft.statements.entities.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclaracaoRepository extends JpaRepository<Declaracao, Long> {
}


