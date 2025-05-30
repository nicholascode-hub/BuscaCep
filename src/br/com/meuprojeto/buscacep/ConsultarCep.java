package br.com.meuprojeto.buscacep;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarCep {
    public Endereco buscaEndereco(String cep) {
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            // Verifica se o CEP não foi encontrado
            if (json.contains("\"erro\": true")) {
                throw new RuntimeException("CEP não encontrado: " + cep);
            }

            return new Gson().fromJson(json, Endereco.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro inesperado ao consultar o CEP: " + cep, e);
        }
    }
}

