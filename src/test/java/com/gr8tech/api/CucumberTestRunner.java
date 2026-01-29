package com.gr8tech.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.gr8tech.api.steps",
        plugin = {"pretty", "summary"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
