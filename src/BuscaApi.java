import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class BuscaApi {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner filme = new Scanner(System.in);
        System.out.println("digitae o nome do filme");
        var busca = filme.nextLine();

        String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=e8f4e026";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(endereco)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String json = response.body();

        Gson gson = new Gson();
        Titulo meuTitulo = gson.fromJson(json, Titulo.class);
        System.out.println("Titulo " + meuTitulo.getNome() + "Ano de lançamento " + meuTitulo.getAnoDeLancamento());

    }
}