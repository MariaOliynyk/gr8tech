RECOMMENDED AUTOMATION PRIORITY 

1 - MUST AUTOMATE (Run on every commit/PR)

TC-01: Valid query returns results (already covered)
TC-02: Partial match works (already covered)
TC-03: Case-insensitive search (already covered)
TC-04: Empty query behavior (already covered)
TC-05: Missing query returns error (already covered)
TC-06: Default per_page is 50
TC-07: per_page boundaries (1, 200, 201)
TC-08: per_page negative/zero returns error
TC-09: Default page is 1
TC-10: page=2 returns different results
TC-11: page negative/zero returns error
TC-12: No duplicate results across pages
TC-13: Required fields present in response
TC-14: All IDs unique
TC-15: Nullable fields can be null
TC-16: Coordinate ranges valid
TC-17: Results match query term
TC-18: Empty results return empty array (already covered)
TC-19: Response count matches per_page

Priority 2 - SHOULD AUTOMATE 
TC-21: Special characters in query (O'Hara, &)
TC-22: Whitespace-only query
TC-23: Large page number returns empty
TC-24: per_page with non-numeric value
TC-25: Content-Type header validation
TC-26: website_url format validation


Priority 3 - CAN BE MANUAL (One-time or exploratory)
TC-27: SQL injection attempts
TC-28: XSS attempts
TC-29: Very long query strings (500+ chars)
TC-30: Unicode/emoji in query
TC-31: Unknown parameters ignored
TC-32: Error message content review
TC-33: Performance testing
TC-34: Load testing
