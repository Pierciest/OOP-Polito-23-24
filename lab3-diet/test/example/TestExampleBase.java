package example;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import diet.Food;
import diet.NutritionalElement;
import diet.Menu;
import diet.Recipe;


public class TestExampleBase {

	private Food foods;
	
	@Before
	public void setUp() {
		foods = new Food();
	}

	@Test
    public void testR1()  {
        foods.defineRawMaterial("Sugar", 400, 0, 100, 0);
        foods.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
        foods.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
        foods.defineRawMaterial("Oil", 900, 0, 0, 100);
        foods.defineRawMaterial("Nutella", 530, 6.8, 56, 31);
        
        assertNotNull("Missing materials", foods.rawMaterials());
        assertEquals("There should be 5 materials", 5, foods.rawMaterials().size());
        
        NutritionalElement oil = foods.getRawMaterial("Oil");
        assertNotNull("Missing material oil",oil);
        assertEquals("Wrong calories", 900, oil.getCalories(), 0.1);
        assertEquals("Wrong calories", 100, oil.getFat(), 0.1);
	}

	@Test
    public void testR2()  {
        foods.defineProduct("Crackers", 111, 2.6, 17.2, 3.5);
        
        NutritionalElement crackers = foods.getProduct("Crackers");
        
        assertNotNull("Missing product",crackers);
        assertEquals("Wrong carbs", 17.2, crackers.getCarbs(), 0.1);
	}

	@Test
    public void testR3()  {
        foods.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
        foods.defineRawMaterial("Nutella", 530, 6.8, 56, 31);

        Recipe r = foods.createRecipe("Pasta and Nutella");
        
        assertNotNull("Missing recipe",r);

        r.addIngredient("Pasta", 70).
          addIngredient("Nutella", 30);
        
        assertEquals("Computation of calories for recipe is wrong", 404.0 , r.getCalories(), 0.1);
        assertTrue("Recipe values should be per 100g", r.per100g());
	}

	@Test
    public void testR4()  {
        foods.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
        foods.defineRawMaterial("Nutella", 530, 6.8, 56, 31);
        foods.defineProduct("Crackers", 111, 2.6, 17.2, 3.5);

        failNull("Missing recipe", foods.createRecipe("Pasta and Nutella"))
        	 .addIngredient("Pasta", 70)
        	 .addIngredient("Nutella", 30);
        
        Menu menu1 = foods.createMenu("M1");
        assertNotNull("Missing recipe",menu1);
        
        menu1.addRecipe("Pasta and Nutella", 50).
        	 addProduct("Crackers");
        
        assertFalse(menu1.per100g());
        assertEquals(313,menu1.getCalories(),0.1);
        assertEquals(7.8,menu1.getProteins(),0.1);
        assertEquals(8.7,menu1.getFat(),0.1);
	}

    private static <T> T failNull(String msg, T x){
        assertNotNull(msg, x);
        return x;
    }
}
