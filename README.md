# Financial Transactions API

Spring Boot application that provides an endpoint to fetch financial transactions based on specified filters.

## Table of Contents

- [Overview](#overview)
- [API Endpoint](#api-endpoint)
- [Query Parameters](#query-parameters)
- [Example Request](#example-request)

## Overview

This application allows users to retrieve financial transactions filtered by various parameters, enhancing the ability to analyze transaction data effectively.

## API Endpoint

**GET** `http://localhost:8080/transactions`

## Query Parameters

The following query parameters can be used to filter the transactions:

| Parameter   | Type   | Description                                  |
|-------------|--------|----------------------------------------------|
| `dateFrom`  | String | Start date for the transaction filter (e.g., `2023-01-01`) |
| `dateTo`    | String | End date for the transaction filter (e.g., `2023-12-31`)   |
| `userId`    | String | ID of the user associated with the transactions (e.g., `user123`) |
| `service`   | String | Service type related to the transaction (e.g., `serviceA`)   |
| `offset`    | Integer| Offset for pagination (e.g., `0`)          |
| `limit`     | Integer| Maximum number of records to return (e.g., `10`) |
| `status`    | String | Status of the transaction (e.g., `completed`) |
| `reference` | String | Reference ID for the transaction (e.g., `ref123`) |

## Example Request

You can test the endpoint using Postman or Curl. Here's an example request:

```http
GET http://localhost:8080/transactions?dateFrom=2023-01-01&dateTo=2025-02-11&userId=user123&service=serviceA&offset=0&limit=10&status=completed&reference=ref123
# financial-transactions
