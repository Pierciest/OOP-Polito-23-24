Library
==============

Write a program to support book rentals and archiving in a public library.
Classes are located in the `it.polito.library` package; the main class is `LibraryManager`. The `TestExample` class in the `example` package shows usage examples for the main methods and examples of the requested checks. You are required to implement the requested checks only. Exceptions in the methods described below are of `LibException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Readers and Books
------------------------

The library has several books in its archives, each having a given title. It is possible to archive several copies of the same book, and each one has a unique book ID.  Each unique book copy is linked to the list of its past rentals, and to its current rental status.

The `addBook()` method enables the library to add a new book to the list of books available for rental. It can be invoked multiple times, and in case the added book is a duplicate (i.e., it has the same title as a book already present in the archive), the method updates the number of copies available for the book. Each book copy has a unique book ID. Book IDs are numeric codes assigned incrementally starting from `1000`. The method returns the unique book ID assigned to the book as a `String` value. 

The `getTitles()` method returns the book titles available in the library, sorted alphabetically, each one linked to the number of copies available for that title. Returns the `SortedMap<String, Integer>` of the titles linked to the number of available copies.

The `getBooks()` method returns the set of unique IDs of the book copies available in the library as a `Set<String>`.

The `addReader()` method allows the library to add a new reader. It requires providing the reader's name and surname (as `String` values). Reader IDs are codes assigned incrementally starting from `1000` to each new reader. The library archive accepts readers with the same name and surname and simply assigns them different IDs. 

The `getReaderName()` method retrieves the name of a reader given their reader ID. It takes a reader ID as input and returns a `String` value representing their name and surname in the format `"Name Surname"`. If the ID does not exist in the archive, a `LibException` is thrown.


R2: Rentals Management
----------------------

Readers can rent one book at a time only. 

The `getAvailableBook()` method retrieves the bookID of an available book copy. The method takes the book title as input, and returns the book ID of the first copy available, in order of book ID, for a specific book as a `String` value. If all copies of the title are currently rented, it returns value `"Not available"`. If the title is not present in the library archive, a `LibException` is thrown.

The `startRental()` method allows the library to activate a rental of a book for a reader. It takes the unique book ID, the unique reader ID and the starting date as inputs. If the same reader rents the same unique book copy twice, the second rental overwrites the first one. If either the book or the reader are not registered in the library archive or either of them is currently involved in a rental, a `LibException` is thrown.

The `endRental()` method allows the library to end a rental of a specific book for a specific reader. Rentals are ended defining their ending date. This method updates the book's list of rentals, and the reader's rental status. It takes the unique book ID, the unique reader ID and the ending date as inputs. If either the book or the reader are not registered in the library archive, or the book is not currently rented, a `LibException` is thrown.

The `getRentals()` method retrieves the list of readers that rented a specific book. It takes a unique book ID an input, and returns the map (as a `SortedMap<String, String>`) of the readers' reader IDs and the starting and ending dates of each rental (as `String` values in the format `"DD-MM-YYYY DD-MM-YYYY"`). If a rental is ongoing, it reports only the starting date, and the word `"ONGOING"` in place of the ending date (in the format `"DD-MM-YYYY ONGOING"`).


R3: Book Donations
----------------

The `receiveDonation()` method collects books donated to the library. It takes in input a list of book titles (as `String` value in the format `"First title,Second title"`; titles can contain words separated only by `" "`). This method adds the received titles to the library archive. 


R4: Archive Management
----------------------

The method `getOngoingRentals()` gets all active rentals as a `Map<String, String>` linking reader IDs of renting readers and the book IDs of the books they are currently renting.

The method `removeBooks()` renews the book collection by removing from the archives all book copies, independently of the title, that were never rented.


R5: Stats
---------

The `findBookWorm()` method finds the reader with the highest total number of rentals and returns their unique reader ID as a `String` value.

The `rentalCounts()` method returns the number of total rentals (both ended and ongoing) for each title in the library as a map have the titles as keys and the number of rentals as values.
