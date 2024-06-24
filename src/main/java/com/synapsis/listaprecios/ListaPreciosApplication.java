package com.synapsis.listaprecios;

import com.synapsis.listaprecios.dtos.DTODetalleListaPrecios;
import com.synapsis.listaprecios.dtos.DTOIngresoDatos;
import com.synapsis.listaprecios.entities.ListaPrecios;
import com.synapsis.listaprecios.entities.TipoTramite;
import com.synapsis.listaprecios.entities.TipoTramiteListaPrecios;
import com.synapsis.listaprecios.repositories.ListaPreciosRepository;
import com.synapsis.listaprecios.repositories.TipoTramiteListaPreciosRepository;
import com.synapsis.listaprecios.repositories.TipoTramiteRepository;
import com.synapsis.listaprecios.services.ListaPreciosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ListaPreciosApplication {

	private final ListaPreciosRepository listaPreciosRepository;
	private final TipoTramiteRepository tipoTramiteRepository;
	private final TipoTramiteListaPreciosRepository tipoTramiteListaPreciosRepository;

	private final ListaPreciosService listaPreciosService;

	public ListaPreciosApplication(ListaPreciosRepository listaPreciosRepository,
								   TipoTramiteRepository tipoTramiteRepository,
								   TipoTramiteListaPreciosRepository tipoTramiteListaPreciosRepository,
								   ListaPreciosService listaPreciosService) {
		this.listaPreciosRepository = listaPreciosRepository;
		this.tipoTramiteRepository = tipoTramiteRepository;
		this.tipoTramiteListaPreciosRepository = tipoTramiteListaPreciosRepository;
		this.listaPreciosService = listaPreciosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ListaPreciosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return args -> {
			// Crear ejemplos de ListaPrecios
			ListaPrecios listaPrecios1 = new ListaPrecios();
			listaPrecios1.setFechaDesde(LocalDate.of(2024, 6, 22));
			listaPrecios1.setFechaHasta(LocalDate.of(2024, 7, 22));
			listaPreciosRepository.save(listaPrecios1);

			// Crear ejemplos de TipoTramite
			TipoTramite tipoTramite = new TipoTramite();
			tipoTramite.setNombreTipoTramite("Renovación de Licencia de Conducir");
			tipoTramite.setDescripcionTipoTramite("Trámite para renovar la licencia de conducir");
			tipoTramiteRepository.save(tipoTramite);

			TipoTramite tipoTramite2 = new TipoTramite();
			tipoTramite2.setNombreTipoTramite("Matrícula Escolar");
			tipoTramite2.setDescripcionTipoTramite("Trámite para matricular a un estudiante en una escuela");
			tipoTramiteRepository.save(tipoTramite2);

			TipoTramite tipoTramite3 = new TipoTramite();
			tipoTramite3.setNombreTipoTramite("Inscripción Laboral");
			tipoTramite3.setDescripcionTipoTramite("Trámite para inscribirse como trabajador en una empresa");
			tipoTramiteRepository.save(tipoTramite3);

			// Crear ejemplos de TipoTramiteListaPrecios
			TipoTramiteListaPrecios tipoTramiteListaPrecios1 = new TipoTramiteListaPrecios();
			tipoTramiteListaPrecios1.setListaPrecios(listaPrecios1);
			tipoTramiteListaPrecios1.setTipoTramite(tipoTramite);
			tipoTramiteListaPrecios1.setPrecioTipoTramite(100.0);
			tipoTramiteListaPreciosRepository.save(tipoTramiteListaPrecios1);

			TipoTramiteListaPrecios tipoTramiteListaPrecios2 = new TipoTramiteListaPrecios();
			tipoTramiteListaPrecios2.setListaPrecios(listaPrecios1);
			tipoTramiteListaPrecios2.setTipoTramite(tipoTramite2);
			tipoTramiteListaPrecios2.setPrecioTipoTramite(120.0);
			tipoTramiteListaPreciosRepository.save(tipoTramiteListaPrecios2);

			TipoTramiteListaPrecios tipoTramiteListaPrecios3 = new TipoTramiteListaPrecios();
			tipoTramiteListaPrecios3.setListaPrecios(listaPrecios1);
			tipoTramiteListaPrecios3.setTipoTramite(tipoTramite3);
			tipoTramiteListaPrecios3.setPrecioTipoTramite(85.0);
			tipoTramiteListaPreciosRepository.save(tipoTramiteListaPrecios3);

			// Crear DTOIngresoDatos con datos simulados para probar
			DTOIngresoDatos dtoIngresoDatos = new DTOIngresoDatos();
			dtoIngresoDatos.setNuevaFechaDesde(LocalDate.of(2024, 7, 15)); // Ejemplo de fecha nueva
			dtoIngresoDatos.setNuevaFechaHasta(LocalDate.of(2024, 8, 15)); // Ejemplo de fecha nueva
			List<DTODetalleListaPrecios> detalles = new ArrayList<>();
			DTODetalleListaPrecios detalle1 = new DTODetalleListaPrecios();
			detalle1.setCodTipoTramite(1);
			detalle1.setPrecioTipoTramite(200.0);
			detalles.add(detalle1);
			DTODetalleListaPrecios detalle2 = new DTODetalleListaPrecios();
			detalle2.setCodTipoTramite(2);
			detalle2.setPrecioTipoTramite(175.0);
			detalles.add(detalle2);
			dtoIngresoDatos.setDetalles(detalles);

			DTOIngresoDatos dtoIngresoDatos2 = new DTOIngresoDatos();
			dtoIngresoDatos2.setNuevaFechaDesde(LocalDate.of(2024, 8, 30)); // Ejemplo de fecha nueva
			dtoIngresoDatos2.setNuevaFechaHasta(LocalDate.of(2024, 9, 30)); // Ejemplo de fecha nueva
			List<DTODetalleListaPrecios> detalles2 = new ArrayList<>();
			DTODetalleListaPrecios detalle3 = new DTODetalleListaPrecios();
			detalle3.setCodTipoTramite(3);
			detalle3.setPrecioTipoTramite(100.0);
			detalles2.add(detalle3);
			dtoIngresoDatos2.setDetalles(detalles2);

			// Ejecutar el método de importación al inicio de la aplicación
			listaPreciosService.importarListaPrecios(dtoIngresoDatos);
			listaPreciosService.importarListaPrecios(dtoIngresoDatos2);
		};
	}
}

