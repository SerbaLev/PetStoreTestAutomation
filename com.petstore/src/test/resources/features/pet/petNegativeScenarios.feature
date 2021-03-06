@PetPositiveTests

Feature: Add, update, delete and get pet data from the pet store service
  As a pet store service consumer
  I want to be able to work with pet data
  So that I can add, update, delete and get pet from the pet store service

  Scenario: Add a new pet to the store with invalid input data
    Given User provides id, name and status information
      | id         | name | status    |
      | gewgwgwgwg | Bob  | available |
    And User provides category information
      | id    | name |
      | eeeee | dog  |
    And User provides tags information
      | id     | name  |
      | ge     | dog   |
      | 34ggg8 | corgi |
    When User send "add new pet" request
    Then Request executed with status code 405 in the response

  Scenario: Add a new pet to the store without mandatory fields
    Given User provides id, name and status information
      | id    | name | status    |
      | 12345 |      | available |
    And User provides category information
      | id  | name |
      | 123 | dog  |
    And User provides tags information
      | id  | name  |
      | 347 | dog   |
      | 348 | corgi |
    When User send "add new pet" request
    Then Request executed with status code 405 in the response

  Scenario: Update information about pet in the store with incorrect data
    Given User provides id, name and status information
      | id         | name | status    |
      | gewgwgwgwg | Bob  | available |
    And User provides category information
      | id    | name |
      | eeeee | dog  |
    And User provides tags information
      | id     | name  |
      | ge     | dog   |
      | 34ggg8 | corgi |
    When User send "update pet information" request
    Then Request executed with status code 405 in the response

  Scenario: Update information about nonexistent pet in the store
    Given User provides id, name and status information
      | id          | name    | status    |
      | 17824562315 | Borggeo | available |
    And User provides category information
      | id        | name |
      | 535252522 | cat  |
    And User provides photo URLs
      | src/test/resources/photos/photo1.JPG |
      | src/test/resources/photos/photo2.JPG |
    And User provides tags information
      | id      | name  |
      | 745262  | dog   |
      | 9742562 | corgi |
    When User send "update pet information" request
    Then Request executed with status code 404 in the response

  Scenario: Update information about nonexistent pet in the store with form
    Given User provides id
      | id        |
      | 526326232 |
    And User update name and status with form
      | status | name   |
      | sold   | Bobobb |
    And User send "update pet information with form" request
    Then Request executed with status code 404 in the response
    And Request executed with correct notification or error in the response
      | code | type    | message   |
      | 404  | unknown | not found |

  Scenario: Update information about pet in the store with invalid input in form
    Given User provides id
      | id        |
      | gdhhxdhxx |
    And User update name and status with form
      | status | name   |
      | 2525   | 125155 |
    And User send "update pet information with form" request
    Then Request executed with status code 405 in the response

  Scenario: Find pets by status
    Given User provides statuses
      | status                           |
      | incorrect_status_436337373545121 |
    When User send "find pet by status" request
    Then Request executed with status code 400 in the response

  Scenario: Find pet by nonexistent id
    Given User provides id, name and status information
      | id    | name | status    |
      | 22345 | Rex  | available |
    And User provides category information
      | id  | name |
      | 223 | dog  |
    And User provides photo URLs
      | src/test/resources/photos/photo1.JPG |
      | src/test/resources/photos/photo2.JPG |
    And User provides tags information
      | id  | name  |
      | 247 | dog   |
      | 248 | corgi |
    And User send "add new pet" request
    And Request executed with status code 200 in the response
    And Request executed with correct response
    When User provides id
      | id        |
      | 115234552 |
    And User send "find pet by id" request
    Then Request executed with status code 404 in the response
    And Request executed with correct notification or error in the response
      | code | type  | message       |
      | 404  | error | Pet not found |

  Scenario: Find pet by incorrect id
    Given User provides id, name and status information
      | id    | name | status    |
      | 22345 | Rex  | available |
    And User provides category information
      | id  | name |
      | 223 | dog  |
    And User provides photo URLs
      | src/test/resources/photos/photo1.JPG |
      | src/test/resources/photos/photo2.JPG |
    And User provides tags information
      | id  | name  |
      | 247 | dog   |
      | 248 | corgi |
    And User send "add new pet" request
    And Request executed with status code 200 in the response
    And Request executed with correct response
    When User provides id
      | id     |
      | qwerty |
    And User send "find pet by id" request
    Then Request executed with status code 400 in the response

  Scenario: Delete pet by unknown id
    Given User provides id
      | id        |
      | 710234173 |
    When User send "delete pet by id" request
    Then Request executed with status code 404 in the response
