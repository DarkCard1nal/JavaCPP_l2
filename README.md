# JavaCPP_l2
_Created for the course "Cross-platform programming" V. N. Karazin Kharkiv National University_

JJava 11 program for testing java.io.Serializable and java.io.Externalizable interfaces

The source codes of the classes can be found in _l1\src\_

___

# All versions of the program:
The `Library` class contains a list of books and their authors, readers, and the name of the library.
The authors of the books `_authorsList` is automatically generated from the books `_booksList`, it does not need to be specified, use it to get references to authors already in the library (to avoid duplication), but keep in mind that this field does not contain authors of books that are in the readers `_bookReadersList` (i.e. not in the library).
Constructors:
`public Library(String name)`
`public Library(String name, ArrayList<Book> booksList, ArrayList<BookReader> bookReadersList)`
Methods:
`public void setName(String name)`
`public String getName()`
`public ArrayList<Book> getBooksList()` - returns a list of books in the library `_booksList`
`public ArrayList<BookReader> getBookReadersList()` - returns a list of readers of the library `_bookReadersList`
`public ArrayList<Author> getAuthorsList()` - returns a list of authors of the library `_authorsList`
`public void addBookReader(BookReader bookReader)` - adds a new reader `bookReader` to the `_bookReadersList`
`public void addBookReader(String firstName, String lastName, int id)` - adds a new reader with parameters to the `_bookReadersList`
`public void addBook(Book book)` - adds a `book` to the `_booksList`
`public void addBook(String title, ArrayList<Author> authors, int year, int edition)` - adds a book with parameters to the `_booksList`
`public Author findAuthor(String firstName, String lastName)` - returns the Author who has firstName and lastName from the `_authorsList` regardless of the registry or a new one, if not found, use to check the existence of the author in the library
`public BookReader findBookReader(int id)` - returns the reader with `id` from the `_bookReadersList`, or `null` if not found
`public boolean borrowBook(BookReader bookReader, Book book)` - borrows a `book` from the reader `bookReader`, returns `true` if successful
`public boolean returnBorrowBook(BookReader bookReader, Book book)` - returns the `book` borrowed by `bookReader`, returns `true` if successful
`public String toString()` - returns a `String` of the format `"Library: name - 'this._name', booksList - _booksList.size(), bookReadersList - _bookReadersList.size(), authorsList - this._authorsList.size()"`

The `Book` class contains the title, list of authors, year, and edition number.
Constructors:
`public Book(String title, ArrayList<Author> authors, int year, int edition)`
Methods:
`public String getTitle()`
`public ArrayList<Author> getAuthors()` - returns a list of authors `_authors` of the book
`public String toString()` - returns a `String` of format `"Book: title - this._title, authors - this._authors.toString(), year - this._year, edition - this._edition;"`

The `Human` class is `abstract` and contains the first and last name of a person:
Constructors:
`public Human(String firstName, String lastName)`
Methods:
`public String getFirstName()`
`public String getLastName()`
`public String toString()` - returns a `String` of type `"FirstName LastName"`

The `Author` class inherits from the `Human` class.
Methods class:
`public ArrayList<Book> findBooks(Library lib)` - returns a list of `Books` from the books of the library `lib` in which the given author is present.

The `BookReader` class inherits the `Human` class and additionally contains the registration number (id) of the reader and the list of books borrowed from the library.
Constructors:
`public BookReader (String firstName, String lastName, int id)`
`public BookReader (String firstName, String lastName, int id, ArrayList<Book> receiveBooks)`
Methods:
`public int getId()` - returns the registration number
`public ArrayList<Book> getReceiveBooks()` - returns the `Book` list of borrowed books
`public void addReceiveBook(Book book)` - adds `book` to the list of borrowed `_receiveBooks`
`public void delReceiveBook(Book book)` - removes `book` from the list of borrowed `_receiveBooks`

# Examples and tests are contained in the `Test` class of the program version.

## Version `1` of the `src/v1` program fully implements the `java.io.Serializable` interface. 
Results of the `Test` execution:
```java
Object libv1.ser is not found

Library: name - 'My Library v1', booksList - 0, bookReadersList - 0, authorsList - 0

Library: name - 'My Library v1', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v1', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v1', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]
BookReader 0: 'Book Reader0'
[]
Object libv1.ser serialized successfully

Object libv1.ser deserialized successfully

Library: name - 'My Library v1', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v1', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v1', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]
BookReader 0: 'Book Reader0'
[]
Object libv1.ser serialized successfully
```

## Version `2` of the `src/v2` program partially implements the `java.io.Serializable interface`, manually saves objects whose classes do not implement the `java.io.Serializable` interface.
Results of the `Test` execution:
```java
Object libv2.ser is not found

Library: name - 'My Library v2', booksList - 0, bookReadersList - 0, authorsList - 0

Library: name - 'My Library v2', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v2', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v2', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]
BookReader 0: 'Book Reader0'
[]
Object libv2.ser serialized successfully

Object libv2.ser deserialized successfully

Library: name - 'My Library v2', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v2', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v2', booksList - 3, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
Object libv2.ser serialized successfully
```

## Version `3` of the `src/v3` program fully implements the `java.io.Externalizable` interface (similar to version `2` of the program).
Results of the `Test` execution:
```java
Object libv3.ser is not found

Library: name - 'My Library v3', booksList - 0, bookReadersList - 0, authorsList - 0

Library: name - 'My Library v3', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v3', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v3', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]
BookReader 0: 'Book Reader0'
[]
Object libv3.ser serialized successfully

Object libv3.ser deserialized successfully

Library: name - 'My Library v3', booksList - 3, bookReadersList - 2, authorsList - 3
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1, Author 2]

Author 0:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]

BookReader 0 <- Book 2 <- lib

Library: name - 'My Library v3', booksList - 2, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]

BookReader 0 -> Book 0 -> lib

Library: name - 'My Library v3', booksList - 3, bookReadersList - 2, authorsList - 2
Books:
[Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;, Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book0, authors - [Author 0], year - 2024, edition - 3;]
BookReaders:
[Book Reader0, Book Reader1]
Author:
[Author 0, Author 1]
BookReader 0: 'Book Reader0'
[Book: title - Book1, authors - [Author 0, Author 1], year - 2024, edition - 4;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;, Book: title - Book2, authors - [Author 2], year - 2024, edition - 1;]
Object libv3.ser serialized successfully
```
