package com.synapsis.listaprecios.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_tramite_lista_precios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoTramiteListaPrecios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "codListaPrecios")
    private ListaPrecios listaPrecios;

    @ManyToOne
    @JoinColumn(name = "codTipoTramite")
    private TipoTramite tipoTramite;

    private Double precioTipoTramite;

}
