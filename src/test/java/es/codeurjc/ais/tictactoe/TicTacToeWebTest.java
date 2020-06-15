package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TicTacToeWebTest {
	
	private Player jugador1 = new Player(1, "x", "Jugador 1");
	private Player jugador2 = new Player(2, "o", "Jugador 2");
	private WebDriver navegador1;
	private WebDriver navegador2;
	 
	private List<Player> arrayJugadores = new CopyOnWriteArrayList<>();
	
	public void inicializarNavegadores(Player j1, Player j2, WebDriver n1, WebDriver n2, List<Player> jugadores) {
		jugadores.add(j1);
		jugadores.add(j2);

		n1.get("http://localhost:8080");
		n2.get("http://localhost:8080");
		 
		WebElement nombreN1 = n1.findElement(By.id("nickname"));
		nombreN1.sendKeys("Jugador1");
		WebElement boton1 = n1.findElement(By.id("startBtn"));
		boton1.click();
		 
		WebElement nombreN2 = n2.findElement(By.id("nickname"));
		nombreN2.sendKeys("Jugador2");
		WebElement boton2 = n2.findElement(By.id("startBtn"));
		boton2.click();
	}
	
	public String[] analizaAlert(WebDriver n1, WebDriver n2) {
		 //Esperamos a la salida del alert
		 WebDriverWait wait1 = new WebDriverWait(navegador1, 120); // seconds
		 wait1.until(ExpectedConditions.alertIsPresent());
		 
		 //cojo el texto del alert y lo troceo
		 String linea = navegador1.switchTo().alert().getText();
		 String [] separador = linea.split(" ");
		 String jug[] = new String[separador.length];
		 //recorro los trozos y los guardo en otro array
		 for(int i=0; i< separador.length; i++) {
			 jug[i] = separador[i];
		 }
		 
		 return jug;
	}
	
	 @BeforeAll
	 public static void setupClass() {
		 WebDriverManager.chromedriver().setup();
		 WebApp.start();
		 
		 
	 }
	 
	 @BeforeEach
	 public void setupTest() {
		 navegador1 = new ChromeDriver();
		 navegador2 = new ChromeDriver();
	 }
	 
	 @AfterEach
	 public void teardown() {
		 if (navegador1 != null) {
			 navegador1.quit();
		 }
		 if (navegador2 != null) {
			 navegador2.quit();
		 }
	 }
	 
	 @Test
	 public void testGanaJugador1() {
		 inicializarNavegadores(jugador1, jugador2, navegador1, navegador2, arrayJugadores);
		 /*marca la celda*/
		 //simulo una partida para el jugador1
		 WebElement Xcelda0 = navegador1.findElement(By.id("cell-"+0)); //j1
		 Xcelda0.click();
		 WebElement Ocelda3 = navegador2.findElement(By.id("cell-"+3)); //j2
		 Ocelda3.click();
		 WebElement Xcelda1 = navegador1.findElement(By.id("cell-"+1)); //j1
		 Xcelda1.click();
		 WebElement Ocelda4 = navegador2.findElement(By.id("cell-"+4)); //j2
		 Ocelda4.click();
		 WebElement Xcelda2 = navegador1.findElement(By.id("cell-"+2)); //j1 gana la partida
		 Xcelda2.click();

		 String[] resultado = analizaAlert(navegador1, navegador2);
		 assertThat(navegador1.switchTo().alert().getText()).isEqualTo(resultado[0] + " wins! " + 
		 			resultado[2] + " looses.");
	 }
	 
	 @Test
	 public void testGanaJugador2() {
		 inicializarNavegadores(jugador1, jugador2, navegador1, navegador2, arrayJugadores);
		 /*marca la celda*/
		 //simulo una partida para el jugador1
		 WebElement Xcelda3 = navegador1.findElement(By.id("cell-"+3)); //j1
		 Xcelda3.click();
		 WebElement Ocelda0 = navegador2.findElement(By.id("cell-"+0)); //j2
		 Ocelda0.click();
		 WebElement Xcelda4 = navegador1.findElement(By.id("cell-"+4)); //j1
		 Xcelda4.click();
		 WebElement Ocelda1 = navegador2.findElement(By.id("cell-"+1)); //j2
		 Ocelda1.click();
		 WebElement Xcelda8 = navegador1.findElement(By.id("cell-"+8)); //j1 
		 Xcelda8.click();
		 WebElement Ocelda2 = navegador2.findElement(By.id("cell-"+2)); //j2 gana la partida
		 Ocelda2.click();

		 String [] resultado = analizaAlert(navegador1, navegador2);
		 assertThat(navegador1.switchTo().alert().getText()).isEqualTo(resultado[0] + " wins! " + 
				 resultado[2] + " looses.");
	 }
	 
	 @Test
	 public void testEmpatan() {
		 inicializarNavegadores(jugador1, jugador2, navegador1, navegador2, arrayJugadores);
		 /*marca la celda*/
		 //simulo una partida para el jugador1
		 WebElement Xcelda0 = navegador1.findElement(By.id("cell-"+0)); //j1
		 Xcelda0.click();
		 WebElement Ocelda2 = navegador2.findElement(By.id("cell-"+2)); //j2
		 Ocelda2.click();
		 WebElement Xcelda1 = navegador1.findElement(By.id("cell-"+1)); //j1
		 Xcelda1.click();
		 WebElement Ocelda3 = navegador2.findElement(By.id("cell-"+3)); //j2
		 Ocelda3.click();
		 WebElement Xcelda5 = navegador1.findElement(By.id("cell-"+5)); //j1 
		 Xcelda5.click();
		 WebElement Ocelda4 = navegador2.findElement(By.id("cell-"+4)); //j2 
		 Ocelda4.click();
		 WebElement Xcelda6 = navegador1.findElement(By.id("cell-"+6)); //j1
		 Xcelda6.click();
		 WebElement Ocelda7 = navegador2.findElement(By.id("cell-"+7)); //j2
		 Ocelda7.click();
		 WebElement Xcelda8 = navegador1.findElement(By.id("cell-"+8)); //j1 Empatan la partida 
		 Xcelda8.click();

		 //Esperamos a la salida del alert
		 WebDriverWait wait1 = new WebDriverWait(navegador1, 120); // seconds
		 wait1.until(ExpectedConditions.alertIsPresent());
		
		 assertThat(navegador1.switchTo().alert().getText()).isEqualTo("Draw!");
	 }
		
}
