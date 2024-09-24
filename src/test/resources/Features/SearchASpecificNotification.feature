Feature: Search a Specific Notification

  Scenario: User successfully fetches a specific notification by ID
    Given the user is authenticated
    And a notification with ID "12345" exists
    When the user makes a request to fetch the notification with ID "12345"
    Then the system should return a success status code (200)
    And the response should include the details of the notification with ID "12345"

  Scenario: User tries to fetch a notification with an invalid ID
    Given the user is authenticated
    And no notification with ID "99999" exists
    When the user makes a request to fetch the notification with ID "99999"
    Then the system should return a failure status code (404)
    And the response should contain an error message like "Notification not found"

  Scenario: User tries to fetch a notification without authentication
    Given the user is not authenticated
    When the user makes a request to fetch a specific notification
    Then the system should return a failure status code (401)
    And the response should contain an error message like "Unauthorized access"

  Scenario: User fetches a specific notification but the notification is already deleted
    Given the user is authenticated
    And the notification with ID "56789" was previously deleted
    When the user makes a request to fetch the notification with ID "56789"
    Then the system should return a failure status code (410)
    And the response should contain an error message like "Notification no longer available"
