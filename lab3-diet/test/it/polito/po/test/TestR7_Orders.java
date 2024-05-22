package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import diet.*;
import org.junit.Before;
import org.junit.Test;

import diet.Customer;

public class TestR7_Orders {

	public static final String RESTAURANT_NAME = "Napoli";
	private Food food;
	private Takeaway t;
	private Customer u;

	@Before
	public void setUp() {
		food = new Food();
		t = new Takeaway(food);
		Restaurant r = t.addRestaurant(RESTAURANT_NAME);
		r.setHours("08:15", "14:00", "19:00", "23:55");
		Menu m1 = food.createMenu("M1");
		Menu m2 = food.createMenu("M2");
		r.addMenu(m1);
		r.addMenu(m2);

		u = t.registerCustomer("Marco", "Rossi", "marco.rossi@example.com", "123456789");
	}

	@Test
	public void testTakeawayCreateOrder() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "19:10");
		assertNotNull("Missing order",o);
		assertEquals(Order.OrderStatus.ORDERED, o.getStatus());
		assertEquals(Order.PaymentMethod.CASH, o.getPaymentMethod());
	}

	@Test
	public void testOrderToStringWorkingTime() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "09:30");
		assertNotNull("Missing order",o);
		assertEquals("Napoli, Marco Rossi : (09:30):", o.toString().trim());
	}

	@Test
	public void testOrderToStringOpenTime() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "19:10");
		assertNotNull("Missing order",o);
		assertEquals("Napoli, Marco Rossi : (19:10):", o.toString().trim());
	}

	@Test
	public void testOrderToStringCloseTime() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "23:59");
		assertNotNull("Missing order",o);
		assertEquals("Napoli, Marco Rossi : (08:15):", o.toString().trim());
	}

	@Test
	public void testOrderToStringInvalidTime() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "15:30");
		assertNotNull("Missing order",o);
		assertEquals("Napoli, Marco Rossi : (19:00):", o.toString().trim());
	}

	@Test
	public void testOrderSetStatus() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "19:10");
		assertNotNull("Missing order",o);
		o.setStatus(Order.OrderStatus.DELIVERED);
		assertEquals(Order.OrderStatus.DELIVERED, o.getStatus());
	}

	@Test
	public void testOrderSetPaymentMethod() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "19:10");
		assertNotNull("Missing order",o);
		o.setPaymentMethod(Order.PaymentMethod.PAID);
		assertEquals(Order.PaymentMethod.PAID, o.getPaymentMethod());
	}

	@Test
	public void testOrderAddMenus() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "9:00");
		assertNotNull("Missing order",o);
		o.addMenus("M1", 1);
		o.addMenus("M2", 2);
		assertEquals("Napoli, Marco Rossi : (09:00):\n\tM1->1\n\tM2->2", o.toString().trim());
	}

	@Test
	public void testOrderAddMenusOverwrite() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "9:00");
		assertNotNull("Missing order",o);
		o.addMenus("M1", 1);
		o.addMenus("M2", 2);
		o.addMenus("M1", 3);
		assertEquals("Napoli, Marco Rossi : (09:00):\n\tM1->3\n\tM2->2", o.toString().trim());
	}

	@Test
	public void testOrderAddMenusSorted() {
		Order o = t.createOrder(u, RESTAURANT_NAME, "9:00");
		assertNotNull("Missing order",o);
		o.addMenus("M2", 2);
		o.addMenus("M1", 1);
		assertEquals("Napoli, Marco Rossi : (09:00):\n\tM1->1\n\tM2->2", o.toString().trim());
	}
}