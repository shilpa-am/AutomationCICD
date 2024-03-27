@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with <name> and <password>
    When I add the product <productName> from card
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                  | password | productName |
      | shilpaam55@gmail.com  | Qwerty1! | ZARA COAT 3 |
      
      
      
      
      
      
