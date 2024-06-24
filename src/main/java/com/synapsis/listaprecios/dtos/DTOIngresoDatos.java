package com.synapsis.listaprecios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOIngresoDatos {
    private LocalDate nuevaFechaDesde;
    private LocalDate nuevaFechaHasta;
    private List<DTODetalleListaPrecios> detalles;

}
