package br.com.meuprojeto.buscacep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {
    public void geradorJson(Endereco endereco) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nomeArquivo = endereco.cep() + ".json";

        try (FileWriter escrita = new FileWriter(nomeArquivo)) {
            escrita.write(gson.toJson(endereco));
        }

        System.out.println("Arquivo JSON gerado com sucesso: " + nomeArquivo);
    }
}
