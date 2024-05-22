package it.polito.po.test;
import java.util.Collection;

import diet.*;

import org.junit.Before;
import org.junit.Test;

import static it.polito.po.test.CollectionsAssertions.*;
import static org.junit.Assert.*;


public class TestR2_Products {
	Food food = new Food();

	@Before
	public void setUp() {
		food = new Food();
	}


	@Test
	public void testDefinition(){
		int initialSize = food.products().size();
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
		int finalSize = food.products().size();

		assertEquals("Initially there should be no product", 0,initialSize);
		assertEquals("Expecting one product", 1, finalSize);
	}

	@Test
	public void testProductCollection(){
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);

		Collection<NutritionalElement> c = food.products();

		assertNotNull("Missing products",c);
		NutritionalElement en = c.iterator().next();

		assertEquals("Wrong product name","Cracker",en.getName());
		assertEquals("Wrong product calories value",111,en.getCalories(),0.001); 
		assertEquals("Wrong product proteins value",2.6,en.getProteins(),0.001); 
		assertEquals("Wrong product carbs value",17.2,en.getCarbs(),0.001); 
		assertEquals("Wrong product fat value",3.5,en.getFat(),0.001); 
		assertFalse("Values should be per whole product",en.per100g());
	}

	@Test
	public void testProduct(){
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
		NutritionalElement en = food.getProduct("Cracker");

		assertNotNull("Missing product",en);

		assertEquals("Wrong product name","Cracker",en.getName());
		assertEquals("Wrong product calories value",111,en.getCalories(),0.001); 
		assertEquals("Wrong product proteins value",2.6,en.getProteins(),0.001); 
		assertEquals("Wrong product carbs value",17.2,en.getCarbs(),0.001); 
		assertEquals("Wrong product fat value",3.5,en.getFat(),0.001); 
		assertFalse("Values should be per whole product",en.per100g());
	}

	@Test
	public void testProductCollectionSorted(){
		food.defineProduct("Cornetto Cioccolato", 230, 3, 27, 11);
		food.defineProduct("Barretta Bueno", 122, 2, 10.6, 8);
		food.defineProduct("Mozzarella Light", 206, 25, 2, 11.25);
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);

		assertSorted("Products are not sorted",
				 food.products(),NutritionalElement::getName);
	}

}
