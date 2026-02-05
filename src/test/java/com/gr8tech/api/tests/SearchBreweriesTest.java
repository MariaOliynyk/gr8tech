package com.gr8tech.api.tests;

import com.gr8tech.utils.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

public class SearchBreweriesTest {

    private static final Logger log = LoggerFactory.getLogger(SearchBreweriesTest.class);
    private static final String BASE_URL = ConfigProperties.getProperty("api.base.url");
    private static final String SEARCH_PATH = ConfigProperties.getProperty("api.search.path");

    @DataProvider(name = "queryModes")
    public Object[][] queryModes() {
        return new Object[][]{
                {"with empty query", "?query="},
                {"without query", ""}
        };
    }

    @Test(groups = {"search", "smoke", "regression"}, description = "Verify search is case-insensitive")
    public void testSearchIsCaseInsensitive() {
        String query = "dEfT";
        String expectedSubstring = "Deft";

        log.info("Test: Search is case-insensitive with query '{}'.", query);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(SEARCH_PATH)
                .queryParam("query", query)
                .when().get()
                .then().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200.");

        String responseBody = response.getBody().asString();
        log.debug("Response body: {}", responseBody);

        boolean containsExpected = responseBody.toLowerCase(Locale.ROOT).contains(expectedSubstring.toLowerCase(Locale.ROOT));
        Assert.assertTrue(containsExpected, "Response does not contain expected substring: " + expectedSubstring);
    }

    @Test(dataProvider = "queryModes", groups = {"search","smoke", "regression"}, description = "Verify query parameter is required")
    public void testQueryParameterIsRequired(String mode, String querySuffix) {
        log.info("Test: Query parameter is required - Mode: '{}'.", mode);

        String url = BASE_URL + SEARCH_PATH + querySuffix;
        log.debug("Request URL: {}", url);

        Response response = RestAssured.given()
                .when().get(url)
                .then().extract().response();

        int statusCode = response.statusCode();
        log.info("Response status code: {}", statusCode);

        Assert.assertNotEquals(statusCode, 200, "Expected non-200 status code for mode: " + mode);
    }
}
