package isi.died.app.ejemplo.estructuras;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class GrafoTest {
	Grafo<String> g1;
	Grafo<String> gPesosNegativos;	
	Grafo<String> gSinPesos;
	
	@Before
	public void armarGrafo() {
		g1 = new Grafo<String>();
		gPesosNegativos = new Grafo<String>();
		gSinPesos = new Grafo<String>();
		g1.addNodo("A");
		g1.addNodo("B");
		g1.addNodo("C");
		g1.addNodo("D");
		g1.addNodo("E");

		gSinPesos.addNodo("A");
		gSinPesos.addNodo("B");
		gSinPesos.addNodo("C");
		gSinPesos.addNodo("D");
		gSinPesos.addNodo("E");
		

		gPesosNegativos.addNodo("A");
		gPesosNegativos.addNodo("B");
		gPesosNegativos.addNodo("C");
		gPesosNegativos.addNodo("D");
		gPesosNegativos.addNodo("E");
		
		g1.conectar("A", "B",2);
		g1.conectar("A", "D",6);
		g1.conectar("A", "C",8);
		g1.conectar("B", "D",1);
		g1.conectar("D", "C",2);
		g1.conectar("C", "E",2);
		g1.conectar("D", "E",5);
		
		gSinPesos.conectar("A", "B");
		gSinPesos.conectar("A", "D");
		gSinPesos.conectar("A", "C");
		gSinPesos.conectar("B", "D");
		gSinPesos.conectar("D", "C");
		gSinPesos.conectar("C", "E");
		gSinPesos.conectar("D", "E");
		
		gPesosNegativos.conectar("A", "B",2);
		gPesosNegativos.conectar("A", "D",6);
		gPesosNegativos.conectar("A", "C",8);
		gPesosNegativos.conectar("B", "D",1);
		gPesosNegativos.conectar("D", "C",2);
		gPesosNegativos.conectar("C", "E",-6);
		gPesosNegativos.conectar("D", "E",5);
		
	}
	
	@Ignore
	public void testRecorridoAnchura() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testRecorridoProfundidad() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testRecorridoTopologico() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCaminosTT() {
		fail("Not yet implemented");
	}

	@Test
	public void testCaminosMinimoDikstra() {
		Map<String, Integer> distancias = g1.caminosMinimoDikstra("A");
		Integer distanciaA = 0;
		Integer distanciaB = 2;
		Integer distanciaC = 5;
		Integer distanciaD = 3;
		Integer distanciaE = 7;
		assertEquals(distancias.get("A"), distanciaA);
		assertEquals(distancias.get("B"), distanciaB);
		assertEquals(distancias.get("C"), distanciaC);
		assertEquals(distancias.get("D"), distanciaD);
		assertEquals(distancias.get("E"), distanciaE);
		
		Map<String, Integer> distanciasSinPeso = gSinPesos.caminosMinimoDikstra("A");
	}
	
	@Test
	public void floydWarshall() {
		g1.floydWarshall();
		gPesosNegativos.floydWarshall();
		gSinPesos.floydWarshall();
	}

}
