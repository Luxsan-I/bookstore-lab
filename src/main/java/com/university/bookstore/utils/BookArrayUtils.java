//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



package com.university.bookstore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.university.bookstore.model.Book;
import java.util.*;

public final class BookArrayUtils {
    private BookArrayUtils() {
        throw new Error("Unresolved compilation problems: \n\tThe declared package \"com.university.bookstore.utils\" does not match the expected package \"src.main.java.com.university.bookstore.utils\"\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import java.util cannot be resolved\n\tThe import com cannot be resolved\n\tImplicit super constructor Object() is undefined. Must explicitly invoke another constructor\n\tUnsupportedOperationException cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tString cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tIllegalArgumentException cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tArrays cannot be resolved\n\tBook cannot be resolved to a type\n\tArrays cannot be resolved\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tSystem cannot be resolved\n\tSystem cannot be resolved\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tSet cannot be resolved to a type\n\tString cannot be resolved to a type\n\tHashSet cannot be resolved to a type\n\tList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tArrayList cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tStream cannot be resolved\n\tBook cannot be resolved to a type\n\tMap cannot be resolved to a type\n\tInteger cannot be resolved to a type\n\tInteger cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tMap cannot be resolved to a type\n\tInteger cannot be resolved to a type\n\tInteger cannot be resolved to a type\n\tTreeMap cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tInteger cannot be resolved\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n\tBook cannot be resolved to a type\n");
    }

    public static int countBeforeYear(Book[] var0, int var1) {
        if (var0 == null)  return 0;

        int count = 0;

        for (Book var2 : var0) {
            if (var2 != null && var2.getYear() < var1) {
                count++;
            }
        }

        return count;

    }

    public static int countByAuthor(Book[] var0, String var1) {
        if (var0 == null || var1 == null) {
            return 0;
        }

        int count = 0;
        for (Book var2 : var0) {
            if (var2 != null && var2.getAuthor().equalsIgnoreCase(var1)) {
                count++;
            }
        }

        return count;
    }

    public static Book[] filterPriceAtMost(Book[] var0, double var1) {
        if (var1 < 0) {
            throw new IllegalArgumentException("maxPrice cannot be negative");
        }

        if (var0 == null) {
            return new Book[0];
        }

        int count = 0;

        for (Book var2 : var0) {
            if (var2 != null && var2.getPrice() <= var1) {
                count++;
            }
        }

        Book[] filteredList = new Book[count];
        int hold = 0;

        for (Book var3 : var0) {
            if (var3 != null && var3.getPrice() <= var1) {
                filteredList[hold] = var3;
                hold++;
            }
        }

        return filteredList;
    }

    public static Book[] filterByDecade(Book[] var0, int var1) {

        if (var0 == null) return new Book[0];

        int finalYear = var1 + 9;
        int count = 0;
        for (Book var2 : var0) {
            if (var2 != null && var2.getYear() >= var1 && var2.getYear() <= finalYear) {
                count++;
            }
        }

        Book[] filteredList = new Book[count];
        int hold = 0;

        for (Book var3 : var0) {
            if (var3 != null && var3.getYear() >= var1 && var3.getYear() <= finalYear) {
                filteredList[hold] = var3;
                hold++;
            }
        }

        return filteredList;
    }

    public static void sortByPrice(Book[] var0) {
        if (var0 == null || var0.length <= 1) {
            return;
        }

        Arrays.sort(var0, (Book x, Book y) -> {
            if (x == null && y == null) return 0;
            if (x == null) return 1;
            if (y == null) return -1;
            return Double.compare(x.getPrice(), y.getPrice());
        });
    }

    public static void sortByYear(Book[] var0) {
        if (var0 == null || var0.length <= 1) {
            return;
        }

        Arrays.sort(var0, (Book x, Book y) -> {
            if (x == null && y == null) return 0;
            if (x == null) return 1;
            if (y == null) return -1;
            return Integer.compare(x.getYear(), y.getYear());
        });
    }

    public static double averagePrice(Book[] var0) {
        if (var0 == null) return 0.0;

        double sum = 0.0;
        int count = 0;

        for (Book var2 : var0) {
            if (var2 != null) {
                sum += var2.getPrice();
                count++;
            }
        }
        if (count == 0) return 0.0;
        else return sum / count;
    }

    public static Book findOldest(Book[] var0) {
        if (var0 == null) return null;

        Book oldestAge = null;
        for (Book var2 : var0) {
            if (var2 != null) {
                if (oldestAge == null || var2.getYear() < oldestAge.getYear()) {
                    oldestAge = var2;
                }
            }
        }

        return oldestAge;
       }

    public static Book[] merge(Book[] var0, Book[] var1) {
        int size = 0;
        int size2 = 0;

        if  (var0 != null) size = var0.length;
        if  (var1 != null) size2 = var1.length;

        Book[] copy = new Book[size+size2];

        if (var0 != null) System.arraycopy(var0, 0, copy, 0, size);
        if (var1 != null) System.arraycopy(var1, 0, copy, size, size2);

        return copy;
    }

    public static Book[] removeDuplicates(Book[] var0) {
        if (var0 == null) return new Book[0];

        Set<String> isbns = new HashSet<String>();
        List<Book> newBooks = new ArrayList<Book>();

        for (Book var1 : var0) {
            if (var1 != null && isbns.add(var1.getISBN())) {
                newBooks.add(var1);
            }
        }
        return newBooks.toArray(new Book[0]);

    }


    public static Book[] filterByYearRange(Book[] var0, int start, int end) {
        if (var0 == null) return new Book[0];
        if (start > end) return new Book[0];
        List<Book> newBooks = new ArrayList<Book>();

        for (Book var1 : var0) {
            if (var1 != null && var1.getYear() >= start && var1.getYear() <= end) {
                newBooks.add(var1);
            }
        }
        return newBooks.toArray(new Book[0]);
    }

    public static Map<Integer, Integer> countByDecade(Book[] var0) {
        Map<Integer, Integer> decade = new HashMap<Integer, Integer>();

        if (var0 != null) {
            for (Book var1 : var0) {
                if (var1 != null) {
                    int yearHold = (var1.getYear()/10) * 10;
                    decade.merge(yearHold, 1, Integer::sum);
                }
            }
        }

        return decade;
        }

    public static Book findLongestTitle(Book[] var0) {
        if (var0 == null) return null;

        Book longestTitle = null;
        int longestLength = 0;

        for (Book var1 : var0) {
            if (var1 != null && var1.getTitle().length() > longestLength) {
                longestTitle = var1;
                longestLength = var1.getTitle().length();
            }
        }
        return longestTitle;
    }
}

