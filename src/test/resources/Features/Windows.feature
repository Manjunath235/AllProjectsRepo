@windowsFeature

Feature: POC winappdriver

  @add
  Scenario: Perform addition on windows calculator
    Given calculator open using winappdriver
    When i perform addition action on calculator
    Then I validate the addition outcome
    
  @length
  Scenario: Navigate to length option
    Given i click on left top button on calculator 
    When i navigate to length section and navigate back to main page
    Then verify the home page standard
