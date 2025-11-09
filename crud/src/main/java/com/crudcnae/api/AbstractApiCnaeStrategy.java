package com.crudcnae.api;

import com.crudcnae.model.SubclasseCnae;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Classe utilitária para centralizar o consumo HTTP e desserialização JSON.
 */
public abstract class AbstractApiCnaeStrategy implements ApiCnaeStrategy {

    protected static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v2/cnae";
    protected final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    protected final ObjectMapper mapper = new ObjectMapper();

    protected List<SubclasseCnae> fetchList(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(java.time.Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200)
            throw new IOException("Erro na API IBGE (" + response.statusCode() + "): " + response.body());

        return mapper.readValue(response.body(), new TypeReference<List<SubclasseCnae>>() {});
    }

    protected SubclasseCnae fetchSingle(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(java.time.Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200)
            throw new IOException("Erro na API IBGE (" + response.statusCode() + "): " + response.body());

        return mapper.readValue(response.body(), SubclasseCnae.class);
    }
}
