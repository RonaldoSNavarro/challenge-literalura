package br.com.alura.literalura.service;

import br.com.alura.literalura.entity.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GutendexService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<Book> searchBookByTitle(String title) {
        try {
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
            String url = "https://gutendex.com/books/?search=" + encodedTitle;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode jsonResponse = objectMapper.readTree(response.body());
                JsonNode results = jsonResponse.get("results");

                if (results.size() > 0) {
                    JsonNode bookJson = results.get(0);
                    Book book = new Book();
                    book.setTitle(bookJson.get("title").asText());
                    book.setAuthor(bookJson.get("authors").get(0).get("name").asText());
                    book.setLanguage(bookJson.get("languages").get(0).asText());
                    book.setDownloads(bookJson.get("download_count").asInt());
                    return Optional.of(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
