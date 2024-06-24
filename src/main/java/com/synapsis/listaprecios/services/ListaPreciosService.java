package com.synapsis.listaprecios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.synapsis.listaprecios.dtos.DTODetalleListaPrecios;
import com.synapsis.listaprecios.dtos.DTOIngresoDatos;
import com.synapsis.listaprecios.entities.ListaPrecios;
import com.synapsis.listaprecios.entities.TipoTramite;
import com.synapsis.listaprecios.entities.TipoTramiteListaPrecios;
import com.synapsis.listaprecios.repositories.ListaPreciosRepository;
import com.synapsis.listaprecios.repositories.TipoTramiteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ListaPreciosService {

    private final ListaPreciosRepository listaPreciosRepository;
    private final TipoTramiteRepository tipoTramiteRepository;

    @Autowired
    public ListaPreciosService(ListaPreciosRepository listaPreciosRepository, TipoTramiteRepository tipoTramiteRepository) {
        this.listaPreciosRepository = listaPreciosRepository;
        this.tipoTramiteRepository = tipoTramiteRepository;
    }

    @Transactional
    public void importarListaPrecios(DTOIngresoDatos dtoIngresoDatos) {

        // Encontrar la lista de precios más reciente por fechaDesde
        ListaPrecios listaPreciosUltima = null;
        List<ListaPrecios> todasListasPrecios = listaPreciosRepository.findByFechaBajaIsNull();
        for (ListaPrecios lista : todasListasPrecios) {
            LocalDate fechaDesde = lista.getFechaDesde();
            if (listaPreciosUltima == null || fechaDesde.isAfter(listaPreciosUltima.getFechaDesde())) {
                listaPreciosUltima = lista;
            }
        }

        LocalDate nuevaFechaDesde = dtoIngresoDatos.getNuevaFechaDesde();

        // Verificar que la nueva fechaDesde sea mayor a la fecha desde de la lista actual
        if (nuevaFechaDesde.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha desde de la nueva lista de precios debe ser mayor a la fecha actual.");
        }

        LocalDate fechaHasta = listaPreciosUltima.getFechaHasta();

        // Verificar si hay solapamiento y separacion de fechas
        if (nuevaFechaDesde.isAfter(fechaHasta) || nuevaFechaDesde.isBefore(fechaHasta)) {
            listaPreciosUltima.setFechaHasta(nuevaFechaDesde);
        }

        // Crear la nueva lista de precios
        ListaPrecios nuevaListaPrecios = new ListaPrecios();
        nuevaListaPrecios.setFechaDesde(nuevaFechaDesde);
        LocalDate nuevaFechaHasta = dtoIngresoDatos.getNuevaFechaHasta();
        nuevaListaPrecios.setFechaHasta(nuevaFechaHasta);

        // Obtener todos los tipos de trámites
        List<TipoTramite> tiposTramite = tipoTramiteRepository.findAll();

        // Procesar cada tipo de trámite
        for (TipoTramite tipoTramite : tiposTramite) {
            // Crear TipoTramiteListaPrecios con precio inicial 0
            TipoTramiteListaPrecios nuevoTipoTramiteListaPrecios = new TipoTramiteListaPrecios();
            nuevoTipoTramiteListaPrecios.setListaPrecios(nuevaListaPrecios);
            nuevoTipoTramiteListaPrecios.setTipoTramite(tipoTramite);
            nuevoTipoTramiteListaPrecios.setPrecioTipoTramite(0.0);

            List<DTODetalleListaPrecios> detalles = dtoIngresoDatos.getDetalles();
            int codTipoTramite = tipoTramite.getCodTipoTramite();
            boolean encontrado = false;

            for (DTODetalleListaPrecios detalle : detalles) {
                int codigoTT = detalle.getCodTipoTramite();
                if (codigoTT == codTipoTramite) {
                    Double precioTipoTramite = detalle.getPrecioTipoTramite();
                    nuevoTipoTramiteListaPrecios.setPrecioTipoTramite(precioTipoTramite);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                //No se encontró el detalle, obtener el precio del tipo de trámite de la lista de precios anterior
                List<TipoTramiteListaPrecios> detallesUltimaLista = listaPreciosUltima.getTipoTramiteListaPrecios();
                for (TipoTramiteListaPrecios detalleUltimaLista : detallesUltimaLista) {
                    TipoTramite tipoTramiteUltimaLista = detalleUltimaLista.getTipoTramite();
                    int codTipoTramiteUltimo = tipoTramiteUltimaLista.getCodTipoTramite();
                    if (codTipoTramiteUltimo == codTipoTramite) {
                        Double precioTipoTramiteUltimo = detalleUltimaLista.getPrecioTipoTramite();
                        nuevoTipoTramiteListaPrecios.setPrecioTipoTramite(precioTipoTramiteUltimo);
                        break;
                    }
                }
            }

            //Agregar TipoTramiteListaPrecios a la nueva lista de precios
            nuevaListaPrecios.addTipoTramiteListaPrecios(nuevoTipoTramiteListaPrecios);
        }

        //Guardar la nueva lista de precios en el repositorio
        listaPreciosRepository.save(nuevaListaPrecios);
    }

}


