package com.ejercicio.neoris.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.neoris.dto.ReporteDTO;
import com.ejercicio.neoris.entidades.Cliente;
import com.ejercicio.neoris.entidades.Cuenta;
import com.ejercicio.neoris.entidades.Movimiento;
import com.ejercicio.neoris.repositorio.ClienteRepositorio;
import com.ejercicio.neoris.repositorio.CuentaRepositorio;
import com.ejercicio.neoris.repositorio.MovimientoRepositorio;
import com.ejercicio.neoris.servicio.NeorisServicio;

@Service
public class NeorisServicioImpl implements NeorisServicio {

	@Autowired
	private CuentaRepositorio cuentaRepositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private MovimientoRepositorio movimientoRepositorio;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void eliminarCuenta(Long idCuenta) {
		cuentaRepositorio.deleteById(idCuenta);
	}

	@Override
	public void eliminarMovimiento(Long idMovimiento) {
		movimientoRepositorio.deleteById(idMovimiento);
	}

	@Override
	public void eliminarCliente(Long idPersona) {
		clienteRepositorio.deleteById(idPersona);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		return clienteRepositorio.findAll();
	}

	@Override
	public List<Cuenta> obtenerCuentas() {
		return cuentaRepositorio.findAll();
	}

	@Override
	public List<Movimiento> obtenerMovimientos() {
		return movimientoRepositorio.findAll();
	}

	@Override
	public void crearCuentas(List<Cuenta> cuentas) {
		cuentas.forEach((cuenta) -> {
			if (cuenta.getId() != null) {
				Optional<Cuenta> optCuenta = cuentaRepositorio.findById(cuenta.getId());
				Cuenta cuentaActualizar = optCuenta.get();
				cuentaRepositorio.save(cuentaActualizar);
			} else {
				cuentaRepositorio.save(cuenta);
			}
		});
	}

	@Override
	public void crearMovimientos(List<Movimiento> movimientos) throws Exception{
		movimientos.forEach((movimiento) -> {
			if (movimiento.getId() != null) {
				Optional<Movimiento> optMovimiento = movimientoRepositorio.findById(movimiento.getId());
				Movimiento movimientoActualizar = optMovimiento.get();
				movimientoRepositorio.save(movimientoActualizar);
			} else {
				Cuenta cuenta = cuentaRepositorio.findById(movimiento.getCuenta().getId()).get();
				movimiento.setTipo(movimiento.getValor() < 0 ? "Retiro" : "Deposito");
				Long operacion = cuenta.getSaldoActual() == null ? cuenta.getSaldoInicial()
						: cuenta.getSaldoActual() + movimiento.getValor();
				if (movimiento.getTipo().equals("Retiro") && (cuenta.getSaldoInicial() == 0 || operacion < 0)) {
					cuenta.setSaldoActual(cuenta.getSaldoInicial());
					throw new RuntimeException("Saldo no disponible");
				} else {
					movimiento.setSaldo(operacion);
					cuenta.setSaldoActual(operacion);
					movimiento.setFecha(LocalDate.now());
					cuentaRepositorio.save(cuenta);
					movimientoRepositorio.save(movimiento);
				}
			}
		});
	}

	@Override
	public void crearClientes(List<Cliente> clientes) {
		clientes.forEach((cliente) -> {
			if (cliente.getId() != null) {
				Optional<Cliente> optCliente = clienteRepositorio.findById(cliente.getId());
				Cliente clienteActualizar = optCliente.get();
				clienteRepositorio.save(clienteActualizar);
			} else {
				clienteRepositorio.save(cliente);
			}
		});
	}

	@Override
	public List<ReporteDTO> obtenerReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
		List<ReporteDTO> reporte = new ArrayList<>();
		List<Movimiento> movimientos = movimientoRepositorio.findAll();
		movimientos.stream().filter(m -> m.getFecha().isBefore(fechaFin) && m.getFecha().isAfter(fechaInicio)
				&& m.getCuenta().getCliente().getId() == idCliente).forEach((movimiento) -> {
					System.out.println("entra " + movimiento.toString());
					ReporteDTO reporteDTO = new ReporteDTO(
							movimiento.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
							movimiento.getCuenta().getCliente().getNombre(),
							movimiento.getCuenta().getNumero().toString(), movimiento.getTipo(),
							movimiento.getCuenta().getSaldoInicial(), movimiento.getCuenta().getEstado(),
							movimiento.getValor(),
							movimiento.getCuenta().getSaldoActual() == null ? movimiento.getCuenta().getSaldoInicial()
									: movimiento.getCuenta().getSaldoActual());
					System.out.println("reporte " + reporteDTO.toString());

					reporte.add(reporteDTO);
				});
		System.out.println("reporte tamaño " + reporte.size());

		return reporte;
	}

}
