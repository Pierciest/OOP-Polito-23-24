package example;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import diet.Food;
import diet.Order;
import diet.Menu;
import diet.Restaurant;
import diet.Takeaway;
import diet.Customer;
import diet.Order.OrderStatus;
import diet.Order.PaymentMethod;


public class TestExampleExt {

	private Food foods;
	private Takeaway takeaway;
	private Menu menu1, menu2, menu3, menu4, menu5, menu6;

	@Before
	public void setUp() {
		foods = new Food();
		takeaway = new Takeaway(foods);

		foods.defineRawMaterial("Sugar", 400, 0, 100, 0);
		foods.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
		foods.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		foods.defineRawMaterial("Oil", 900, 0, 0, 100);
		foods.defineRawMaterial("Nutella", 530, 6.8, 56, 31);
		foods.defineRawMaterial("Eggs", 120, 6.8, 56, 31);
		foods.defineRawMaterial("Flour", 100, 6.8, 56, 31);
		foods.defineRawMaterial("Sausage", 500, 15.8, 20, 4.1);
		foods.defineRawMaterial("Tomato sauce", 120, 6.8, 56, 31);
		foods.defineRawMaterial("Minced meat", 450, 6.8, 56, 31);
		foods.defineRawMaterial("Mozzarella", 250, 6.8, 56, 31);
		foods.defineRawMaterial("Olives", 60, 6.8, 56, 31);
		foods.defineRawMaterial("Pesto", 120, 6.8, 56, 20);
		foods.defineRawMaterial("Onion", 40, 3, 20.3, 1);
		foods.defineRawMaterial("Prosciutto Crudo", 420, 6.8, 56, 31);
		foods.defineRawMaterial("Carrots", 60, 6.8, 56, 31);
		foods.defineRawMaterial("Red wine", 60, 6.8, 56, 31);
		foods.defineRawMaterial("Bacon", 530, 6.8, 56, 31);
		foods.defineRawMaterial("Garlic", 34, 4.1, 21.2, 2);
		foods.defineRawMaterial("Cream", 530, 6.8, 56, 31);
		foods.defineRawMaterial("Gorgonzola", 300, 6.8, 56, 31);
		foods.defineRawMaterial("Walnuts", 220, 6.8, 56, 31);
		foods.defineRawMaterial("Prosciutto Cotto", 400, 6.8, 56, 31);
		foods.defineRawMaterial("Mortadella", 420, 6.8, 56, 31);

		foods.defineProduct("Beer 0.5l", 40, 0.5, 0.2, 0.05);
		foods.defineProduct("Grissini", 20, 0.5, 0.2, 0.05);
		foods.defineProduct("Biscuits", 150, 2.0, 10.2, 1.4);
		foods.defineProduct("Amaro", 10, 0.6, 0.25, 0.1);
		foods.defineProduct("Wine 0.5l", 40, 0.5, 1.2, 0.05);
		foods.defineProduct("Water bottle 0.33l", 5, 0.1, 0.2, 0.05);
		foods.defineProduct("Orange Juice 0.4l", 80, 0.5, 2.2, 0.05);
		foods.defineProduct("Crackers", 111, 2.6, 17.2, 3.5);

		// RECIPES
		failNull("Missing recipe",foods.createRecipe("Pasta and Nutella"))
			.addIngredient("Pasta", 70).addIngredient("Nutella", 30);

		foods.createRecipe("Pasta al Ragu").addIngredient("Pasta", 350).addIngredient("Onion", 100)
				.addIngredient("Garlic", 40).addIngredient("Tomato sauce", 250).addIngredient("Red wine", 50)
				.addIngredient("Carrots", 150).addIngredient("Bacon", 200).addIngredient("Minced meat", 400);

		foods.createRecipe("Pizza 1").addIngredient("Flour", 150).addIngredient("Oil", 20).addIngredient("Sausage", 100)
				.addIngredient("Tomato sauce", 75).addIngredient("Pesto", 40).addIngredient("Mozzarella", 50);

		foods.createRecipe("Pizza 2").addIngredient("Flour", 150).addIngredient("Oil", 20).addIngredient("Cream", 75)
				.addIngredient("Gorgonzola", 175).addIngredient("Walnuts", 50);

		foods.createRecipe("Pizza 3").addIngredient("Flour", 160).addIngredient("Oil", 30)
				.addIngredient("Mozzarella", 200).addIngredient("Prosciutto Crudo", 140).addIngredient("Olives", 65);

		foods.createRecipe("Meatballs").addIngredient("Tomato sauce", 400).addIngredient("Eggs", 50)
				.addIngredient("Onion", 175).addIngredient("Minced meat", 500);

		// MENUS
		menu1 = foods.createMenu("M1").addRecipe("Pasta and Nutella", 50).addProduct("Crackers");

		menu2 = foods.createMenu("M2").addRecipe("Pizza 1", 350).addProduct("Beer 0.5l");

		menu3 = foods.createMenu("M3").addRecipe("Pasta al Ragu", 320).addProduct("Wine 0.5l").addProduct("Biscuits")
				.addProduct("Amaro");

		menu4 = foods.createMenu("M4").addRecipe("Pizza 2", 350).addProduct("Orange Juice 0.4l");

		menu5 = foods.createMenu("M5").addRecipe("Pizza 3", 380).addProduct("Water bottle 0.33l");

		menu6 = foods.createMenu("M6").addRecipe("Meatballs", 500).addProduct("Grissini");

	}

	@Test
	public void testR5() {
		Takeaway takeaway = new Takeaway(foods);
		Restaurant r1 = takeaway.addRestaurant("Napoli");

		assertNotNull("Missing restaurant", r1);
		assertEquals("Wrong restaurant name", "Napoli", r1.getName());

		r1.setHours("08:00", "14:00", "19:00", "23:59");
		Restaurant r2 = takeaway.addRestaurant("Roma");
		r2.setHours("08:45", "13:30", "18:20", "23:00");

		Collection<String> restaurants = takeaway.restaurants();
		assertEquals("Wrong number of restaurants", 2, restaurants.size());

		assertTrue("Should be open", r1.isOpenAt("12:00"));
		assertFalse("Should be closed", r2.isOpenAt("17:00"));

		Menu menu1 = foods.createMenu("M1");
		r1.addMenu(menu1);
		Menu m = r1.getMenu("M1");

		assertNotNull("Missing menu", m);
	}

	@Test
	public void testR6() {
		Takeaway takeaway = new Takeaway(foods);

		// Registering new users
		Customer c1 = takeaway.registerCustomer("Ralph", "Fiennes", "r.fiennes@gmail.com", "333413493");

		assertNotNull("Missing customer", c1);
		assertEquals("Wrong customer name", "Ralph", c1.getFirstName());
		assertEquals("Wrong customer surname", "Fiennes", c1.getLastName());
		assertEquals("Wrong customer name", "r.fiennes@gmail.com", c1.getEmail());
		assertEquals("Wrong customer name", "333413493", c1.getPhone());

		takeaway.registerCustomer("Ian", "McKellen", "i.mckellen@gmail.com", "124882578");
		takeaway.registerCustomer("Maggie", "Smith", "m.smith@gmail.com", "3647851225");

		Collection<Customer> customers = takeaway.customers();

		assertNotNull("Missing customers", customers);
		assertEquals("Wrong number of customers", 3, customers.size());
		assertTrue("Maggie is not in the list", customers.contains(c1));
	}

	@Test
	public void testR7() {
		Customer u1 = takeaway.registerCustomer("Ralph", "Fiennes", "r.fiennes@gmail.com", "333413493");
		Customer u2 = takeaway.registerCustomer("Ian", "McKellen", "i.mckellen@gmail.com", "124882578");

		Restaurant r1 = takeaway.addRestaurant("Napoli");
		r1.setHours("08:00", "14:00", "19:00", "23:59");

		r1.addMenu(menu1);
		r1.addMenu(menu2);
		r1.addMenu(menu6);

		Order o1 = takeaway.createOrder(u1, "Napoli", "19:30");// r1
		assertNotNull("Missing order", o1);

		o1.addMenus("M6", 1).addMenus("M1", 2);
		assertEquals("Wrong menu contents", "Napoli, Ralph Fiennes : (19:30):\n\tM1->2\n\tM6->1", o1.toString().trim());
		assertEquals("Default payment method should be CASH", PaymentMethod.CASH, o1.getPaymentMethod());
		assertEquals("Default status for order should be ORDERED", OrderStatus.ORDERED, o1.getStatus());

		Order o2 = takeaway.createOrder(u2, "Napoli", "17:55");// r1
		o2.addMenus("M6", 2).addMenus("M2", 2);
		assertEquals("Wrong menu contents", "Napoli, Ian McKellen : (19:00):\n\tM2->2\n\tM6->2", o2.toString().trim());
	}

	@Test
	public void testR8() {
		Customer u1 = takeaway.registerCustomer("Ralph", "Fiennes", "r.fiennes@gmail.com", "333413493");
		Customer u2 = takeaway.registerCustomer("Ian", "McKellen", "i.mckellen@gmail.com", "124882578");
		Customer u3 = takeaway.registerCustomer("Maggie", "Smith", "m.smith@gmail.com", "3647851225");
		Customer u4 = takeaway.registerCustomer("Judi", "Dench", "j.dench@gmail.com", "9885422544");
		takeaway.registerCustomer("Adam", "McKellen", "a.mckellen@gmail.com", "124882578");

		Restaurant r1 = takeaway.addRestaurant("Napoli");
		r1.setHours("08:00", "14:00", "19:00", "23:59");
		Restaurant r2 = takeaway.addRestaurant("Roma");
		r2.setHours("08:45", "13:30", "18:20", "23:00");
		Restaurant r3 = takeaway.addRestaurant("Venezia");
		r3.setHours("12:30", "16:00", "07:45", "11:00");
		Restaurant r4 = takeaway.addRestaurant("Milano");
		r4.setHours("08:45", "23:30");

		r1.addMenu(menu1);
		r1.addMenu(menu2);
		r1.addMenu(menu6);

		r2.addMenu(menu3);
		r2.addMenu(menu5);
		r2.addMenu(menu4);

		r3.addMenu(menu1);
		r3.addMenu(menu4);

		r4.addMenu(menu2);
		r4.addMenu(menu3);
		r4.addMenu(menu4);
		r4.addMenu(menu5);

		Order o1 = takeaway.createOrder(u1, "Napoli", "17:47");// r1
		o1.addMenus("M6", 1).addMenus("M1", 2);

		Order o2 = takeaway.createOrder(u3, "Napoli", "17:55");// r1
		o2.addMenus("M6", 2).addMenus("M2", 2);

		Order o3 = takeaway.createOrder(u2, "Milano", "20:30");// r4
		o3.addMenus("M2", 1).addMenus("M4", 2).addMenus("M5", 1);

		Order o4 = takeaway.createOrder(u3, "Roma", "2:15");// r2
		o4.addMenus("M3", 4).addMenus("M5", 3);
		o4.setPaymentMethod(PaymentMethod.CARD);

		Order o5 = takeaway.createOrder(u4, "Napoli", "17:18");// r1
		o5.addMenus("M6", 1);

		Order o6 = takeaway.createOrder(u3, "Venezia", "11:15");// r3
		o6.addMenus("M4", 2).addMenus("M1", 1);

		Order o7 = takeaway.createOrder(u1, "Napoli", "11:47");// r1
		o7.addMenus("M6", 2);

		Order o8 = takeaway.createOrder(u1, "Napoli", "9:15");// r1
		o8.addMenus("M1", 1);

		o1.setStatus(OrderStatus.DELIVERED);
		o2.setStatus(OrderStatus.READY);
		o4.setStatus(OrderStatus.READY);
		o5.setStatus(OrderStatus.DELIVERED);

		assertEquals("Napoli, Judi Dench : (19:00):\n\tM6->1\n"+
					 "Napoli, Ralph Fiennes : (19:00):\n\tM1->2\n\tM6->1",
					 r1.ordersWithStatus(OrderStatus.DELIVERED).trim());
		
		Collection<Restaurant> open = takeaway.openRestaurants("05:50");
		assertNotNull(open);
		assertTrue("There should be no restaurant open at 5:50", open.isEmpty());

		open = takeaway.openRestaurants("11:31");
		assertEquals("Wrong number of open restaurants at 11:31", 3, open.size());
		assertTrue("Missing Napoli", open.contains(r1));
	}
	
	private static <T> T failNull(String msg, T x){
        assertNotNull(msg, x);
        return x;
    }
}
