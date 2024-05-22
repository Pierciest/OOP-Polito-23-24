package diet;
import java.util.*;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	String name;

	Map<String,Double> usedRecipes = new HashMap<>();
	List<String> usedProducts = new ArrayList<>();
	Map<String,Recipe> recipes;
	Map<String,Product> products;

	public Menu(String name, Map<String,Recipe> recipes, Map<String,Product> products){
		this.name = name;
		this.recipes = recipes;
		this. products = products;
	}
	

	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
    public Menu addRecipe(String recipe, double quantity) {
		usedRecipes.put(recipe, quantity);
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
    public Menu addProduct(String product) {
		usedProducts.add(product);;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		double totalCalories = usedRecipes.keySet().stream().mapToDouble(x->recipes.get(x).getCalories()/100 * usedRecipes.get(x)).sum();
		totalCalories += usedProducts.stream().mapToDouble(x->products.get(x).getCalories()).sum();
		return totalCalories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		double totalProteins = usedRecipes.keySet().stream().mapToDouble(x->recipes.get(x).getProteins()/100 * usedRecipes.get(x)).sum();
		totalProteins += usedProducts.stream().mapToDouble(x->products.get(x).getProteins()).sum();
		return totalProteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		double totalCarbs = usedRecipes.keySet().stream().mapToDouble(x->recipes.get(x).getCarbs()/100 * usedRecipes.get(x)).sum();
		totalCarbs += usedProducts.stream().mapToDouble(x->products.get(x).getCarbs()).sum();
		return totalCarbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		double totalFat = usedRecipes.keySet().stream().mapToDouble(x->recipes.get(x).getFat()/100 * usedRecipes.get(x)).sum();
		totalFat += usedProducts.stream().mapToDouble(x->products.get(x).getFat()).sum();
		return totalFat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return false;
	}
}