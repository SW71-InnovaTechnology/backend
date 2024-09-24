Feature: Delete a Notification

  Scenario: User successfully deletes a notification by ID
    Given the user is authenticated
    And a notification with ID "12345" exists
    When the user makes a request to delete the notification with ID "12345"
    Then the system should return a success status code (200)
    And the notification should be removed from the system

  Scenario: User tries to delete a notification that does not exist
    Given the user is authenticated
    And no notification with ID "99999" exists
    When the user makes a request to delete the notification with ID "99999"
    Then the system should return a failure status code (404)
    And the response should contain an error message like "Notification not found"

  Scenario: User tries to delete a notification without authentication
    Given the user is not authenticated
    When the user makes a request to delete a specific notification
    Then the system should return a failure status code (401)
    And the response should contain an error message like "Unauthorized access"

  Scenario: User tries to delete an already deleted notification
    Given the user is authenticated
    And the notification with ID "56789" was previously deleted
    When the user makes a request to delete the notification with ID "56789"
    Then the system should return a failure status code (410)
    And the response should contain an error message like "Notification no longer available"
