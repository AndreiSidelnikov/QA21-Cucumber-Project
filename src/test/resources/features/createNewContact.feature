Feature: Navigate

  @createNewContact

  Scenario: Add contact with valid data
    Given Navigate to Page PhoneBook
    When Click on Login tab
    And Enter a valid data
    And Click on Login button
    And click On Add Tab
    And enter a valid data of a contact
    And Click on Save button
    Then is Contact Created