package it.polito.po.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import diet.*;
import java.util.Collection;
import static it.polito.po.test.CollectionsAssertions.*;

public class TestR1_RawMaterials {
	private Food food = new Food();

	@Before
	public void setUp() {
		food = new Food();
	}

	@Test
	public void testDefinition() {
		int initialSize = food.rawMaterials().size();
		food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		int finalSize = food.rawMaterials().size();

		assertEquals("Initially there should be no raw material", 0, initialSize);
		assertEquals("Expecting one product", 1, finalSize);
	}

	@Test
	public void testRawMaterialsCollection() {
		food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);

		Collection<NutritionalElement> c = food.rawMaterials();

		NutritionalElement en = c.iterator().next();

		assertEquals("Wrong material name", "Pasta", en.getName());
		assertEquals("Wrong material calories", 350, en.getCalories(), 0.001);
		assertEquals("Wrong material proteins", 12, en.getProteins(), 0.001);
		assertEquals("Wrong material carbs", 72.2, en.getCarbs(), 0.001);
		assertEquals("Wrong material fat", 1.5, en.getFat(), 0.001);
		assertTrue("Material should report value per 100 grams", en.per100g());
	}

	@Test
	public void testRawMaterials() {
		food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		NutritionalElement en = food.getRawMaterial("Pasta");

		assertEquals("Wrong material name", "Pasta", en.getName());
		assertEquals("Wrong material calories", 350, en.getCalories(), 0.001);
		assertEquals("Wrong material proteins", 12, en.getProteins(), 0.001);
		assertEquals("Wrong material carbs", 72.2, en.getCarbs(), 0.001);
		assertEquals("Wrong material fat", 1.5, en.getFat(), 0.001);
		assertTrue("Material should report value per 100 grams", en.per100g());
	}

	@Test
	public void testRawMaterialsCollectionsSorted() {
		food.defineRawMaterial("Zucchero", 400, 0, 100, 0);
		food.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
		food.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);

		assertSorted("Raw materials are not sorted",
					 food.rawMaterials(),NutritionalElement::getName);
	}

}
