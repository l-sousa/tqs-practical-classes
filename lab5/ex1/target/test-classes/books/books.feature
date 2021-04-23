Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
  Background:
    Given a book with the title 'One good book', written by 'Anonymous', published in '3/10/2013'

  Scenario: Search books by publication year
    When the customer searches for books published between '1/1/2013' and '31/12/2014s'
    Then 1 book should have been found
    And Book 0 should have the title 'One good book'

  Scenario:
    When the customer searched for books written by 'Anonymous'
    Then 1 book should have been found
    And Book 0 should have the author 'Anonymous'

  Scenario:
    When the customer searched a book with title 'One good book'
    Then 1 book should have been found
    And Book 0 should have the title 'One good book'

