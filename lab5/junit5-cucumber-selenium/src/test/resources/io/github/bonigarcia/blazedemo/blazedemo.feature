Feature: Book a flight

  Scenario: Choose departure and destination cities
    Given that I'm on "https://blazedemo.com/"
    When I select 'Portland' as departure city
    And I select 'Berlin' as destination  city
    And I click the button fo find flights
    Then I should be redirected to "https://blazedemo.com/reserve.php"

  Scenario: Choose a flight
    Given that I'm on "https://blazedemo.com/reserve.php"
    When I click the button to choose flight
    Then I should be redirected to "https://blazedemo.com/purchase.php"

  Scenario: Purchase the flight
    Given that I'm on "https://blazedemo.com/purchase.php"
    When I fill the form with my personal information
    And click the button to purchase the flight
    Then I should be redirected to "https://blazedemo.com/confirmation.php"
