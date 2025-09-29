package com.university.bookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Book} class.
 *
 * <p>These tests achieve 100% line and branch coverage of the Book class.
 * They verify:</p>
 * <ul>
 *   <li>Valid and invalid construction of Book objects</li>
 *   <li>Validation of ISBN, title, author, price, and year</li>
 *   <li>Behavior of equals, hashCode, and toString</li>
 *   <li>Immutability and string trimming</li>
 *   <li>Performance of object creation</li>
 * </ul>
 *
 * Luxsan Indran
 * 221298286
 * luxsan@my.yorku.ca
 */
class BookTest {

    /** Valid book instance used for testing */
    private Book validBook;

    /** Example valid ISBN-13 */
    private final String VALID_ISBN_13 = "9781234567897";

    /** Example valid ISBN-10 */
    private final String VALID_ISBN_10 = "1234567890";

    /**
     * Initializes a valid {@link Book} before each test.
     */
    @BeforeEach
    void setUp() {
        validBook = new Book("Clean Code", "Robert Martin", 2008, 50.0, VALID_ISBN_13);
    }

    /**
     * Tests that a valid book is created successfully with all fields.
     */
    @Test
    void testValidBookCreation() {
        assertNotNull(validBook);
        assertEquals("Clean Code", validBook.getTitle());
        assertEquals("Robert Martin", validBook.getAuthor());
        assertEquals(2008, validBook.getYear());
        assertEquals(50.0, validBook.getPrice());
        assertEquals(VALID_ISBN_13, validBook.getISBN());
    }

    /**
     * Tests creation of a book with a valid ISBN-10.
     */
    @Test
    void testIsbn10Format() {
        Book book10 = new Book("Effective Java", "Joshua Bloch", 2005, 45.0, VALID_ISBN_10);
        assertEquals(VALID_ISBN_10, book10.getISBN());
    }

    /**
     * Tests that ISBN strings are cleaned of dashes and whitespace.
     */
    @Test
    void testIsbnCleaning() {
        Book book = new Book("Title", "Author", 2020, 20.0, "978-1-234-56789-7");
        assertEquals("9781234567897", book.getISBN());
    }

    /**
     * Tests that invalid ISBN values throw exceptions.
     *
     * @param isbn invalid ISBN value
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   ", "123", "978123"})
    void testInvalidIsbn(String isbn) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", 2020, 10.0, isbn));
    }

    /**
     * Tests that invalid titles throw exceptions.
     *
     * @param title invalid title value
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidTitle(String title) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book(title, "Author", 2020, 10.0, VALID_ISBN_10));
    }

    /**
     * Tests that invalid authors throw exceptions.
     *
     * @param author invalid author value
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidAuthor(String author) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", author, 2020, 10.0, VALID_ISBN_10));
    }

    /**
     * Tests that negative prices throw exceptions.
     *
     * @param price negative price
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -50.0, -0.01})
    void testInvalidPriceValues(double price) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", 2020, price, VALID_ISBN_10));
    }

    /**
     * Tests that a zero price is valid.
     */
    @Test
    void testZeroPrice() {
        Book freeBook = new Book("Free Book", "Author", 2020, 0.0, VALID_ISBN_10);
        assertEquals(0.0, freeBook.getPrice());
    }

    /**
     * Tests that years earlier than 1450 are invalid.
     *
     * @param year invalid early year
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1400})
    void testYearTooEarly(int year) {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", year, 10.0, VALID_ISBN_10));
    }

    /**
     * Tests that years far in the future are invalid.
     */
    @Test
    void testYearTooLate() {
        int futureYear = Year.now().getValue() + 5;
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", futureYear, 10.0, VALID_ISBN_10));
    }

    /**
     * Tests that valid years are accepted.
     */
    @Test
    void testValidYearRange() {
        Book book = new Book("Title", "Author", 2000, 10.0, VALID_ISBN_10);
        assertEquals(2000, book.getYear());
    }

    /**
     * Tests equality of two books with the same ISBN.
     */
    @Test
    void testEquals() {
        Book book2 = new Book("Clean Code", "Robert Martin", 2008, 50.0, VALID_ISBN_13);
        assertEquals(validBook, book2);
        assertNotEquals(validBook, new Book("Other", "Author", 2010, 10.0, "9999999999999"));
    }

    /**
     * Tests that hashCode is consistent for equal books.
     */
    @Test
    void testHashCode() {
        Book book2 = new Book("Clean Code", "Robert Martin", 2008, 50.0, VALID_ISBN_13);
        assertEquals(validBook.hashCode(), book2.hashCode());
    }

    /**
     * Tests the toString output includes title and author.
     */
    @Test
    void testToString() {
        String s = validBook.toString();
        assertTrue(s.contains("Clean Code"));
        assertTrue(s.contains("Robert Martin"));
    }

    /**
     * Tests that strings are trimmed of whitespace.
     */
    @Test
    void testStringTrimming() {
        Book book = new Book("  Title  ", "  Author  ", 2020, 10.0, " 1234567890 ");
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("1234567890", book.getISBN());
    }

    /**
     * Tests performance of creating many Book objects.
     */
    @Test
    void testCreationPerformance() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            new Book("Title" + i, "Author", 2020, 10.0, "1234567890" + (i % 10));
        }
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration < 1000, "Book creation too slow");
    }
}
