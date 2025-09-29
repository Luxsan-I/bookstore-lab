# Bookstore Management System

## Contact
221331327, yuzhelin@gmail.com, Yuzhe, Lin
221633680, abelb23@my.yorku.ca, Abel Berhanu

## Project Overview

This projects create a Bookstore Management System using software engineering principles which includes encapsulation, immutability, defensive programming, and thorough testing. This projects provides too ways to mangage asort of books of both Array list and array.

## Architecture
```
├──Model Layer (Book.java)
│   └── Immutable object with validation
├──API Layer (BookstoreAPI.java)
│   └── Interface to Bookstore operations
├──Implementation Layer
│   └── BookstoreArrayList.java - Impletement throught array list
│   └── BookArrayUtils.java - Impletement throught array
└── Test Layer
    └──Junit 5 Test for objects
```

## Build Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Quick Start
```bash
cd bookstore-lab
./runme.sh
mvn clean compile
mvn test
mvn javadoc:javadoc
```

### Maven Commands
```bash
mvn clean test jacoco:report
mvn javadoc:javadoc
mvn package
mvn test -Dtest=BookTest
mvn test -X
```

### Build Script
The project includes a comprehensive build script (`runme.sh`) that:
- Validates environment (Java 17+, Maven 3.6+)
- Compiles source code
- Runs all 95 tests with JaCoCo coverage
- Generates Javadoc documentation
- Creates JAR package
- Provides detailed status reporting

```bash
./runme.sh
./runme.sh --test-only
./runme.sh --skip-test
./runme.sh --help
```

### Coverage Analysis
- **Target**: 85% coverage (configurable)
- **Primary Metric**: Instruction coverage (97%)
- **Fallback**: Custom coverage estimation script
- **Reports**: HTML, CSV, and XML formats in `target/site/jacoco/`

## Usage Examples

### Basic Operations
```java
BookstoreAPI book_store = new BookstoreArrayList();

Book harry_porter = new Book("9780134685991", "Harry Porter", 
                              "JK R", 69.99, 2018);
book_store.add(harry_porter);

// Search operations
Book found = book_store.findByIsbn("9780134685991");
List<Book> javaBooks = book_store.findByTitle("Porter");
List<Book> affordable = book_store.findByPriceRange(0, 50.00);

// Analytics
double totalValue = book_store.inventoryValue();
Book mostExpensive = book_store.getMostExpensive();
```

### Array Utilities
```java
Book[] books = book_store.snapshotArray();

// Filter operations
Book[] cheapBooks = BookArrayUtils.filterPriceAtMost(books, 30.0);
Book[] recentBooks = BookArrayUtils.filterByDecade(books, 2020);

// Sorting (in-place)
BookArrayUtils.sortByPrice(books);
BookArrayUtils.sortByYear(books);

// Analytics
double avgPrice = BookArrayUtils.averagePrice(books);
Book oldest = BookArrayUtils.findOldest(books);
```

## Performance Characteristics

| Operation | ArrayList | Array | Notes |
|-----------|-----------|-------|-------|
| Add | O(1)* | O(n) | *Amortized, O(n) for uniqueness check |
| Remove | O(n) | O(n) | Linear search required |
| Find by ISBN | O(n) | O(n) | No index structure |
| Find by Title | O(n) | O(n) | Full scan needed |
| Get by Index | O(1) | O(1) | Direct access |
| Size | O(1) | O(1) | Maintained/fixed |

## Design Decisions

### 1. Immutability
The `Book` class is immutable, it has`final` class, `final` fields, andno setters, which provides thread safety without synchronization and prevention of accidental modification.

### 2. Defensive Programming
The defensive copies returned from all collection getters, it made sure that the editing will not destroy the original collective.

### 3. ISBN as Identity
Books use ISBN to check each book's uniqueness, which makes sure that each Book object can found without errors.

## Testing Strategy

### Coverage Statistics
- **Overall Coverage**: 97% (exceeds 85% target)
- **Book Class**: 100% coverage (33 tests)
- **BookstoreArrayList**: 97% coverage (33 tests)
- **BookArrayUtils**: 97% coverage (29 tests)

### Test Categories
1. **Validation Tests**: All input validation rules
2. **Boundary Tests**: Empty collections, single elements, nulls
3. **Contract Tests**: equals/hashCode consistency, Comparable
4. **Performance Tests**: Large dataset handling (1000+

## Reflection Answers

### 1. Array vs ArrayList: When to Use Each?

**If users wants to make things convennient, they can user the arraylist. However, users are recommonded to use the array when they need to spent more cpu and mermory. Array elements are saved continously so it is easier to search through the mermory, arraylist elements requires more bytes.**

### 2. Amortized O(1) Analysis of ArrayList.add()
**When the arraylist is not full, just add the new element to the end, the time complexity becames O(1). If it is full, need to move the entire old array list to the new array list, it becomes O(n), but under most cases it is O(1). Averagely speaking, the cost per element is O(1).**

### 3. Defensive Copying Importance

**Defensive copying:**
The defensive copy prevents modifying internal private properties of an objects, maintaining the encapsulation.

