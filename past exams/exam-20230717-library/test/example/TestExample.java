package example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

import it.polito.library.LibException;
import it.polito.library.LibraryManager;

public class TestExample {

    @Test
    public void testAll() throws LibException {
	    LibraryManager lib = new LibraryManager();
	
	    // R1: Readers and Books
	
	    lib.addBook("Dance Dance Dance");
	    lib.addBook("Lolita");
	    lib.addBook("Master and Margarita");
	    lib.addBook("Lolita");
	    lib.addBook("Dance Dance Dance");
	    lib.addBook("Dance Dance Dance");
	    assertEquals("1006",lib.addBook("Dance Dance Dance"));

	
	    SortedMap<String, Integer> titles = lib.getTitles();
	    assertEquals(3, titles.size());
	    
	    Set<String> bookSet = lib.getBooks();	    
	    assertEquals(7, bookSet.size());
	    
	    lib.addReader("Maria", "Verdi");
	    lib.addReader("Gianni", "Fidenza");
	    lib.addReader("Maria", "Lonza");
	    lib.addReader("Gianni", "Fidenza");
    
	    assertEquals("Gianni Fidenza", lib.getReaderName("1003"));
	    
	    
	    // R2: Rentals Management
	
		assertEquals("1002",lib.getAvailableBook("Master and Margarita"));
		  
		lib.startRental("1002", "1000", "14-07-2023");
		assertEquals("Not available", lib.getAvailableBook("Master and Margarita"));
		lib.endRental("1002", "1000","15-07-2023");
		assertEquals("1002", lib.getAvailableBook("Master and Margarita"));
		  
		SortedMap<String, String> rentals = lib.getRentals("1002");
		assertEquals(1,rentals.size());
		assertEquals("14-07-2023 15-07-2023", rentals.get("1000"));
		 
	    
	    // R3: Book Donations
	    
	    lib.receiveDonation("Beauty and the Beast,Cindarella,Snowhite");
		titles = lib.getTitles();
	    assertEquals(6, titles.size());
	    Set<String> books = lib.getBooks();

	    assertEquals(10, books.size());	    
	    	    	    
	    assertEquals("1007", lib.getAvailableBook("Beauty and the Beast"));
	    assertEquals("1008", lib.getAvailableBook("Cindarella"));
	    assertEquals("1009", lib.getAvailableBook("Snowhite"));

	    // R4: Archive Management
	    
	    Map<String, String> activeRentals = lib.getOngoingRentals();
	    
	    assertEquals(0, activeRentals.size());
	    
	    lib.startRental("1002", "1002", "10-07-2023");
	    
	    activeRentals = lib.getOngoingRentals();
	    assertEquals(1, activeRentals.size());
	    lib.endRental("1002", "1002", "11-07-2023");
	    
	    lib.startRental("1001", "1002", "10-07-2021");
	    lib.endRental("1001", "1002", "11-07-2021");
	    
	    lib.startRental("1001", "1001", "12-07-2021");
	    lib.endRental("1001", "1001", "19-07-2021");

	    assertEquals(10, books.size());
	    lib.removeBooks();
		books = lib.getBooks();
	    assertEquals(2, books.size());
	    
	    
	    // R5: Stats
	    
	    String bookWorm = lib.findBookWorm();
	    assertEquals("1002", bookWorm);

	    Map<String,Integer> counts = lib.rentalCounts();
	    assertNotNull(counts);
	    assertEquals(2,counts.size());
	    assertTrue(counts.containsKey("Master and Margarita"));
	    assertEquals(Integer.valueOf(2), counts.get("Master and Margarita"));
    }
    
}