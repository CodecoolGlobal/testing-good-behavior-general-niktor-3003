@api
Feature: Pokemon backend API

    Background:
        Given the API base URL is "http://localhost:4000"

     Scenario: Check backend health
        When I send a GET request to "/api/health"
        Then the response status code should be 200
        And the response JSON field "status" should be "ok"