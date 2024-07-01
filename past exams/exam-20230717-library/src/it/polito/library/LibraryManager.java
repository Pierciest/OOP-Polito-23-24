package it.polito.library;

import java.util.*;
import java.util.stream.*;

public class LibraryManager {

	int id = 1000;
	int rid = 1000;
	Map<String,Book> Archive = new HashMap<>();
	Map<String,Reader> readers = new HashMap<>();
    // R1: Readers and Books 
    
    /**
	 * adds a book to the library archive
	 * The method can be invoked multiple times.
	 * If a book with the same title is already present,
	 * it increases the number of copies available for the book
	 * 
	 * @param title the title of the added book
	 * @return the ID of the book added 
	 */
    public String addBook(String title) {
		Book safeBook = Archive.get(title);
		if(safeBook == null){
			Book curBook = new Book(title);
			curBook.increaseCopy();
			curBook.appendId(String.valueOf(id));
			curBook.appendAvailable(String.valueOf(id));
			Archive.put(title, curBook);
			id++;
			return String.valueOf(id-1);
		}
		else{
			safeBook.increaseCopy();
			safeBook.appendId(String.valueOf(id));
			safeBook.appendAvailable(String.valueOf(id));
			id++;
			return String.valueOf(id-1);
		}		
    }
    
    /**
	 * Returns the book titles available in the library
	 * sorted alphabetically, each one linked to the
	 * number of copies available for that title.
	 * 
	 * @return a map of the titles liked to the number of available copies
	 */
	public SortedMap<String, Integer> getTitles() {    	
		SortedMap<String, Integer> result = Archive.values().stream().collect(Collectors.toMap(Book::getName, Book::getCopies, (n1,n2)->n1, TreeMap::new));
		return result;
	}
	
	/**
	 * Returns the books available in the library
	 * 
	 * @return a set of the titles liked to the number of available copies
	 */
	public Set<String> getBooks() {    	    	
		return Archive.values().stream()
				.flatMap(book -> book.getId().stream())
				.collect(Collectors.toSet());
	}
	    

		public void addReader(String name, String surname) {
			Reader curReader = new Reader(name,surname);
			curReader.appendId(String.valueOf(rid));
			readers.put(String.valueOf(rid), curReader);
			rid++;

		}
	    
	    /**
		 * Returns the reader name associated to a unique reader ID
		 * 
		 * @param readerID the unique reader ID
		 * @return the reader name
		 * @throws LibException if the readerID is not present in the archive
		 */
	    public String getReaderName(String readerID) throws LibException {
			if(readers.get(readerID) == null){
				throw new LibException();
			}
	        return readers.get(readerID).getName() + " " + readers.get(readerID).getSurname();
	    }    
    
    
    // R2: Rentals Management
    
    
    /**
	 * Retrieves the bookID of a copy of a book if available
	 * 
	 * @param bookTitle the title of the book
	 * @return the unique book ID of a copy of the book or the message "Not available"
	 * @throws LibException  an exception if the book is not present in the archive
	 */
    public String getAvailableBook(String bookTitle) throws LibException {
		if(Archive.get(bookTitle) == null){
			throw new LibException();
		}
		Book curBook = Archive.get(bookTitle);
		if(curBook.availableStack.size() != 0){
			return curBook.availableStack.peek();
		}
		else{
			return "Not available";
		}
    }   

    /**
	 * Starts a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param startingDate the starting date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is already renting a book, or if the book copy is already rented
	 */

	public Book getBook(String bookID){
		Book curBook = null;
		for(Book b : Archive.values()){
			if(b.ids.contains(bookID)){
				curBook = b;
			}
		}
		return curBook;
	}
	public void startRental(String bookID, String readerID, String startingDate) throws LibException {
		Reader curReader = readers.get(readerID);
		Book curBook = getBook(bookID);
		if(curBook == null){
			throw new LibException();
		}
		if(curReader == null){
			throw new LibException();
		}
		curBook.appendRent();
		curBook.increaseTrack(bookID);
		curReader.appendRent(bookID, startingDate);
    }
    
	/**
	 * Ends a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param endingDate the ending date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is not renting a book, or if the book copy is not rented
	 */
    public void endRental(String bookID, String readerID, String endingDate) throws LibException {
		Reader curReader = readers.get(readerID);
		Book curBook = getBook(bookID);
		if(curBook == null){
			throw new LibException();
		}
		if(curReader == null){
			throw new LibException();
		}
		curBook.terminateRent(bookID);
		curReader.terminateRent(bookID, endingDate);

    }
    
    
   /**
	* Retrieves the list of readers that rented a specific book.
	* It takes a unique book ID as input, and returns the readers' reader IDs and the starting and ending dates of each rental
	* 
	* @param bookID the unique book ID of the book copy
	* @return the map linking reader IDs with rentals starting and ending dates
	* @throws LibException  an exception if the book copy or the reader are not present in the archive,
	* if the reader is not renting a book, or if the book copy is not rented
	*/
public SortedMap<String, String> getRentals(String bookID) throws LibException {
	SortedMap<String,String> result = new TreeMap<>();
	for (Reader r : readers.values()) {
		String c = r.completed.get(bookID);
		String o = r.onRent.get(bookID);
		if(c != null){
			result.put(r.id, c);
		}
		else if (o != null){
			result.put(r.id, o + " ONGOING");
		}
	}
	return result;
}
    
    
    // R3: Book Donations
    
    /**
	* Collects books donated to the library.
	* 
	* @param donatedTitles It takes in input book titles in the format "First title,Second title"
	*/
    public void receiveDonation(String donatedTitles) {
		for(String bookTitle : donatedTitles.split(",")){
			addBook(bookTitle);
		}
    }
    
    // R4: Archive Management

    /**
	* Retrieves all the active rentals.
	* 
	* @return the map linking reader IDs with their active rentals

	*/
    public Map<String, String> getOngoingRentals() {
		Map<String ,String> result = new HashMap<>();

		for(Reader r: readers.values()){
			if(!r.onRent.values().isEmpty()){
				result.put(r.id, r.onRent.values().toString());
			}
		}
        return result;
    }
    
    /**
	* Removes from the archives all book copies, independently of the title, that were never rented.
	* 
	*/
public void removeBooks() {
	for (Book b : Archive.values()) {
		for (String id : new ArrayList<>(b.ids)) {
			if (b.rentTrack.get(id) == 0) {
				b.removeCopy(id);
			}
		}
	}
}
    	
    // R5: Stats
    
    /**
	* Finds the reader with the highest number of rentals
	* and returns their unique ID.
	* 
	* @return the uniqueID of the reader with the highest number of rentals
	*/
    public String findBookWorm() {
		int highest = 0;
		String id = "";
		for(Reader r : readers.values()){
			if(r.completed.size() + r.onRent.size() > highest){
				highest = r.completed.size() + r.onRent.size();
				id = r.id;
			}
		}
        return id;
    }
    
    /**
	* Returns the total number of rentals by title. 
	* 
	* @return the map linking a title with the number of rentals
	*/
    public Map<String,Integer> rentalCounts() {
        return 	Archive.values().stream().filter(b->b.getTotalRental()!=0).collect(Collectors.toMap(Book :: getName, Book :: getTotalRental))
		;
    }

}
