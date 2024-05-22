package diet;

import java.util.*;
import java.util.stream.Collectors;



/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {

	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */

	 Map<String,RawMaterial> rawMaterials = new HashMap<>();
	 Map<String,Product> products = new HashMap<>();
	 Map<String,Recipe> recipes = new HashMap<>();
	 Map<String,Menu> menus = new HashMap<>();


	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		RawMaterial newRawMaterial = new RawMaterial(name,calories,proteins,carbs,fat);
		this.rawMaterials.put(name, newRawMaterial);
	}

	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials() {
		return new ArrayList<>(rawMaterials.values().stream()
				.sorted(Comparator.comparing(NutritionalElement::getName))
				.collect(Collectors.toList()));
	}

	/**
	 * Retrieves a specific raw material, given its name
	 * @param name  name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name) {
		NutritionalElement result = rawMaterials.get(name);
		return result;
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		Product newProduct = new Product(name, calories, proteins, carbs, fat);
		this.products.put(name, newProduct);
	}

	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products() {
		return new ArrayList<>(products.values().stream()
		.sorted(Comparator.comparing(NutritionalElement::getName))
		.collect(Collectors.toList()));	}

	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name) {
		return products.get(name);
	}

	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe newRecipe = new Recipe(name, rawMaterials);
		recipes.put(name, newRecipe);
		return newRecipe;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes() {
		List<NutritionalElement> result = new ArrayList<>(recipes.values());
		result.sort(Comparator.comparing(NutritionalElement::getName));
		return result;
	}

	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name) {
		return recipes.get(name);
	}

	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu newMenu = new Menu(name, recipes, products);
		menus.put(name, newMenu);
		return newMenu;
	}
}