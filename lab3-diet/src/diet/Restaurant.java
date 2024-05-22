package diet;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant class with given opening times and a set of menus.
 */
public class Restaurant {
	String name;
	List<LocalTime[]> operationalHours = new ArrayList<>();
	Map<String,Menu> menus = new HashMap<>();
	List<Order> orders = new ArrayList<>();


	public Restaurant(String name){
		this.name = name;
	}
	
	/**
	 * retrieves the name of the restaurant.
	 *
	 * @return name of the restaurant
	 */
	public String getName() {
		return this.name;
	}

	public void addOrder(Order order){
		orders.add(order);
	}

	/**
	 * Define opening times.
	 * Accepts an array of strings (even number of elements) in the format {@code "HH:MM"},
	 * so that the closing hours follow the opening hours
	 * (e.g., for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00,
	 * arguments would be {@code "08:15", "14:00", "19:00", "00:00"}).
	 *
	 * @param hm sequence of opening and closing times
	 */
	public void setHours(String ... hm) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("[HH:mm][H:mm]");
		LocalTime tempStart;
		LocalTime tempEnd;
		for(int i = 0; i <hm.length; i+=2){
			tempStart = LocalTime.parse(hm[i],timeFormatter);
			tempEnd = LocalTime.parse(hm[i+1],timeFormatter);
			operationalHours.add(new LocalTime[]{tempStart,tempEnd});
		}
	}

	public String formatToHHmm(String timeString){
		String[] parts = timeString.split(":");
		if(parts[0].length()==1){
			parts[0] = "0" + parts[0];
		}
		return parts[0]+":"+parts[1];
	}

	/**
	 * Checks whether the restaurant is open at the given time.
	 *
	 * @param time time to check
	 * @return {@code true} is the restaurant is open at that time
	 */
	public boolean isOpenAt(String time) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime curTime = LocalTime.parse(formatToHHmm(time), timeFormatter);
	
		for (LocalTime[] hours : operationalHours) {
			if ((curTime.isAfter(hours[0]) || curTime.equals(hours[0])) && (curTime.isBefore(hours[1]) || curTime.equals(hours[1]))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a menu to the list of menus offered by the restaurant
	 *
	 * @param menu	the menu
	 */
	public void addMenu(Menu menu) {
		String menuName = menu.getName();
		menus.put(menuName, menu);
	}

	/**
	 * Gets the restaurant menu with the given name
	 *
	 * @param name	name of the required menu
	 * @return menu with the given name
	 */
	public Menu getMenu(String name) {
		return menus.get(name);
	}

	/**
	 * Retrieve all order with a given status with all the relative details in text format.
	 *
	 * @param status the status to be matched
	 * @return textual representation of orders
	 */
	public String ordersWithStatus(OrderStatus status) {
		orders = orders.stream().sorted(Comparator.comparing(x-> x.toString())).toList();
		String result = "";
		for(Order order : orders){
			if(order.getStatus().equals(status)){
				result += order.toString();
				result += "\n";
			}
		}
		return result;
	}
}
