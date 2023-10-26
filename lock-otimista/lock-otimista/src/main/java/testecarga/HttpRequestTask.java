package testecarga;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask implements Runnable{

    @Override
    public void run() {
        try {
            // Defina o URL do endpoint
            URL url = new URL("http://localhost:8080/pedido");

            // Abra uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar a conexão para o método POST
            connection.setRequestMethod("POST");

            // Habilitar a entrada e saída de dados
            connection.setDoOutput(true);

            // Definir o tipo de conteúdo para JSON
            connection.setRequestProperty("Content-Type", "application/json");

            // Crie o JSON que você deseja enviar
            String jsonInput = "{\"quantidadeItem\": 5, \"produto\": {\"id\": 1}}";

            // Obtenha o OutputStream da conexão
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            // Escreva o JSON para o OutputStream
            osw.write(jsonInput);
            osw.flush();
            osw.close();

            // Obtenha a resposta do servidor
            int responseCode = connection.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);


            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // O JSON de resposta está contido na variável 'response'
                System.out.println("Resposta JSON: " + response.toString());
            }

            // Feche a conexão
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
