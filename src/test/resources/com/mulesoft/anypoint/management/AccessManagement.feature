Feature: Any Point's Access Management
  Scenario: Add Role
    Given I open the login page
      And I sign in with user name "jhoanmanuelms" and password "Admin123"
    When I go to Access Management section
      And I go to "Roles" section
      And I add a role with name "Test Role" and description "This is a test role"
    Then I expect the role "Test Role" was created
