import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private static final List<Book> store = new ArrayList<>();

    public Library() {
        store.clear();
    }

    public void addBook(final Book book) {
        store.add(book);
    }

    public static List<Book> findBooks(final Date from, final Date to) {
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        end.roll(Calendar.YEAR, 1);
        return store.stream().filter(book ->
        {
            return from.before(book.getPublished()) && end.getTime().after(book.getPublished());
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public static List<Book> findBooksOfAuthor(String author) {

        return store.stream().filter(book ->
        {
            return book.getAuthor().equals(author);
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public static List<Book> findBooksByTitle(String title) {

        return store.stream().filter(book ->
        {
            return book.getTitle().equals(title);
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public List<Book> getStore() {
        return store;
    }

}