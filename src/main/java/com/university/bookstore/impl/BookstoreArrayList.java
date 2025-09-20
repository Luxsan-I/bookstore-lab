package com.university.bookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Book class.
 */
class BookTest {

    private Book validBook;
    private final String VALID_ISBN_13 = "9781234567897";
    private final String VALID_ISBN_10 = "1234567890";

    /**
     * Sets up a valid Book instance before each test.
     */
    @BeforeEach
    void setUp() {
        validBook = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
    }

    @Test
    void testValidBookCreation() {
        assertNotNull(validBook);
        assertEquals(VALID_ISBN_13, validBook.getIsbn());
        assertEquals("Clean Code", validBook.getTitle());
        assertEquals("Robert Martin", validBook.getAuthor());
        assertEquals(50.0, validBook.getPrice());
        assertEquals(2008, validBook.getYear());
    }

    @Test
    void testIsbn10Format() {
        Book book10 = new Book(VALID_ISBN_10, "Effective Java", "Joshua Bloch", 45.0, 2005);
        assertEquals(VALID_ISBN_10, book10.getIsbn());
    }

    @Test
    void testIsbnCleaning() {
        Book book = new Book("978-1-234-56789-7", "Title", "Author", 20.0, 2020);
        assertEquals("9781234567897", book.getIsbn());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidIsbn(String isbn) {
        assertThrows(IllegalArgumentException.class, () -> new Book(isbn, "Title", "Author", 10.0, 2020));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidTitle(String title) {
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", title, "Author", 10.0, 2020));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testInvalidAuthor(String author) {
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", "Title", author, 10.0, 2020));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -50.0})
    void testNegativePrice(double price) {
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", "Title", "Author", price, 2020));
    }

    @Test
    void testInvalidPriceValues() {
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", "Title", "Author", -0.01, 2020));
    }

    @Test
    void testZeroPrice() {
        Book freeBook = new Book("1234567890", "Free Book", "Author", 0.0, 2020);
        assertEquals(0.0, freeBook.getPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1400})
    void testYearTooEarly(int year) {
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", "Title", "Author", 10.0, year));
    }

    @Test
    void testYearTooLate() {
        int futureYear = 2100;
        assertThrows(IllegalArgumentException.class, () -> new Book("1234567890", "Title", "Author", 10.0, futureYear));
    }

    @Test
    void testValidYearRange() {
        Book book = new Book("1234567890", "Title", "Author", 10.0, 2000);
        assertEquals(2000, book.getYear());
    }

    @Test
    void testEquals() {
        Book book2 = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
        assertEquals(validBook, book2);
        assertNotEquals(validBook, new Book("9999999999999", "Other", "Author", 10.0, 2010));
    }

    @Test
    void testHashCode() {
        Book book2 = new Book(VALID_ISBN_13, "Clean Code", "Robert Martin", 50.0, 2008);
        assertEquals(validBook.hashCode(), book2.hashCode());
    }

    @Test
    void testCompareTo() {
        Book newer = new Book("9781234567800", "Clean Architecture", "Robert Martin", 60.0, 2017);
        assertTrue(validBook.compareTo(newer) < 0);
    }

    @Test
    void testCompareToNull() {
        assertThrows(NullPointerException.class, () -> validBook.compareTo(null));
    }

    @Test
    void testToString() {
        String s = validBook.toString();
        assertTrue(s.contains("Clean Code"));
        assertTrue(s.contains("Robert Martin"));
    }

    @Test
    void testImmutability() {
        assertThrows(UnsupportedOperationException.class, () -> {
            // assuming Book has a method returning modifiable collection, if not, skip
            // validBook.getTags().add("newTag");
        });
    }

    @Test
    void testStringTrimming() {
        Book book = new Book(" 1234567890 ", "  Title  ", "  Author  ", 10.0, 2020);
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
    }

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
