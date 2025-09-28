//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public interface BookstoreAPI {
    static {
        throw new Error("Unresolved compilation problems: \n\tThe type java.lang.Object cannot be resolved. It is indirectly referenced from required .class files\n\tThe declared package \"com.university.bookstore.api\" does not match the expected package \"src.main.java.com.university.bookstore.api\"\n\tThe import java.util cannot be resolved\n\tThe import com cannot be resolved\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n");
    }

    boolean add(Book book);

    boolean removeByIsbn(String isbn);

    Book findByIsbn(String isbn);

    List<book> findByTitle(String title);

    List<book> findByAuthor(String author);

    List<book> findByPriceRange(double priceMin, double priceMax);

    int size();

    double inventoryValue();

    Book getMostExpensive();

    Book getMostRecent();

    Book[] snapshotArray();

    List<Book> getAllBooks();
}
