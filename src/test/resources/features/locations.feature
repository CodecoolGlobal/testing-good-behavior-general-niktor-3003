@ui
Feature: Location selection
  As a player
  I want to see the available locations
  So that I can choose where to explore

  Scenario: Display 20 locations
    Given I am on the location selection page
    Then I should see 20 locations

  Scenario: Start an encounter by selecting a location
    Given I am on the location selection page
    When I select the location "canalave-city"
    Then the location list should not be visible
    And the encountered Pokemon's name and image should be visible

  Scenario: Select an owned Pokemon before battle
    Given I am on the location selection page
    When I select the location "canalave-city"
    And I select my Pokemon "bulbasaur"
    Then my Pokemon "bulbasaur" should be selected
    And the encountered Pokemon's name and image should be visible

  Scenario: Return to locations without starting a battle
    Given I am on the location selection page
    When I select the location "canalave-city"
    And I select my Pokemon "bulbasaur"
    And I leave the encounter
    Then I should see 20 locations
    And the encounter should not be visible

  Scenario: Display the required no-encounter message
    Given I am on the location selection page
    When I select the location "spring-path"
    Then a no-encounter message should be visible
    And the no-encounter message should match the requirement

  Scenario: Return from a location without Pokemon
    Given I am on the location selection page
    When I select the location "spring-path"
    Then a no-encounter message should be visible
    When I leave the encounter
    Then I should see 20 locations
    And the encounter should not be visible
