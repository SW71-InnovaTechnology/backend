Feature: Create a Notification

  Scenario: User successfully creates a notification
    Given the user is authenticated
    And the user provides valid notification details
    When the user makes a request to create a notification
    Then the system should return a success status code (201)
    And the new notification should be saved in the system

  Scenario: User tries to create a notification with missing information
    Given the user is authenticated
    And the notification details are incomplete
    When the user makes a request to create a notification
    Then the system should return a failure status code (400)
    And the response should contain an error message like "Missing notification details"

  Scenario: User tries to create a notification without authentication
    Given the user is not authenticated
    When the user makes a request to create a notification
    Then the system should return a failure status code (401)
    And the response should contain an error message like "Unauthorized access"

  Scenario: User tries to create a duplicate notification
    Given the user is authenticated
    And the user tries to create a notification with the same details as an existing one
    When the user makes a request to create a duplicate notification
    Then the system should return a failure status code (409)
    And the response should contain an error message like "Notification already exists"
