package br.com.cunha.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cunha.cm.excecao.ExplosaoException;

public class CampoTest {

	private Campo campo;

	@BeforeEach
	void criarCampoTest() {
		campo = new Campo(3, 3);
	}

	// Testes adicionar Vizinhos

	@Test
	void testeVizinhoMesmaLinha() {
		Campo vizinhoEsquerda = new Campo(3, 2);
		Campo vizinhoDireita = new Campo(3, 4);
		boolean resultado1 = campo.adicionarVizinho(vizinhoEsquerda);
		boolean resultado2 = campo.adicionarVizinho(vizinhoDireita);

		assertTrue(resultado1);
		assertTrue(resultado2);
	}

	@Test
	void testeVizinhoLinhaSuperior() {
		Campo vizinho1 = new Campo(2, 2);
		Campo vizinho2 = new Campo(2, 3);
		Campo vizinho3 = new Campo(2, 4);

		boolean resultado1 = campo.adicionarVizinho(vizinho1);
		boolean resultado2 = campo.adicionarVizinho(vizinho2);
		boolean resultado3 = campo.adicionarVizinho(vizinho3);

		assertTrue(resultado1);
		assertTrue(resultado2);
		assertTrue(resultado3);

	}

	@Test
	void testeVizinhoLinhaInferior() {
		Campo vizinho1 = new Campo(4, 2);
		Campo vizinho2 = new Campo(4, 3);
		Campo vizinho3 = new Campo(4, 4);

		boolean resultado1 = campo.adicionarVizinho(vizinho1);
		boolean resultado2 = campo.adicionarVizinho(vizinho2);
		boolean resultado3 = campo.adicionarVizinho(vizinho3);

		assertTrue(resultado1);
		assertTrue(resultado2);
		assertTrue(resultado3);

	}

	@Test
	void testeNaoVizinho() {
		Campo vizinho1 = new Campo(2, 5);
		Campo vizinho2 = new Campo(3, 5);
		Campo vizinho3 = new Campo(4, 5);

		boolean resultado1 = campo.adicionarVizinho(vizinho1);
		boolean resultado2 = campo.adicionarVizinho(vizinho2);
		boolean resultado3 = campo.adicionarVizinho(vizinho3);

		assertFalse(resultado1);
		assertFalse(resultado2);
		assertFalse(resultado3);

	}

	// Teste Alterar Marcação!

	@Test
	void AlterarM1() {
		campo.alterarM();

		boolean Marcado = campo.isMarcado();

		assertTrue(Marcado);

	}

	@Test
	void AlterarM2() {

		boolean Marcado = campo.isMarcado();

		assertFalse(Marcado);

	}

	// Teste abrir campo selecionado.

	@Test
	void AbrirCampo() {
		assertTrue(campo.abrirC());
	}

	// Teste abrir campo Marcado.

	@Test
	void abrirCampoMarcado() {
		campo.alterarM();
		boolean erroAbrir = campo.abrirC();
		assertFalse(erroAbrir);
	}

	// Teste abrir campo minado e não marcado.

	@Test
	void testAbrirCampoMinado() throws ExplosaoException {
		campo.minar();

		assertThrows(ExplosaoException.class, () -> {
			campo.abrirC();
		});

	}

	// Teste abrir campo minado e marcado.
	@Test
	void campoMarcadoMinado() {
		campo.alterarM();
		campo.minar();
		assertFalse(campo.abrirC());

	}

	// Teste abrir campo aberto.

	@Test
	void testeAbrirCampoAberto() {

		campo.abrirC(); // Abrindo o campo pela 1° vez

		boolean erroAbrir = campo.abrirC(); // Abrindo o campo pela 2º vez

		assertFalse(erroAbrir);
	}
	
	
	
	//Teste Abrir campos vizinhança
	
	@Test
	void abrirVizinhosSeguros() {
		Campo vizinhoReal = new Campo(2, 3);
		Campo vizinho2 = new Campo(1,2);
		
		campo.adicionarVizinho(vizinhoReal);
		vizinhoReal.adicionarVizinho(vizinho2);
		
		campo.abrirC();
		
		assertTrue(vizinhoReal.isAberto() && vizinho2.isAberto());
	}
	
	//Teste Abrir campos vizinhança Não segura
	
		@Test
		void abrirVizinhosNaoSeguros() {
			Campo vizinhoReal = new Campo(2, 3);
			Campo vizinho2 = new Campo(1,3);
								
			campo.adicionarVizinho(vizinhoReal);
			vizinhoReal.adicionarVizinho(vizinho2);
			
			vizinhoReal.minar();
			campo.abrirC();
			
			assertTrue(campo.isAberto() && vizinho2.isFechado());
		}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
