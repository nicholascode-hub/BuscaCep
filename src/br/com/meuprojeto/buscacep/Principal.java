package br.com.meuprojeto.buscacep;

import java.io.IOException;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {

        ConsultarCep consultarCep = new ConsultarCep();

        try (Scanner entrada = new Scanner(System.in)) {

            System.out.println("Insira o número do CEP para continuar:");
            String cep = entrada.nextLine().trim();

            // Validação simples do CEP (8 dígitos numéricos)
            if (!cep.matches("\\d{8}")) {
                System.out.println("CEP inválido. Informe um CEP com 8 dígitos numéricos.");
                return;
            }

            Endereco novoEndereco = consultarCep.buscaEndereco(cep);
            System.out.println(novoEndereco);

            GeradorDeArquivo gerador = new GeradorDeArquivo();
            gerador.geradorJson(novoEndereco);

        } catch (RuntimeException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("A aplicação foi encerrada.");
        }

    }
}
