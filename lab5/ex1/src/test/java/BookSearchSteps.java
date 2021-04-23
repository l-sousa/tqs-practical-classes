import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class BookSearchSteps {
    private Book book;
    private Library library;

    @Given("a book with the title {string}, written by {string}, published in {string}")
    public void setup(String title, String author, String published) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(published);

        book = new Book(title, author, date);
        this.library = new Library();

        library.addBook(book);
    }

    @When("the customer searched a book with title {string}")
    public void the_customer_searched_a_book_with_title(String string) {
        library.findBooksByTitle(string);
    }

    @When("the customer searched for books written by {string}")
    public void the_customer_searched_for_books_written_by(String string) {
        library.findBooksOfAuthor(string);

    }

    @Then("Book {int} should have the author {string}")
    public void book_should_have_the_author(Integer int1, String string) {
        assertEquals(library.getStore().get(int1).getAuthor(), string);
    }


    @When("the customer searches for books published between {string} and {string}")
    public void the_customer_searches_for_books_published_between_and(String s1, String s2) throws ParseException {
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(s2);

        Library.findBooks(d1, d2);

    }

    @Then("{int} book should have been found")
    public void book_should_have_been_found(Integer int1) {
        List<Book> store = library.getStore();
        assertEquals(int1, store.size());
    }

    @Then("Book {int} should have the title {string}")
    public void book_should_have_the_title(Integer int1, String string) {
        List<Book> store = library.getStore();
        assertEquals(store.get(int1).getTitle(), string);
    }

}
