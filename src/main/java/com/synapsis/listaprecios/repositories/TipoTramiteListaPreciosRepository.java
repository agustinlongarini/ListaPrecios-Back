package com.synapsis.listaprecios.repositories;

import com.synapsis.listaprecios.entities.TipoTramiteListaPrecios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTramiteListaPreciosRepository extends JpaRepository<TipoTramiteListaPrecios, Long> {
}