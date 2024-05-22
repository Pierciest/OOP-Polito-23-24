package it.polito.po.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import static it.polito.po.test.CollectionsAssertions.*;

import diet.*;

public class TestR3_Recipes {
	private Food food;

	@Before
	public void setUp() {
		food = new Food();
		food.defineRawMaterial("Zucchero", 400, 0, 100, 0);
		food.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
		food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		food.defineRawMaterial("Olio", 900, 0, 0, 100);
		food.defineRawMaterial("Nutella", 530, 6.8, 56, 31);
	}

	@Test
	public void testCreateRecipe() {
		/* Recipe r = */ food.createRecipe("Pasta col Mais");

		assertNotNull("Missing recipe", food.getRecipe("Pasta col Mais"));

		assertNotNull("Missing recipes", food.recipes());
		assertEquals("Wrong number of recipes", 1, food.recipes().size());
	}

	@Test
	public void testCreateManyRecipe() {
		food.createRecipe("Pasta alla Norma");
		food.createRecipe("Melanzane alla Parmigiana");
		food.createRecipe("Bistecca alla Fiorentina");
		food.createRecipe("Tiramisu'");

		assertNotNull("Missing recipe", food.getRecipe("Tiramisu'"));
		assertNotNull("Missing recipes", food.recipes());
		assertEquals("Wrong number of recipes", 4, food.recipes().size());
	}

	@Test
	public void testRecipesOrder() {
		food.createRecipe("Pasta alla Norma");
		food.createRecipe("Melanzane alla Parmigiana");
		food.createRecipe("Bistecca alla Fiorentina");
		food.createRecipe("Tiramisu'");
		food.createRecipe("Valdostana");
		food.createRecipe("Ratatouille");
		food.createRecipe("Bagna caoda");

		Collection<NutritionalElement> recipes = food.recipes();
		assertNotNull(recipes);
		assertEquals("Some recipes missing", 7, recipes.size());
		assertSorted("Wrong recipes order", recipes,NutritionalElement::getName);
	}

	@Test
	public void testRecipe() {

		Recipe r = food.createRecipe("Pasta e Nutella");

		r.addIngredient("Pasta", 70);
		r.addIngredient("Nutella", 30);
		assertEquals("Wrong recipe calories value", 350 * 0.7 + 530 * 0.3, r.getCalories(), 0.001);
		assertEquals("Wrong recipe proteins value", 12 * 0.7 + 6.8 * 0.3, r.getProteins(), 0.001);
		assertEquals("Wrong recipe carbs value", 72.2 * 0.7 + 56 * 0.3, r.getCarbs(), 0.001);
		assertEquals("Wrong recipe fat value", 1.5 * 0.7 + 31 * 0.3, r.getFat(), 0.001);
		assertTrue(r.per100g());
	}

	@Test
	public void testRecipe2() {
		Recipe r = food.createRecipe("Pasta col Mais");

		r.addIngredient("Pasta", 70);
		r.addIngredient("Mais", 70);
		r.addIngredient("Olio", 13);

		assertEquals("Wrong recipe calories value", 
				(350 * 0.7 + 70 * 0.7 + 900 * 0.13) * 100 / 153, r.getCalories(),
				0.001);
		assertEquals("Wrong recipe proteins value", 
				(12 * 0.7 + 2.7 * 0.7 + 0 * 0.13) * 100 / 153, r.getProteins(),
				0.001);
		assertEquals("Wrong recipe carbs value", 
				(72.2 * 0.7 + 12 * 0.7 + 0 * 0.13) * 100 / 153, r.getCarbs(), 0.001);
		assertEquals("Wrong recipe fat value", 
				(1.5 * 0.7 + 1.3 * 0.7 + 100 * 0.13) * 100 / 153, r.getFat(), 0.001);
	}

	@Test
	public void testToString() {
		Recipe r = food.createRecipe("Pasta col Mais");

		r.addIngredient("Pasta", 70);
		r.addIngredient("Mais", 70);
		r.addIngredient("Olio", 13);

		String rs = r.toString();

		assertNotNull("Missing Recipe string representation", rs);
		assertTrue("Empty Recipe string representation", rs.length() > 0);
		assertFalse("Recipe string representation non defined", rs.startsWith("diet.Recipe"));

		String[] lines = rs.split("\n");

		assertEquals("Numero di ingredienti errato in '" + rs + "'", 3, lines.length);

		assertTrue("First line should contain Pasta " + rs, lines[0].trim().startsWith("Pasta"));
		assertTrue("First line should contain Mais", lines[1].trim().startsWith("Mais"));
		assertTrue("First line should contain Olio", lines[2].trim().startsWith("Olio"));
	}

}
