Feature: TypeProduct registration

  Scenario: TypeProduct is not registered
    Given typeProduct is unknown
    When typeProduct is registered with success
    Then typeProduct is known