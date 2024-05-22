package it.polito.po.test;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import diet.Food;
import diet.Menu;
import diet.Restaurant;
import diet.Takeaway;

import static org.junit.Assert.*;

public class TestR5_Restaurant {

	private Food food;
	private Takeaway takeaway;

	@Before
	public void setUp() {
		food = new Food();
		takeaway = new Takeaway(food);
	}

	@Test
	public void testRestaurantGetName() {
		Restaurant r = takeaway.addRestaurant("Napoli");
		assertNotNull("No restaurant created", r);
		assertNotNull("Missing restaurant name", r.getName());
		assertEquals("Wrong restaurant name", "Napoli", r.getName());
	}

	@Test
	public void testOpenSingle(){
		Restaurant r = takeaway.addRestaurant("Venezia");
		r.setHours("08:00","20:00");

		assertTrue("Should be open at noon", r.isOpenAt("12:00"));
		assertFalse("Should be close at 9pm", r.isOpenAt("21:00"));
	}

	@Test
	public void testOpenWPause(){
		Restaurant r = takeaway.addRestaurant("Venezia");
		r.setHours("11:00","14:30","19:00","23:00");

		assertTrue("Should be open at noon", r.isOpenAt("12:00"));
		assertFalse("Should be close at 5pm", r.isOpenAt("17:00"));
		assertTrue("Should be open at 9pm", r.isOpenAt("21:00"));
	}

	@Test
	public void testRestaurantCreateMenu() {
		Restaurant r = takeaway.addRestaurant("Napoli");
		Menu m = food.createMenu("M1");
		r.addMenu(m);
		
		assertNotNull("Missing created menu",m);
		assertSame("Retrieved wrong menu",m, r.getMenu("M1"));
	}

	@Test
	public void testTakeawayAddRestaurant() {
		takeaway.addRestaurant("Napoli");
		takeaway.addRestaurant("Torino");

		Collection<String> restaurants = takeaway.restaurants();

		assertNotNull("Missing restaurants", restaurants);
		assertEquals("Missing restaurant", 2, restaurants.size());
		assertTrue(restaurants.contains("Napoli"));
		assertTrue(restaurants.contains("Torino"));
	}
}
