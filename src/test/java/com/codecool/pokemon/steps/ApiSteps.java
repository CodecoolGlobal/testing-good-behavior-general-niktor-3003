package com.codecool.pokemon.steps;

import com.codecool.pokemon.api.ApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {
    private ApiClient apiClient;
    private HttpResponse<String> response;

    @Given("the API base URL is {string}")
    public void configureApiBaseUrl(String baseUrl) {
        apiClient = new ApiClient(baseUrl);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String path) throws IOException, InterruptedException {
        response = apiClient.get(path);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode(),
                "The API response status code should match the expected value.");
    }

    @Then("the response JSON field {string} should be {string}")
    public void verifyJsonField(String fieldName, String expectedValue) {
        Map<?, ?> jsonBody = new Json().toType(
                response.body(),
                Map.class
        );

        assertEquals(expectedValue, jsonBody.get(fieldName),
                "The JSON field should be match the expected value: " + fieldName);
    }

    @Then("the response should contain a non-empty list of Pokemon URLs")
    public void verifyPokemonUrlList() {
        Map<?, ?> jsonBody = new Json().toType(
                response.body(),
                Map.class);

        List<?> pokemonUrls = assertInstanceOf(
                List.class,
                jsonBody.get("pokemons"),
                "The pokemons field should contain a list."
        );

        assertFalse(pokemonUrls.isEmpty(),
                "The Pokemon list should not be empty.");

        for (Object item : pokemonUrls) {
            String pokemonUrl = assertInstanceOf(
                    String.class,
                    item,
                    "Each Pokemon entry should be a string."
            );

            assertTrue(pokemonUrl.startsWith("https://pokeapi.co/api/v2/pokemon/"),
                    "Each entry should reference the Pokemon endpoint: " + pokemonUrl);
        }
    }
}
