package com.ejercicio.neoris.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.neoris.dto.ReporteDTO;
import com.ejercicio.neoris.entidades.Cliente;
import com.ejercicio.neoris.entidades.Cuenta;
import com.ejercicio.neoris.entidades.Movimiento;
import com.ejercicio.neoris.servicio.NeorisServicio;

@RestController
public class NeorisControlador {
	@Autowired
	private NeorisServicio neorisServicio;

	@PostMapping("/cuenta")
	public void agregarCuenta(@RequestBody List<Cuenta> cuentas) {
		neorisServicio.crearCuentas(cuentas);
	}

	@DeleteMapping("/cuenta/{CUENTA}")
	public void eliminarCuenta(@PathVariable(name = "CUENTA") Long idCuenta) {
		neorisServicio.eliminarCuenta(idCuenta);
	}

	@GetMapping("/cuentas")
	public List<Cuenta> obtenerCuentas() {
		return neorisServicio.obtenerCuentas();
	}

	@PostMapping("/movimiento")
	public ResponseEntity<String> agregarMovimiento(@RequestBody List<Movimiento> movimientos) {
		try {
			neorisServicio.crearMovimientos(movimientos);
			return new ResponseEntity<String>("Movimiento registrado con exito", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/movimiento/{MOVIMIENTO}")
	public void eliminarMovimiento(@PathVariable(name = "MOVIMIENTO") Long idMovimiento) {
		neorisServicio.eliminarMovimiento(idMovimiento);
	}

	@GetMapping("/movimientos")
	public List<Movimiento> obtenerMovimientos() {
		return neorisServicio.obtenerMovimientos();
	}

	@PostMapping("/cliente")
	public void agregarCliente(@RequestBody List<Cliente> clientes) {
		neorisServicio.crearClientes(clientes);
	}

	@DeleteMapping("/cliente/{CLIENTE}")
	public void eliminarCliente(@PathVariable(name = "CLIENTE") Long idCliente) {
		neorisServicio.eliminarCliente(idCliente);
	}

	@GetMapping("/clientes")
	public List<Cliente> obtenerCliente() {
		return neorisServicio.obtenerClientes();
	}

	@GetMapping("/reportes")
	public @ResponseBody List<ReporteDTO> obtenerReporte(
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin,
			@RequestParam("idCliente") Long idCliente) {
		return neorisServicio.obtenerReporte(fechaInicio, fechaFin, idCliente);
	}
}
