package diet;

import java.util.*;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;



/**
 * Represents a takeaway restaurant chain.
 * It allows managing restaurants, customers, and orders.
 */
public class Takeaway {
	Map<String, Restaurant> restaurants = new HashMap<>();
	List<Customer> customers = new ArrayList<>();

	/**
	 * Constructor
	 * @param food the reference {@link Food} object with materials and products info.
	 */
	public Takeaway(Food food){
	}

	/**
	 * Creates a new restaurant with a given name
	 *
	 * @param restaurantName name of the restaurant
	 * @return the new restaurant
	 */
	public Restaurant addRestaurant(String restaurantName) {
		Restaurant newRestaurant = new Restaurant(restaurantName);
		restaurants.put(restaurantName, newRestaurant);
		return newRestaurant;
	}

	/**
	 * Retrieves the names of all restaurants
	 *
	 * @return collection of restaurant names
	 */
	public Collection<String> restaurants() {
		return restaurants.keySet();
	}

	/**
	 * Creates a new customer for the takeaway
	 * @param firstName first name of the customer
	 * @param lastName	last name of the customer
	 * @param email		email of the customer
	 * @param phoneNumber mobile phone number
	 *
	 * @return the object representing the newly created customer
	 */
	public Customer registerCustomer(String firstName, String lastName, String email, String phoneNumber) {
		Customer newCustomer = new Customer(firstName, lastName);
		newCustomer.setPhone(phoneNumber);
		newCustomer.SetEmail(email);

		customers.add(newCustomer);

		return newCustomer;
	}

	/**
	 * Retrieves all registered customers
	 *
	 * @return sorted collection of customers
	 */
	public Collection<Customer> customers(){
		return customers.stream()
				.sorted(Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName))
				.collect(Collectors.toList());
	}


	/**
	 * Creates a new order for the chain.
	 *
	 * @param customer		 customer issuing the order
	 * @param restaurantName name of the restaurant that will take the order
	 * @param time	time of desired delivery
	 * @return order object
	 */
	public String findNextHour(String time, String restaurantName){
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime curTime = LocalTime.parse(time,timeFormatter);
		Restaurant curRestaurant = restaurants.get(restaurantName);
		if(curTime.isAfter(curRestaurant.operationalHours.get(curRestaurant.operationalHours.size() -1)[1])){
			return curRestaurant.operationalHours.get(0)[0].format(timeFormatter);
		}
		for(int i = 0; i<curRestaurant.operationalHours.size();i+=1 ){
			if(curTime.isAfter(curRestaurant.operationalHours.get(i)[1]) && curTime.isBefore(curRestaurant.operationalHours.get(i+1)[0])){
				return curRestaurant.operationalHours.get(i+1)[0].format(timeFormatter);
			}

		}
		return time;
	}

	public String formatToHHmm(String timeString){
		String[] parts = timeString.split(":");
		if(parts[0].length()==1){
			parts[0] = "0" + parts[0];
		}
		return parts[0]+":"+parts[1];
	}

	public Order createOrder(Customer customer, String restaurantName, String time) {
		time = formatToHHmm(time);
		String realTime = findNextHour(time, restaurantName);
		Order curOrder = new Order(customer, restaurantName, realTime);
		restaurants.get(restaurantName).addOrder(curOrder);
		return curOrder;
	}

	/**
	 * Find all restaurants that are open at a given time.
	 *
	 * @param time the time with format {@code "HH:MM"}
	 * @return the sorted collection of restaurants
	 */
	public Collection<Restaurant> openRestaurants(String time){
		return restaurants.keySet().stream()
			.filter(x -> restaurants.get(x).isOpenAt(time))
			.map(x -> restaurants.get(x))
			.sorted(Comparator.comparing(Restaurant::getName))
			.collect(Collectors.toList());
	}
}
