package com.ejercicio.neoris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ejercicio.neoris.entidades.Cuenta;
import com.ejercicio.neoris.servicio.impl.NeorisServicioImpl;

@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
public class NeorisApplicationTests {

	@Autowired
	private NeorisServicioImpl biblioteca;

	@Test
	public void buscarLibro() {
		assertTrue(Boolean.TRUE);
	}
	
	

}
