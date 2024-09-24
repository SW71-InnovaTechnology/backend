Feature: User Sign Up

  Scenario: User successfully registers with valid information
    Given the user provides all required information (username, email, password)
    When the user submits the sign-up form
    Then the system should return a success status code (201)
    And the system should create a new user in the database
    And the response should include a welcome message or token

  Scenario: User tries to sign up with an already registered email
    Given the email is already registered in the system
    When the user submits the sign-up form with the same email
    Then the system should return a failure status code (409)
    And the response should contain an error message like "Email already exists"

  Scenario: User attempts to sign up with missing required information
    Given the user does not provide all required information
    When the user submits the sign-up form
    Then the system should return a failure status code (400)
    And the response should contain an error message like "Missing required fields"

  Scenario: User signs up with a weak password
    Given the user provides a weak password
    When the user submits the sign-up form
    Then the system should return a failure status code (400)
    And the response should contain an error message like "Password is too weak"

  Scenario: User tries to sign up with an invalid email format
    Given the user provides an invalid email format
    When the user submits the sign-up form
    Then the system should return a failure status code (400)
    And the response should contain an error message like "Invalid email format"
