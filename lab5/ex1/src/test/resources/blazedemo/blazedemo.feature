Feature: Book a flight

  Scenario: Choose departure and destination cities
    Given that I'm on "https://blazedemo.com/"
    When I select 'Paris' as departure city
    And I select 'Portland' as destination  city
    And I click the button fo find flights
    Then I should be redirected to "https://blazedemo.com/reserve.php"

  Scenario: Choose a flight
    Given that I'm on "https://blazedemo.com/reserve.php"
    When I decide to take flight 43
    And click the button
    Then I should be redirected to "https://blazedemo.com/purchase.php"

  Scenario: Purchase the flight
    Given that I'm on "https://blazedemo.com/purchase.php"
    When I fill the form with my personal information
    And click the button
    Then I should be redirected to "https://blazedemo.com/confirmation.php"
