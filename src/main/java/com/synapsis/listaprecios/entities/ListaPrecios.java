package com.synapsis.listaprecios.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista_precios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaPrecios{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codListaPrecios;

    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalDate fechaBaja;

    @OneToMany(mappedBy = "listaPrecios", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoTramiteListaPrecios> tipoTramiteListaPrecios = new ArrayList<>();

    public void addTipoTramiteListaPrecios(TipoTramiteListaPrecios tipoTramiteListaPrecios) {
        this.tipoTramiteListaPrecios.add(tipoTramiteListaPrecios);
        tipoTramiteListaPrecios.setListaPrecios(this);
    }

}
