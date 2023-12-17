Feature: User registration

  Scenario: User is not registered
    Given user is unknown
    When user is registered with success
    Then user is known

#  Scenario: Customer without document
#    Given customer without document
#    When customer failed to register
#    Then notify document must be not null
#    And customer is still unknown