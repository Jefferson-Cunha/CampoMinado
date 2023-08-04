package br.com.cunha.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cunha.cm.excecao.ExplosaoException;
import br.com.cunha.cm.excecao.sairException;
import br.com.cunha.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tbl) {
		this.tabuleiro = tbl;
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			while (continuar) {
				cicloDoJogo();
				System.out.println("Outra partida? (S/n): ");
				String resposta = entrada.next();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}

			}

		} catch (sairException e) {
			System.out.println("Obrigado por jogar!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {

			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println("\n" + tabuleiro);
				String digitado = capturarValor("Informe o valor de X, Y: ");

				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = capturarValor("1: Abrir ou 2: (Des)Marcar: ");
				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrirCampo(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(digitado)){
					tabuleiro.MarcaCampo(xy.next(), xy.next());
				} 

			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!  \n");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.err.println("Você perdeu!!");
		}

	}

	private String capturarValor(String txt) {
		System.out.print(txt);
		String digitado = entrada.next();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new sairException();
		} else {
			return digitado;
		}

	}

}
