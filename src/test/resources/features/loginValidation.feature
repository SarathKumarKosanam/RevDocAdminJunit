Feature: Login field validations

@EmailValidationTest
  Scenario: Validate email and password fields with and without input
    Given I launching the login page
    When I click login without entering any fields
    Then I should see "This field is required" message under email and password
    When I enter invalid email "test@invalid.c" and click login
    Then I should see "This is not a valid email" message
    When I enter email "test@invalid.com" and password "Test@123" and click login
    Then I should see "The email and password combination you entered is incorrect." message
