# Test Automation Approach – List Breweries API

## 📋 Overview

The **List Breweries** endpoint (`GET /v1/breweries`) returns a paginated list of breweries and supports multiple optional query parameters for filtering and sorting.

The most effective automation strategy is to split the work into **separate tasks** and estimate the effort using **story points**.

---

## 🎯 Automation Approach

Test automation for this endpoint should be implemented **incrementally** by dividing the work into independent tasks, each with a clear scope and value.

### Benefits:
- ✅ Better control over scope and priorities
- ✅ Realistic estimation based on complexity

---

## 🧪 Test Design Techniques

The following techniques would be applied:

1. **Equivalence Partitioning** – for parameters with defined valid and invalid value sets (e.g. `by_type`, `by_postal`)
2. **Boundary Value Analysis** – for pagination and limits (`page`, `per_page`)
3. **Pairwise / Combinatorial Testing** – for selected combinations of filters
4. **Positive and Negative Testing** – to validate both expected and erroneous inputs

---

## 📊 Task Breakdown & Story Points

### **TC-01: Test Analysis & Test Design**

**Scope:**
- Analyze API documentation
- Identify critical scenarios
- Define test coverage and test sets
- Select test design techniques

**Story Points:** `3`

---

### **TC-02: Test Framework Setup**

**Scope:**
- Project initialization
- API client setup
- Configuration management
- Base test structure
- README documentation
- Test execution instructions
- Basic reporting setup

**Story Points:** `5`

---

### **TC-03: Core Functional Tests**

**Scope:**
- GET request without parameters
- Status code validation
- Basic response structure checks

**Story Points:** `2`

---

### **TC-04: Filters Testing (Single Parameter)**

**Scope:**
- Independent tests for each filter: `by_city`, `by_state`, etc.
- Validation that returned data actually matches the applied filter

**Story Points:** `5`

---

### **TC-05: Pagination & Limits**

**Scope:**
- `page`: first page, zero, large values
- `per_page`: minimum, default, maximum, above maximum
- Consistency across pages

**Story Points:** `3`

---

### **TC-06: Sorting & Special Filters**

**Scope:**
- Sorting with `asc` and `desc`
- `by_dist` parameter
- Validation of incompatible parameters (`by_dist` with `sort`)

**Story Points:** `3`

---

### **TC-07: Negative & Edge Case Testing**

**Scope:**
- Invalid parameter values
- Non-existing cities or countries
- Incorrect formats
- Expected behavior (empty list vs error response)

**Story Points:** `5` _(or divide into 2 tasks of 2 and 3 points)_

---

### **TC-08: Response Contract & Schema Validation**

**Scope:**
- Mandatory fields presence
- Data types validation
- API contract stability

**Story Points:** `3`

---

## 📈 Summary

| Task | Description | Story Points |
|------|-------------|--------------|
| TC-01 | Test Analysis & Test Design | 3 |
| TC-02 | Test Framework Setup | 5 |
| TC-03 | Core Functional Tests | 2 |
| TC-04 | Filters Testing (Single Parameter) | 5 |
| TC-05 | Pagination & Limits | 3 |
| TC-06 | Sorting & Special Filters | 3 |
| TC-07 | Negative & Edge Case Testing | 5 |
| TC-08 | Response Contract & Schema Validation | 3 |

### **Total Story Points:** `29`

