package servico;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Pais;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ServicoPais {
    private static final String URL_BASE = "https://restcountries.com/v3.1/";

    public List<Pais> buscarPaisPorNome(String nome) throws IOException, InterruptedException {
        String url = URL_BASE + "name/" + nome;
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        if (resposta.statusCode() == 200) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Pais>>() {}.getType();
            return gson.fromJson(resposta.body(), tipoLista);
        } else {
            throw new RuntimeException("Erro ao buscar país: " + resposta.body());
        }
    }
}
