package com.gr8tech.api.steps;

import com.gr8tech.api.pojo.Brewery;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class SearchBreweriesSteps {

    private static final String BASE;
    private static final String SEARCH_PATH;
    private static final Logger log = LoggerFactory.getLogger(SearchBreweriesSteps.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        Properties props = new Properties();
        try (InputStream in = SearchBreweriesSteps.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(in);
            BASE = props.getProperty("api.base.url");
            SEARCH_PATH = props.getProperty("api.search.path");
            if (BASE == null || SEARCH_PATH == null) {
                throw new RuntimeException("Required properties missing in config.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private List<Brewery> lastResponse = Collections.emptyList();
    private final Map<String, String> savedIds = new HashMap<>();
    private Response lastRawResponse;

    private List<Brewery> fetch(String query, Integer perPage, Integer page) {
        log.info("Fetching breweries with query='{}', per_page={}, page={}", query, perPage, page);

        var req = RestAssured.given().baseUri(BASE).basePath(SEARCH_PATH)
                .queryParam("query", query);
        if (perPage != null) req.queryParam("per_page", perPage);
        if (page != null) req.queryParam("page", page);

        Response response = req
                .when().get()
                .then()
                .extract().response();

        this.lastRawResponse = response;

        int statusCode = response.statusCode();
        log.info("Response received: status={}", statusCode);

        return response.jsonPath().getList("", Brewery.class);
    }

    @When("I search breweries with query {string}")
    public void i_search_breweries_with_query(String query) {
        log.info("Step: Search breweries with query '{}'", query);
        lastResponse = fetch(query, null, null);
    }

    @When("I search breweries with query {string} per_page {int} page {int}")
    public void i_search_breweries_with_query_per_page_page(String query, Integer perPage, Integer page) {
        log.info("Step: Search breweries with query '{}', per_page={}, page={}", query, perPage, page);
        lastResponse = fetch(query, perPage, page);
    }

    @When("I search breweries {string}")
    public void i_search_breweries_without_query(String mode) {
        log.info("Step: Search breweries '{}'", mode);
        var req = RestAssured.given().baseUri(BASE).basePath(SEARCH_PATH);

        switch (mode) {
            case "with empty query":
                req = req.queryParam("query", "");
                log.debug("Adding empty query parameter");
                break;
            case "without query":
                log.debug("No query parameter added");
                break;
            default:
                throw new IllegalArgumentException("Unsupported query mode: " + mode);
        }

        this.lastRawResponse = req
                .when().get()
                .andReturn();

        log.info("Response status: {}", lastRawResponse.statusCode());
    }

    @Then("response contains at least one brewery with name containing {string}")
    public void response_contains_at_least_one_brewery_with_name_containing(String expectedPart) {
        log.info("Step: Verify response contains brewery with name containing '{}'", expectedPart);
        String expectedLower = expectedPart.toLowerCase(Locale.ROOT);
        List<String> names = lastResponse.stream()
                .map(b -> b.getName() == null ? "" : b.getName())
                .toList();

        log.debug("Brewery names in response: {}", names);
        boolean any = names.stream().anyMatch(n -> n.toLowerCase(Locale.ROOT).contains(expectedLower));
        log.info("Match found: {}", any);
        Assert.assertTrue(any, "No brewery name contains '" + expectedPart + "'. Names: " + names);
    }

    @Then("response size is {int}")
    public void response_size_is(Integer expectedSize) {
        log.info("Step: Verify response size is {}", expectedSize);
        int actualSize = lastResponse.size();
        log.info("Actual response size: {}", actualSize);
        Assert.assertEquals(actualSize, expectedSize.intValue(),
                "expected per_page size to match");
    }

    @Then("the response is empty")
    public void the_response_is_empty() {
        log.info("Step: Verify response is empty");
        int actualSize = lastResponse.size();
        log.info("Actual response size: {}", actualSize);
        Assert.assertEquals(actualSize, 0, "Expected empty array when no matches");
    }

    @Then("response status should not be 200")
    public void response_status_should_not_be_200() {
        log.info("Step: Verify response status is not 200");
        if (lastRawResponse == null) {
            Assert.fail("No raw response available to check status");
        }
        int code = lastRawResponse.statusCode();
        String body = lastRawResponse.getBody() == null ? "" : lastRawResponse.getBody().asString();
        log.info("Actual response status: {}", code);
        log.debug("Response body: {}", body);
        Assert.assertNotEquals(code, 200,
                "Expected non-200 status when query parameter is missing, got 200. Response body:\n" + body);
    }
}
