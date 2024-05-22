package it.polito.po.test;

import static it.polito.po.test.CollectionsAssertions.assertSorted;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import diet.Customer;
import diet.Food;
import org.junit.Before;
import org.junit.Test;

import diet.Takeaway;

public class TestR6_Users {

	private Food food;
	private Takeaway t;

	@Before
	public void setUp(){
		this.food = new Food();
		this.t = new Takeaway(food);
	}

	@Test
	public void testTakeawayRegisterUser() {
		Customer u = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		assertNotNull("Missing new registered user",u );
		assertEquals("Marco", u.getFirstName());
		assertEquals("Rossi", u.getLastName());
		assertEquals("marco.rossi@example.com", u.getEmail());
		assertEquals("123456789", u.getPhone());
	}

	@Test
	public void testUserToString() {
		Customer u = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		assertNotNull("Missing new registered user",u );
		String us = u.toString();
		assertNotNull(us);
		assertFalse("toString method non redefined for User " + us,us.contains("@"));
		// We split and reorder to allow both "first last" and "last first"
		String[] usa = us.split(" +");
		Arrays.sort(usa);
		assertArrayEquals(new String[] {"Marco","Rossi"}, usa);
	}

	@Test
	public void testUserSetEmail() {
		Customer u = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		assertNotNull("Missing new registered user",u );
		u.SetEmail("marco@example.com");
		assertEquals("Wrong email","marco@example.com", u.getEmail());
	}

	@Test
	public void testUserSetPhone() {
		Customer u = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		assertNotNull("Missing new registered user",u );
		u.setPhone("987654321");
		assertEquals("Wrong mobile","987654321", u.getPhone());
	}

	@Test
	public void testTakeawayUsers() {
		Customer u1 = t.registerCustomer("Giuseppe", "Verdi", "marco.rossi@example.com", "123456789");
		Customer u2 = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		Customer u3 = t.registerCustomer("Giovanni", "Rossi", "marco.rossi@example.com", "123456789");
		Collection<Customer> customers = t.customers();
		assertNotNull("Missing user list", customers);
		assertEquals("Wrong number of users",3, customers.size());
		assertTrue("Missing user in list", customers.contains(u1));
		assertTrue("Missing user in list", customers.contains(u2));
		assertTrue("Missing user in list", customers.contains(u3));
	}

	@Test
	public void testTakeawayUsersSorted() {
		t.registerCustomer("Giuseppe", "Verdi", "marco.rossi@example.com", "123456789");
		t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
		t.registerCustomer("Giovanni", "Rossi", "marco.rossi@example.com", "123456789");
		Collection<Customer> customers = t.customers();
		assertNotNull("Missing user list", customers);
		assertEquals("Wrong number of users",3, customers.size());

		assertSorted("Wrong user order", customers, u -> u.getLastName()+u.getFirstName());
	}

}
