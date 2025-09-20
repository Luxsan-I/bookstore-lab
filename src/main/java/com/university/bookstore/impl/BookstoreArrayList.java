package com.university.bookstore.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.stream.Collectors;

import com.university.bookstore.model.Book;

/**
 * ArrayList-based implementation of a bookstore.
 * Stores Book objects in a list and enforces unique ISBNs.
 */
public class BookstoreArrayList {

    private final List<Book> inventory;

    /**
     * Default constructor creates empty inventory.
     */
    public BookstoreArrayList() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Constructor with initial collection of books.
     *
     * @param books initial books to add
     */
    public BookstoreArrayList(Collection<Book> books) {
        this.inventory = new ArrayList<>();
        if (books != null) {
            for (Book book : books) {
                add(book);
            }
        }
    }

    /**
     * Adds a book to inventory if ISBN is unique.
     *
     * @param book Book to add
     * @return true if added, false if null or duplicate ISBN
     */
    public boolean add(Book book) {
        if (book == null || findByIsbn(book.getIsbn()) != null) return false;
        return inventory.add(book);
    }

    /**
     * Removes a book by ISBN.
     *
     * @param isbn ISBN of book to remove
     * @return true if removed, false if not found
     */
    public boolean removeByIsbn(String isbn) {
        return inventory.removeIf(book -> book.getIsbn().equals(isbn));
    }

    /**
     * Finds a book by ISBN.
     *
     * @param isbn ISBN to search
     * @return matching Book or null
     */
    public Book findByIsbn(String isbn) {
        for (Book book : inventory) {
            if (book.getIsbn().equals(isbn)) return book;
        }
        return null;
    }

    /**
     * Finds books containing title substring.
     *
     * @param title query
     * @return list of matching books
     */
    public List<Book> findByTitle(String title) {
        if (title == null) return Collections.emptyList();
        String query = title.toLowerCase();
        return inventory.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    /**
     * Finds books containing author substring.
     *
     * @param author query
     * @return list of matching books
     */
    public List<Book> findByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        String query = author.toLowerCase();
        return inventory.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    /**
     * Finds books within price range.
     *
     * @param min minimum price
     * @param max maximum price
     * @return list of matching books
     */
    public List<Book> findByPriceRange(double min, double max) {
        if (min > max) throw new IllegalArgumentException("Invalid price range");
        return inventory.stream()
                .filter(book -> book.getPrice() >= min && book.getPrice() <= max)
                .collect(Collectors.toList());
    }

    /**
     * Finds books published in a given year.
     *
     * @param year year
     * @return list of matching books
     */
    public List<Book> findByYear(int year) {
        return inventory.stream()
                .filter(book -> book.getYear() == year)
                .collect(Collectors.toList());
    }

    /**
     * Returns number of books.
     *
     * @return inventory size
     */
    public int size() {
        return inventory.size();
    }

    /**
     * Returns total value of inventory.
     *
     * @return sum of prices
     */
    public double inventoryValue() {
        return inventory.stream().mapToDouble(Book::getPrice).sum();
    }

    /**
     * Returns the most expensive book.
     *
     * @return book with highest price
     */
    public Book getMostExpensive() {
        return inventory.stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .orElse(null);
    }

    /**
     * Returns the most recent book.
     *
     * @return book with latest year
     */
    public Book getMostRecent() {
        return inventory.stream()
                .max(Comparator.comparingInt(Book::getYear))
                .orElse(null);
    }

    /**
     * Returns snapshot of inventory as array.
     *
     * @return array of books
     */
    public Book[] snapshotArray() {
        return inventory.toArray(new Book[0]);
    }

    /**
     * Returns copy of all books.
     *
     * @return list of books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(inventory);
    }

    /**
     * Clears the inventory.
     */
    public void clear() {
        inventory.clear();
    }

    /**
     * Sorts inventory by title.
     */
    public void sortByTitle() {
        Collections.sort(inventory);
    }

    /**
     * Sorts inventory by price ascending.
     */
    public void sortByPrice() {
        inventory.sort(Comparator.comparingDouble(Book::getPrice));
    }

    /**
     * Sorts inventory by year ascending.
     */
    public void sortByYear() {
        inventory.sort(Comparator.comparingInt(Book::getYear));
    }

    /**
     * Returns statistics about inventory.
     *
     * @return map with size, total_value, average_price, min_year, max_year, unique_authors
     */
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("size", size());
        stats.put("total_value", inventoryValue());
        stats.put("average_price", size() == 0 ? 0 : inventoryValue() / size());
        stats.put("min_year", inventory.stream().mapToInt(Book::getYear).min().orElse(0));
        stats.put("max_year", inventory.stream().mapToInt(Book::getYear).max().orElse(0));
        stats.put("unique_authors", inventory.stream().map(Book::getAuthor).distinct().count());
        return stats;
    }

    /**
     * String summary of inventory.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("BookstoreArrayList[size=%d, value=%.2f]", size(), inventoryValue());
    }
}
