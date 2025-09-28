//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.university.bookstore.model;

import java.time.Year;
import java.util.Objects;
import java.util.regex.Pattern;


//Writing JavaDoc

/**
 * Domain model of the project - it represents a real book in the bookstore
 *
 * <p>
 *     Each book has a title, author, year published, price, and unique ISBN
 *     This class is designed to define these fields and validate them
 *     It provides getters and setters for these fields
 * </p>
 *
 * <p>
 *     Instances of this class is intended to be used with Bookstore implementations
 *     like BookstoreArrayList and utility methods in BookArrayUtils
 * </p>
 *
 * @author Ayan Hasan
 * @version 1.0
 * @since 2025-09-28
 *
 */

public final class Book {

    //Constants
    private static final int MIN_YEAR = 1450;

    //ISBN Validation
    private static final Pattern ISBN_13_PATTERN = Pattern.compile("^\\d{13}$");
    private static final Pattern ISBN_10_PATTERN = Pattern.compile("^\\d{9}[\\dX]$");

    //Fields
    //Declared final for immutability (defensive programming)
    private final String title;
    private final String author;
    private final int year;
    private final double price;
    private final String isbn;

    /**
     * Creates a new Book with comprehensive validation
     *
     * @param isbn   the International Standard Book Number (10 or 13 digits)
     * @param title  the book title (non-null, non-blank)
     * @param author the primary author (non-null, non-blank)
     * @param price  the price in dollars (non-negative)
     * @param year   the publication year (1450 to current year + 1)
     * @throws IllegalArgumentException if any parameter is invalid
     * @throws NullPointerException     if any string parameter is null
     */

    //Constructor to initialize the fields while validating them
    public Book(String title, String author, int year, double price, String isbn) {
        this.title = validateStringField(title, "Title");
        this.author = validateStringField(author, "Author");
        this.year = validateYear(year);
        this.price = validatePrice(price);
        this.isbn = validateISBN(isbn);
    }

    /**
     * Validation
     * 1. Validate String Fields (i.e. Title and Author of book)
     * Value cannot be null
     * Value cannot be blank
     */

    private String validateStringField(String value, String fieldName) {
        if (value == null) {
            throw new NullPointerException(fieldName + " cannot be null");
        }

        //Remove empty spaces
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }

        return value.trim();
    }

    /**
     * 2. Validate publication year of the book
     * Has to fall in the range of 1450 and current year + 1
     */

    private int validateYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < MIN_YEAR || year > currentYear + 1) {
            throw new IllegalArgumentException(
                    "Year must be between " + MIN_YEAR + " and " + (currentYear + 1) +
                            ". The value provided: " + year);
        }

        return year;
    }

    /**
     * 3. Validate price of the book
     * It cannot be negative
     * Must be a valid number
     */

    private double validatePrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative. The value provided: " + price);
        }

        if (Double.isNaN(price) || Double.isInfinite(price)) {
            throw new IllegalArgumentException("Price cannot be NaN or Infinite. The value provided: " + price);
        }

        return price;
    }

    /**4. Validate ISBN
     * Must be either 10 or 13 digits and follow the pattern
     */
    private String validateISBN(String isbn) {
        if (isbn == null) {
            throw new NullPointerException("ISBN cannot be null");
        }

        //Remove dashes and trim whitespace
        String cleaned = isbn.replaceAll("-", "").trim();

        if (!ISBN_13_PATTERN.matcher(cleaned).matches() &&
            !ISBN_10_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("ISBN must be 10-digits or 13-digits. The value provided: " + isbn);
        }

        return cleaned;

    }

    /**
     * The Getters for each field
     * There are 5 getters for the 5 fields
     */

    /**
     * 1. Gets the title of this book
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 2. Gets the author of this book
     * @return the author name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 3. Gets the publication year of this book
     * @return the publication year
     */
    public int getYear() {
        return year;
    }

    /**
     *4. Gets the price of the book in the bookstore
     * @returns price in dollars
     */
    public double getPrice() {
        return price;
    }

    /**
     * 5. Gets the ISBN of the book
     * @returns the ISBN
     */

    public String getISBN() {
        return isbn;
    }

    /**
     * Useful Methods: equals, hashCode, toString
     */

    /**
     * Equals method
     * Compares this book to another object for equality
     * They are considered equal if they have the same ISBN
     * @param obj the object to compare with
     * @return true if the other object is a Book with the same ISBN, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return isbn.equals(other.isbn);
    }

    /**
     * Hash code method
     * Generates hash code based on ISBN
     * @return hash code of the ISBN
     */
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    /**
     * toString method
     * Provides a human-readable string representing the book object
     * @return formatted string with all the fields of the book
     */
    @Override
    public String toString() {
        return String.format("Book[Title='%s', Author='%s', Year='%d', Price='%.2f', ISBN='%s' ]",
                title, author, year, price, isbn);
    }

}
