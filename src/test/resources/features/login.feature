Feature: Login
  As a mobile app user
  I want to sign in
  So that I can access the products page

  Scenario: TC_001 Login with valid credentials
    Given the login screen is displayed
    When I log in as "standard_user" with password "secret_sauce"
    Then the products page is displayed

  Scenario: TC_002 Login with an invalid username
    Given the login screen is displayed
    When I log in as "standard_user2" with password "secret_sauce"
    Then I see the login error "Username and password do not match any user in this service."

  Scenario: TC_003 Login with empty username and password
    Given the login screen is displayed
    When I log in as "" with password ""
    Then I see the empty fields error "Username is required"

  Scenario: TC_004 Logout returns to the login screen
    Given the login screen is displayed
    When I log in as "standard_user" with password "secret_sauce"
    And I log out
    Then I am returned to the login screen