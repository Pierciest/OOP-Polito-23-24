package diet;
import java.util.*;


/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	Map<String,Double> ingridients = new LinkedHashMap<>(); 
	Map<String,RawMaterial> rawMaterials;
	String name;

	public Recipe(String name, Map<String,RawMaterial> rawMaterials){
		this.name = name;
		this.rawMaterials = rawMaterials;
	}
	
	/**
	 * Adds the given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		this.ingridients.put(material, quantity);
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}


	
	@Override
	public double getCalories() {
		double totalWeight = ingridients.values().stream().reduce(0.0, Double::sum);
		return ingridients.keySet().stream().mapToDouble(x -> rawMaterials.get(x).getCalories() * ingridients.get(x)/totalWeight).sum();
	}


	@Override
	public double getProteins() {
		double totalWeight = ingridients.values().stream().reduce(0.0, Double::sum);
		return ingridients.keySet().stream().mapToDouble(x -> rawMaterials.get(x).getProteins() * ingridients.get(x)/totalWeight).sum();
	}

	@Override
	public double getCarbs() {
		double totalWeight = ingridients.values().stream().reduce(0.0, Double::sum);
		return ingridients.keySet().stream().mapToDouble(x -> rawMaterials.get(x).getCarbs() * ingridients.get(x)/totalWeight).sum();
	}

	@Override
	public double getFat() {
		double totalWeight = ingridients.values().stream().reduce(0.0, Double::sum);
		return ingridients.keySet().stream().mapToDouble(x -> rawMaterials.get(x).getFat() * ingridients.get(x)/totalWeight).sum();
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}

	public String toString(){
		String result = "";
		for(String key : ingridients.keySet()){
			result += key + "\n";
		}
		return result;
	}
	
}
