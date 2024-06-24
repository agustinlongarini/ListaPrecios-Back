package com.synapsis.listaprecios.repositories;

import com.synapsis.listaprecios.entities.TipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTramiteRepository extends JpaRepository<TipoTramite, Long> {
}