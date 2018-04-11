Feature: Browsing around

  Scenario: Login and land on home page
    Given I login to "PetClinic" application
    And I land on the "PetClinic" home page

  Scenario: Login and Vets
    Given I login to "PetClinic" application
    Then I search for "Veterinarians"
    And I get a list of "Veterinarians"

  Scenario: Login and check owner based on last name
    Given I login to "PetClinic" application
    And I search owner "Franklin"
    And I get owner "Franklin" Informations