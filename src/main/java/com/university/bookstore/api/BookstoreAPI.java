//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package src.main.java.com.university.bookstore.api;

public interface BookstoreAPI {
    static {
        throw new Error("Unresolved compilation problems: \n\tThe type java.lang.Object cannot be resolved. It is indirectly referenced from required .class files\n\tThe declared package \"com.university.bookstore.api\" does not match the expected package \"src.main.java.com.university.bookstore.api\"\n\tThe import java.util cannot be resolved\n\tThe import com cannot be resolved\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n");
    }

    boolean add(Book var1);

    boolean removeByIsbn(String var1);

    Book findByIsbn(String var1);

    List<Book> findByTitle(String var1);

    List<Book> findByAuthor(String var1);

    List<Book> findByPriceRange(double var1, double var3);

    List<Book> findByYear(int var1);

    int size();

    double inventoryValue();

    Book getMostExpensive();

    Book getMostRecent();

    Book[] snapshotArray();

    List<Book> getAllBooks();
}
