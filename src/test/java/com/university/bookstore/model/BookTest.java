package com.university.bookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Book} class.
 * Tests cover:
 * <ul>
 *     <li>Valid and invalid creation of Book objects</li>
 *     <li>ISBN, title, author, price, and year validation</li>
 *     <li>Equality, hashCode, compareTo, and toString methods</li>
 *     <li>Trimming of strings and immutability</li>
 *     <li>Performance of object creation</li>
 * </ul>
 */
class BookTest {

    /** Valid book instance used for testing */
    private Book validBook;

    /** Example valid ISBN-13 */
    private final String VALID_ISBN_13 = "9781234567897";

    /** Example valid ISBN-10 */
    private final String VALID_ISBN_10 = "1234567890";

    /**
     * Sets up a valid {@link Book} instance before each test.
     */
    @BeforeEach
    void setUp() {
        validBook = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
    }

    /**
     * Tests that a valid book is correctly created with all fields.
     */
    @Test
    void testValidBookCreation() {
        assertNotNull(validBook);
        assertEquals(VALID_ISBN_13, validBook.getIsbn());
        assertEquals("Clean Code", validBook.getTitle());
        assertEquals("Robert Martin", validBook.getAuthor());
        assertEquals(50.0, validBook.getPrice());
        assertEquals(2008, validBook.getYear());
    }

    /**
     * Tests ISBN-10 creation and format.
     */
    @Test
    void testIsbn10Format() {
        Book book10 = new Book(VALID_ISBN_10, "Effective Java", "Joshua Bloch", 45.0, 2005);
        assertEquals(VALID_ISBN_10, book10.getIsbn());
    }

    /**
     * Tests that ISBN strings are cleaned of dashes or whitespace.
     */
    @Test
    void testIsbnCleaning() {
        Book book = new Book("978-1-234-56789-7", "Title", "Author", 20.0, 2020);
        assertEquals("9781234567897", book.getIsbn());
    }

    /**
     * Tests that invalid ISBN values throw IllegalArgumentException.
     *
     * @param isbn invalid ISBN values to test
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidIsbn(String isbn) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book(isbn, "Title", "Author", 10.0, 2020));
    }

    /**
     * Tests that invalid book titles throw IllegalArgumentException.
     *
     * @param title invalid title values
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidTitle(String title) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", title, "Author", 10.0, 2020));
    }

    /**
     * Tests that invalid authors throw IllegalArgumentException.
     *
     * @param author invalid author values
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidAuthor(String author) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", "Title", author, 10.0, 2020));
    }

    /**
     * Tests that negative price values throw IllegalArgumentException.
     *
     * @param price negative price values
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -50.0})
    void testNegativePrice(double price) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", "Title", "Author", price, 2020));
    }

    /**
     * Tests invalid price edge cases.
     */
    @Test
    void testInvalidPriceValues() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", "Title", "Author", -0.01, 2020));
    }

    /**
     * Tests that zero price is allowed.
     */
    @Test
    void testZeroPrice() {
        Book freeBook = new Book("1234567890", "Free Book", "Author", 0.0, 2020);
        assertEquals(0.0, freeBook.getPrice());
    }

    /**
     * Tests years that are too early throw exception.
     *
     * @param year invalid early year
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1400})
    void testYearTooEarly(int year) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", "Title", "Author", 10.0, year));
    }

    /**
     * Tests years that are too late throw exception.
     */
    @Test
    void testYearTooLate() {
        int futureYear = 2100;
        assertThrows(IllegalArgumentException.class, () ->
                new Book("1234567890", "Title", "Author", 10.0, futureYear));
    }

    /**
     * Tests valid year range is accepted.
     */
    @Test
    void testValidYearRange() {
        Book book = new Book("1234567890", "Title", "Author", 10.0, 2000);
        assertEquals(2000, book.getYear());
    }

    /**
     * Tests equality of two books with the same ISBN.
     */
    @Test
    void testEquals() {
        Book book2 = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
        assertEquals(validBook, book2);
        assertNotEquals(validBook, new Book("9999999999999", "Other", "Author", 10.0, 2010));
    }

    /**
     * Tests that hashCode is consistent for equal books.
     */
    @Test
    void testHashCode() {
        Book book2 = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
        assertEquals(validBook.hashCode(), book2.hashCode());
    }

    /**
     * Tests compareTo method with newer book.
     */
    @Test
    void testCompareTo() {
        Book newer = new Book("9781234567800", "Clean Architecture", "Robert Martin", 60.0, 2017);
        assertTrue(validBook.compareTo(newer) < 0);
    }

    /**
     * Tests compareTo throws exception when comparing to null.
     */
    @Test
    void testCompareToNull() {
        assertThrows(NullPointerException.class, () -> validBook.compareTo(null));
    }

    /**
     * Tests toString output contains title and author.
     */
    @Test
    void testToString() {
        String s = validBook.toString();
        assertTrue(s.contains("Clean Code"));
        assertTrue(s.contains("Robert Martin"));
    }

    /**
     * Tests immutability of internal collections if any.
     */
    @Test
    void testImmutability() {
        assertThrows(UnsupportedOperationException.class, () -> {
            // Modify internal collection if Book exposes one
            // validBook.getTags().add("newTag");
        });
    }

    /**
     * Tests that input strings are trimmed.
     */
    @Test
    void testStringTrimming() {
        Book book = new Book(" 1234567890 ", "  Title  ", "  Author  ", 10.0, 2020);
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
    }

    /**
     * Tests that creating a large number of books is performant.
     */
    @Test
    void testCreationPerformance() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            new Book("1234567890" + i, "Title", "Author", 10.0, 2020);
        }
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration < 1000, "Book creation too slow");
    }
}
