package com.synapsis.listaprecios.repositories;

import com.synapsis.listaprecios.entities.ListaPrecios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaPreciosRepository extends JpaRepository<ListaPrecios, Long> {
    List<ListaPrecios> findByFechaBajaIsNull();
    ListaPrecios findByCodListaPrecios(int codListaPrecios);
}
