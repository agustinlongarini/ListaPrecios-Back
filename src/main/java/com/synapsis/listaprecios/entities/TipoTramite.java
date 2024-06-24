package com.synapsis.listaprecios.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_tramite")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoTramite{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codTipoTramite;

    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private LocalDate fechaBaja;

    @OneToMany(mappedBy = "tipoTramite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TipoTramiteListaPrecios> tipoTramiteListaPrecios = new HashSet<>();

}