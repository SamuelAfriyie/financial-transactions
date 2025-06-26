# 💰 Financial Transactions API

A Spring Boot REST API that allows you to retrieve financial transactions using flexible filters such as date range, user ID, service type, transaction status, and more.

---

## 📚 Table of Contents

- [🔍 Overview](#-overview)  
- [🌐 API Endpoint](#-api-endpoint)  
- [⚙️ Query Parameters](#️-query-parameters)  
- [📬 Example Request](#-example-request)

---

## 🔍 Overview

The Financial Transactions API provides a powerful and customizable way to query transactions for analytical or operational purposes.  
With support for filtering, pagination, and user-specific searches, it's ideal for building reporting tools and dashboards.

---

## 🌐 API Endpoint

**GET** `/transactions`  
Base URL: `http://localhost:8080/transactions`

---

## ⚙️ Query Parameters

| Parameter   | Type    | Description                                                                 |
|-------------|---------|-----------------------------------------------------------------------------|
| `dateFrom`  | String  | Start date of the filter range (e.g., `2023-01-01`)                         |
| `dateTo`    | String  | End date of the filter range (e.g., `2023-12-31`)                           |
| `userId`    | String  | ID of the user whose transactions are being queried (e.g., `user123`)       |
| `service`   | String  | Service name/type related to the transaction (e.g., `serviceA`)             |
| `status`    | String  | Current status of the transaction (e.g., `completed`, `pending`)            |
| `reference` | String  | Reference ID of the transaction (e.g., `ref123`)                            |
| `offset`    | Integer | Starting index for pagination (default: `0`)                                |
| `limit`     | Integer | Maximum number of records to return (e.g., `10`)                            |

---

## 📬 Example Request

Here’s how you can test the endpoint using `curl` or Postman:

```http
GET http://localhost:8080/transactions?dateFrom=2023-01-01&dateTo=2025-02-11&userId=user123&service=serviceA&status=completed&reference=ref123&offset=0&limit=10

## 🚀 Getting Started

To run the project locally:

```bash
# Clone the repository
git clone https://github.com/your-org/financial-transactions.git

# Navigate into the project
cd financial-transactions

# Build and run the app
./mvnw spring-boot:run