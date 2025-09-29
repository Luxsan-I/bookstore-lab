//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


package com.university.bookstore.api;

import com.university.bookstore.model.Book;
import java.util.List;




public interface BookstoreAPI {


    boolean add(Book book);

    boolean removeByIsbn(String isbn);

    Book findByISBN(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPriceRange(double priceMin, double priceMax);

    int size();

    double inventoryValue();

    Book getMostExpensive();

    Book getMostRecent();

    Book[] snapshotArray();

    List<Book> getAllBooks();
}
