# Running the Tests

This project uses TestNG as the testing framework and RestAssured for API testing. Follow the steps below to run the tests:

## Prerequisites
1. Ensure you have Java installed (version 8 or higher).
2. Install Maven for dependency management and build.
3. Verify that the `config.properties` file is correctly configured with the API base URL and other required properties.

## Running the Tests

### Step 1: Clean and Build the Project
Run the following Maven command to clean and build the project:
```bash
mvn clean install
```

### Step 2: Execute the Tests
Run the following Maven command to execute the TestNG tests:
```bash
mvn test
```

### Step 3: View the Test Results
After the tests complete, the results will be available in the `target/surefire-reports` directory. Open the `emailable-report.html` file to view a summary of the test results.

## Running Specific Tests
To run specific test groups (e.g., `smoke` or `regression`), use the following command:
```bash
mvn test -Dgroups="smoke"
```
Replace `smoke` with the desired group name.

## Debugging Tests
If you need to debug a specific test, you can run it directly from your IDE (e.g., IntelliJ IDEA) by right-clicking the test method and selecting `Run`.

## Notes
- Ensure that the API server is running and accessible before executing the tests.
- If you encounter any issues, check the logs for detailed error messages.

---

For further assistance, refer to the TestNG and RestAssured documentation.
