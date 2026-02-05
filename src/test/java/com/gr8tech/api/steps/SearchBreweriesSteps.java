package com.gr8tech.api.steps;

import com.gr8tech.api.pojo.Brewery;
import com.gr8tech.utils.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SearchBreweriesSteps {

    private static final Logger log = LoggerFactory.getLogger(SearchBreweriesSteps.class);

    private List<Brewery> lastResponse = Collections.emptyList();
    private Response lastRawResponse;

    private List<Brewery> fetch(String query, Integer perPage, Integer page) {
        log.info("Fetching breweries with query='{}', per_page={}, page={}", query, perPage, page);

        String baseUrl = ConfigProperties.getProperty("api.base.url");
        String searchPath = ConfigProperties.getProperty("api.search.path");

        var req = RestAssured.given().baseUri(baseUrl).basePath(searchPath)
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

    public void i_search_breweries_with_query(String query) {
        log.info("Step: Search breweries with query '{}'", query);
        lastResponse = fetch(query, null, null);
    }

    public void i_search_breweries_with_query_per_page_page(String query, Integer perPage, Integer page) {
        log.info("Step: Search breweries with query '{}', per_page={}, page={}", query, perPage, page);
        lastResponse = fetch(query, perPage, page);
    }

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

    public void response_size_is(Integer expectedSize) {
        log.info("Step: Verify response size is {}", expectedSize);
        int actualSize = lastResponse.size();
        log.info("Actual response size: {}", actualSize);
        Assert.assertEquals(actualSize, expectedSize.intValue(),
                "expected per_page size to match");
    }

    public void the_response_is_empty() {
        log.info("Step: Verify response is empty");
        int actualSize = lastResponse.size();
        log.info("Actual response size: {}", actualSize);
        Assert.assertEquals(actualSize, 0, "Expected empty array when no matches");
    }

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
