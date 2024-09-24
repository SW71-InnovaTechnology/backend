Feature: Search All Notifications

  Scenario: User successfully fetches all notifications
    Given the user is authenticated
    When the user makes a request to fetch all notifications
    Then the system should return a success status code (200)
    And the system should respond with a list of notifications

  Scenario: User tries to fetch notifications without authentication
    Given the user is not authenticated
    When the user makes a request to fetch all notifications
    Then the system should return a failure status code (401)
    And the response should contain an error message like "Unauthorized access"

  Scenario: User fetches all notifications but no notifications exist
    Given the user is authenticated
    And no notifications exist in the system
    When the user makes a request to fetch all notifications
    Then the system should return a success status code (200)
    And the response should contain an empty list

  Scenario: User fetches notifications with a filter
    Given the user is authenticated
    When the user requests all notifications filtered by "unread"
    Then the system should return a success status code (200)
    And the response should contain only unread notifications
