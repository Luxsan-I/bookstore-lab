# Bookstore Management System

## Contact
221331327, yuzhelin@gmail.com, Yuzhe, Lin

## Project Overview

This projects create a Bookstore Management System using software engineering principles which includes encapsulation, immutability, defensive programming, and thorough testing. This projects provides too ways to mangage asort of books of both Array list and array.

## Architecture
├──Model Layer (Book.java)
│   |--Immutable object with validation
├── API Layer (BookstoreAPI.java)
│   |-- Interface to Bookstore operations
├── Implementation Layer
│   |-- BookstoreArrayList.java - Impletement throught array list
│   |-- BookArrayUtils.java - Impletement throught array
├── Test Layer
│   |-- Junit 5 Test for objects

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

## Testing Strategy

## Reflection Answers
