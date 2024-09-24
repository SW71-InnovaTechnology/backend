Feature: User Sign In

  Scenario: User signs in with valid credentials
    Given the user has valid login credentials
    When the user submits the correct username and password
    Then the system should return a success status code (200)
    And the system should respond with a valid authentication token
    And the token should be stored in the user's session or local storage

  Scenario: User signs in with invalid credentials
    Given the user has invalid login credentials
    When the user submits incorrect username or password
    Then the system should return a failure status code (401)
    And the response should contain an error message like "Invalid credentials"

  Scenario: User attempts sign in without providing credentials
    Given the user has not entered username or password
    When the user tries to sign in
    Then the system should return a failure status code (400)
    And the response should contain an error message like "Missing credentials"

  Scenario: User signs in with expired token
    Given the user has valid login credentials
    And the user's token has expired
    When the user attempts to sign in
    Then the system should return a failure status code (403)
    And the response should contain an error message like "Token expired"
