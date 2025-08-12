Feature: Login feature

  Scenario: Valid login
    Given I launch the login page
    When I login with username "rushendra@quadricit.com" password "Rushidoddoji1!" and OTP "123456"
    Then I should be logged in successfully
