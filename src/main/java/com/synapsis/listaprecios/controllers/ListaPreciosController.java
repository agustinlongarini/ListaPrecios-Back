package com.synapsis.listaprecios.controllers;

import com.synapsis.listaprecios.entities.ListaPrecios;
import com.synapsis.listaprecios.repositories.ListaPreciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ListaPreciosController {

    @Autowired
    private ListaPreciosRepository listaPreciosRepository;

    @GetMapping("/listasPrecios")
    public String getAllListasPrecios(Model model) {
        List<ListaPrecios> listasPrecios = listaPreciosRepository.findAll();
        model.addAttribute("listasPrecios", listasPrecios);
        return "listasPrecios";
    }

    @GetMapping("/listasPrecios/{codListaPrecios}/detalles")
    public String getListaPreciosDetalles(@PathVariable("codListaPrecios") int codListaPrecios, Model model) {
        ListaPrecios listaPrecios = listaPreciosRepository.findByCodListaPrecios(codListaPrecios);
        model.addAttribute("tipoTramiteListaPrecios", listaPrecios.getTipoTramiteListaPrecios());
        return "detallesListaPrecios";
    }
}

