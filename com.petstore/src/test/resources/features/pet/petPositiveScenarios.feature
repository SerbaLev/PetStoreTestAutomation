@PetNegativeTests

Feature: Add, update, delete and get pet data from the pet store service
  As a pet store service consumer
  I want to be able to work with pet data
  So that I can add, update, delete and get pet from the pet store service

  Scenario: Add a new pet to the store
    Given User provides id, name and status information
      | id    | name | status    |
      | 12345 | Bob  | available |
    And User provides category information
      | id  | name |
      | 123 | dog  |
    And User provides photo URLs
      | src/test/resources/photos/photo1.JPG |
      | src/test/resources/photos/photo2.JPG |
    And User provides tags information
      | id  | name  |
      | 347 | dog   |
      | 348 | corgi |
    When User send "add new pet" request
    Then Request executed with status code 200 in the response
    And Request executed with correct response

  Scenario: Update information about pet in the store
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
    When User send "add new pet" request
    And Request executed with status code 200 in the response
    And Request executed with correct response
    And User provides id, name and status information
      | id    | name | status  |
      | 27894 | Jack | pending |
    And User send "update pet information" request
    Then Request executed with status code 200 in the response
    And Request executed with correct response

  Scenario: Update information about pet in the store with form
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
    When User send "add new pet" request
    And Request executed with status code 200 in the response
    And Request executed with correct response
    And User provides id
      | id    |
      | 22345 |
    And User update name and status with form
      | status | name   |
      | sold   | Tuturu |
    And User send "update pet information with form" request
    Then Request executed with status code 200 in the response
    And Request executed with correct notification or error in the response
      | code | type    | message |
      | 200  | unknown | 22345   |

  Scenario: Upload image of the pet
    Given User provides id, name and status information
      | id    | name  | status    |
      | 93467 | Bobik | available |
    And User provides category information
      | id  | name |
      | 123 | dog  |
    And User provides photo URLs
      | src/test/resources/photos/photo1.JPG |
    And User provides tags information
      | id  | name  |
      | 347 | dog   |
      | 348 | corgi |
    And User send "add new pet" request
    And Request executed with status code 200 in the response
    And Request executed with correct response
    When User upload image of the pet
      | additionalMetadata | file                                 |
      | pet photo          | src/test/resources/photos/photo2.JPG |
    And User provides id
      | id    |
      | 93467 |
    And User send "upload image" request
    Then Request executed with status code 200 in the response
    And Request executed with correct notification or error in the response
      | code | type    | message                                                                   |
      | 200  | unknown | additionalMetadata: pet photo\nFile uploaded to ./photo2.JPG, 17526 bytes |

  Scenario Outline: Find pets by status
    Given User provides statuses
      | status   |
      | <status> |
    When User send "find pet by status" request
    Then Request executed with status code 200 in the response
    And Results contains pets only for provided statuses
    Examples:
      | status                 |
      | available              |
      | pending                |
      | sold                   |
      | available,sold         |
      | available,sold,pending |

  Scenario: Find pet by id
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
      | id    |
      | 22345 |
    And User send "find pet by id" request
    Then Request executed with status code 200 in the response
    And Request executed with correct response

  Scenario: Delete pet by id
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
      | id    |
      | 22345 |
    And User send "delete pet by id" request
    Then Request executed with status code 200 in the response
    And Request executed with correct notification or error in the response
      | code | type    | message |
      | 200  | unknown | 22345   |
