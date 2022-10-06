package com.ejercicio.neoris.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.neoris.entidades.Cuenta;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {

}
