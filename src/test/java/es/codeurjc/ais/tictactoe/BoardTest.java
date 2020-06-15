package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	private Board board;
	private String jugador1;
	private String jugador2;
	
	public void iniciarJugadores(String j1, String j2) {
		this.jugador1 = j1 = "x";
		this.jugador2 = j2 = "o";
	}
	
	public int [] obtenerCeldas(String jugador) {
		return board.getCellsIfWinner(jugador);
	}
	
	@BeforeEach
	public void setUp() {
		board = new Board();
	}
	@DisplayName ("Se comprueba que gane el jugador 1")
	@Test
	public void ganaJugador1() {
		//inicializo los jugadores
		iniciarJugadores(jugador1, jugador2);
		board.getCell(0).value = jugador1; //j1
		board.getCell(1).value = jugador2; //j2
		board.getCell(3).value = jugador1; //j1
		board.getCell(2).value = jugador2; //j2
		board.getCell(6).value = jugador1; //j1
		board.getCell(7).value = jugador2; //j2
		
		//obtengo las celdas
		int [] celdasJ1 = obtenerCeldas(jugador1);
		int [] celdasJ2 = obtenerCeldas(jugador2);
		
		//asserts
		assertNotNull(celdasJ1); //gana jugador1
		assertNull(celdasJ2);
	}
	@DisplayName ("Se comprueba que gane el jugador 2")
	@Test
	public void ganaJugador2() {
		
		iniciarJugadores(jugador1, jugador2);
		board.getCell(1).value = jugador1; //j1
		board.getCell(2).value = jugador2; //j2
		board.getCell(6).value = jugador1; //j1
		board.getCell(5).value = jugador2; //j2
		board.getCell(7).value = jugador1; //j1
		board.getCell(8).value = jugador2; //j2
		
		//obtengo las celdas
		int [] celdasJ1 = obtenerCeldas(jugador1);
		int [] celdasJ2 = obtenerCeldas(jugador2);
		
		//asserts
		assertNotNull(celdasJ2); //gana jugador2
		assertNull(celdasJ1);
	}
	@DisplayName ("Se comprueba que empaten ambos jugadores")
	@Test
	public void empatanJugadores() {
		
		iniciarJugadores(jugador1, jugador2);
		
		int[] posicionesJ1 = {0, 3, 7, 2, 8};
		int[] posicionesJ2 = {6, 4, 1, 5};		
		
		for (int pos : posicionesJ1) {
			board.getCell(pos).value = jugador1;	
		}
		for (int pos : posicionesJ2) {
			board.getCell(pos).value = jugador2;	
		}
		
		assertTrue(board.checkDraw());
	}
}
