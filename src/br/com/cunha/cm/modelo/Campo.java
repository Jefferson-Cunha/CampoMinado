package br.com.cunha.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cunha.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean minado = false;
	private boolean aberto = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// Função para adicionar vizinhos do campo!
	// V -> Abreviação de Verificar!

	boolean adicionarVizinho(Campo v) {

		boolean vLinha = linha != v.linha;
		boolean vColuna = coluna != v.coluna;
		boolean vDiagonal = vLinha && vColuna;

		int deltaLinha = Math.abs(linha - v.linha);
		int deltaColuna = Math.abs(coluna - v.coluna);
		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !vDiagonal) {
			vizinhos.add(v);
			return true;
		} else if (deltaGeral == 2 && vDiagonal) {
			vizinhos.add(v);
			return true;
		} else {
			return false;
		}

	}

	// Gets da Classe Campo.

	boolean isMinado() {
		return minado;
	}

	boolean isAberto() {
		return aberto;
	}
	
	boolean isFechado() {
		return !aberto;
	}

	boolean isMarcado() {
		return marcado;
	}

	// Função para alterar a marcação do campo!

	void alterarM() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	// Função para minar campo!

	void minar() {
		if (!minado) {
			minado = !minado; 
		}
	}

	// Função para abrir o campo!

	boolean abrirC() {
		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrirC());
			}
			return true;
		} else {
			return false;
		}
	}

	// Função para verificar vizinhança do campo selecionado.

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

}
