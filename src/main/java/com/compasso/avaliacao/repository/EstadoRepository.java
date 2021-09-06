package com.compasso.avaliacao.repository;

import com.compasso.avaliacao.modelo.Estado;
import com.compasso.avaliacao.modelo.Regiao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

;

public interface EstadoRepository extends JpaRepository<Estado,Long> {

    Page<Estado> findByRegiao(Regiao regiao, Pageable paginacao);


}
