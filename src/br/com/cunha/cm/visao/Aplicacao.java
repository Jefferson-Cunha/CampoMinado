package br.com.cunha.cm.visao;

import br.com.cunha.cm.modelo.Tabuleiro;

public class Aplicacao {
	public static void main(String[] args) {
		
		Tabuleiro tbl = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tbl);	

	}
}
