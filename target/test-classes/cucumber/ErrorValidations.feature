@tag
Feature: Error validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline:  Negative test of submitting the order
    Given I landed on Ecommerce Page
    When Logged in with <name> and <password>
    Then "Incorrect email or password." something message is displayed

    Examples: 
      | name                  | password  |
      | shilpaam55@gmail.com  | Qwerty1   | 
