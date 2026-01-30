# 📊 Allure Reporting Guide

### 1. Run Tests with Maven

```bash
mvn clean test
```
This will:
- Execute all tests
- Generate Allure results in `target/allure-results/`
---

### 2. Generate and View Allure Report

#### Option A: Generate and Open Report (Recommended)

```bash
mvn allure:serve
```
This command will:
- Generate the HTML report
- Start a local web server
- Automatically open the report in your default browser

#### Option B: Generate Report Only

```bash
mvn allure:report
```

The report will be generated in `target/site/allure-maven-plugin/` directory.
To view it, open `target/site/allure-maven-plugin/index.html` in your browser.

