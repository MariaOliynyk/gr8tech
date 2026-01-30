# 🎯 Recommended Automation Priority

---

## 🔴 Priority 1 - MUST AUTOMATE 
**(Run on every commit/PR)**

| Test ID | Description | Status |
|---------|-------------|--------|
| **TC-01** | Valid query returns results | ✅ Already covered |
| **TC-02** | Partial match works | ✅ Already covered |
| **TC-03** | Case-insensitive search | ✅ Already covered |
| **TC-04** | Empty query behavior | ✅ Already covered |
| **TC-05** | Missing query returns error | ✅ Already covered |
| **TC-06** | Default `per_page` is 50 | ⏳ To implement |
| **TC-07** | `per_page` boundaries (1, 200, 201) | ⏳ To implement |
| **TC-08** | `per_page` negative/zero returns error | ⏳ To implement |
| **TC-09** | Default `page` is 1 | ⏳ To implement |
| **TC-10** | `page=2` returns different results | ⏳ To implement |
| **TC-11** | `page` negative/zero returns error | ⏳ To implement |
| **TC-12** | No duplicate results across pages | ⏳ To implement |
| **TC-13** | Required fields present in response | ⏳ To implement |
| **TC-14** | All IDs unique | ⏳ To implement |
| **TC-15** | Nullable fields can be null | ⏳ To implement |
| **TC-16** | Coordinate ranges valid | ⏳ To implement |
| **TC-17** | Results match query term | ⏳ To implement |
| **TC-18** | Empty results return empty array | ✅ Already covered |
| **TC-19** | Response count matches `per_page` | ⏳ To implement |

---

## 🟡 Priority 2 - SHOULD AUTOMATE
**(Important but not critical for every run)**

| Test ID | Description | Status |
|---------|-------------|--------|
| **TC-21** | Special characters in query (O'Hara, &) | ⏳ To implement |
| **TC-22** | Whitespace-only query | ⏳ To implement |
| **TC-23** | Large page number returns empty | ⏳ To implement |
| **TC-24** | `per_page` with non-numeric value | ⏳ To implement |
| **TC-25** | Content-Type header validation | ⏳ To implement |
| **TC-26** | `website_url` format validation | ⏳ To implement |

---

## 🟢 Priority 3 - CAN BE MANUAL
**(One-time or exploratory testing)**

| Test ID | Description | Testing Type |
|---------|-------------|--------------|
| **TC-27** | SQL injection attempts | 🔒 Security |
| **TC-28** | XSS attempts | 🔒 Security |
| **TC-29** | Very long query strings (500+ chars) | 🔍 Exploratory |
| **TC-30** | Unicode/emoji in query | 🔍 Exploratory |
| **TC-31** | Unknown parameters ignored | 🔍 Exploratory |
| **TC-32** | Error message content review | 📝 Manual review |
| **TC-33** | Performance testing | ⚡ Performance |
| **TC-34** | Load testing | ⚡ Performance |

---

## 📊 Summary

- **Priority 1 (MUST):** 19 test cases (5 ✅ covered, 14 ⏳ to implement)
- **Priority 2 (SHOULD):** 6 test cases
- **Priority 3 (CAN BE MANUAL):** 8 test cases

**Total:** 33 test cases
