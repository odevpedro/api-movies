import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import excessao.ErroDeConversaoAnoExcpetion;
import modelos.Titulo;
import modelos.TituloOMDB;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaApi {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner filme = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(!busca.equalsIgnoreCase("sair")){

        System.out.println("digite o nome do filme: ");
         busca = filme.nextLine();

         if (busca.equalsIgnoreCase("sair")){
             break;
         }

        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=e8f4e026";
         System.out.println(endereco);
         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(endereco)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        TituloOMDB meuTituloOmdb = gson.fromJson(json, TituloOMDB.class);
        System.out.println(meuTituloOmdb);

        try {
            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Titulo já convertido");
            System.out.println(meuTitulo);

            titulos.add(meuTitulo);

        } catch (NumberFormatException e){
            System.out.println("Aconteceu um erro");
            System.out.println(e.getMessage());
        } catch (ErroDeConversaoAnoExcpetion e){
            System.out.println(e.getMessage());
        }

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("O programa finalizou corretamente!!!");



        }

        System.out.println(titulos);
    }

}