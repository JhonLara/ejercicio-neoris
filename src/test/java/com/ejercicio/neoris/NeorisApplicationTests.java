package com.ejercicio.neoris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ejercicio.neoris.entidades.Cuenta;
import com.ejercicio.neoris.servicio.impl.NeorisServicioImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
@WebAppConfiguration
public class NeorisApplicationTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private NeorisServicioImpl biblioteca;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void buscarCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setEstado(Boolean.TRUE);
		cuenta.setNumero(9959599L);
		cuenta.setSaldoActual(10000L);
		cuenta.setSaldoInicial(10000L);
		cuenta.setTipo("Retiro");
		biblioteca.crearCuentas(Arrays.asList(cuenta));

		Cuenta cuentaEncontrada = entityManager.find(Cuenta.class, 2L);

		assertTrue(cuentaEncontrada.getNumero() == cuenta.getNumero());
	}

	@Test
	public void deleteCuenta() throws Exception {
		Cuenta cuenta = new Cuenta();
		cuenta.setEstado(Boolean.TRUE);
		cuenta.setNumero(99599L);
		cuenta.setSaldoActual(100L);
		cuenta.setSaldoInicial(100L);
		cuenta.setTipo("Debito");
		entityManager.persist(cuenta);
		String uri = "/cuenta/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void crearCuenta() throws Exception {
		String uri = "/cuenta";
		Cuenta cuenta = new Cuenta();
		cuenta.setEstado(Boolean.TRUE);
		cuenta.setNumero(999L);
		cuenta.setSaldoActual(12200L);
		cuenta.setSaldoInicial(12200L);
		cuenta.setTipo("Debito");
		String inputJson = mapToJson(cuenta);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
}
