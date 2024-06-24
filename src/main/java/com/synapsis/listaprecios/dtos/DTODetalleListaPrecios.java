package com.synapsis.listaprecios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTODetalleListaPrecios {

    private int codTipoTramite;
    private Double precioTipoTramite;

}
