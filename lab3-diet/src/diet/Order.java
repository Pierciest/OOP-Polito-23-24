package diet;
import java.util.*;



/**
 * Represents and order issued by an {@link Customer} for a {@link Restaurant}.
 *
 * When an order is printed to a string is should look like:
 * <pre>
 *  RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
 *  	MENU_NAME_1->MENU_QUANTITY_1
 *  	...
 *  	MENU_NAME_k->MENU_QUANTITY_k
 * </pre>
 */
public class Order {
	private PaymentMethod curPaymentMethod;
	private OrderStatus curOrderStatus;

	Map<String,Integer> menus = new HashMap<>();

	Customer customer;
	String name;
	String time;

	public Order(Customer customer, String name, String time){
		this.curPaymentMethod = PaymentMethod.CASH;
		this.curOrderStatus = OrderStatus.ORDERED;
		this.customer = customer;
		this.name = name;
		this.time = time;
	}

	/**
	 * Possible order statuses
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED
	}

	/**
	 * Accepted payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD
	}

	/**
	 * Set payment method
	 * @param pm the payment method
	 */
	public void setPaymentMethod(PaymentMethod pm) {
		this.curPaymentMethod = pm;
	}

	/**
	 * Retrieves current payment method
	 * @return the current method
	 */
	public PaymentMethod getPaymentMethod() {
		return this.curPaymentMethod;
	}

	/**
	 * Set the new status for the order
	 * @param os new status
	 */
	public void setStatus(OrderStatus os) {
		this.curOrderStatus = os;
	}

	/**
	 * Retrieves the current status of the order
	 *
	 * @return current status
	 */
	public OrderStatus getStatus() {
		return this.curOrderStatus;
	}

	/**
	 * Add a new menu to the order with a given quantity
	 *
	 * @param menu	menu to be added
	 * @param quantity quantity
	 * @return the order itself (allows method chaining)
	 */
	public Order addMenus(String menu, int quantity) {
		menus.put(menu, quantity);
		return this;
	}

	public String toString(){
		String toPrint = this.name + ", " + this.customer.firstName + " " + this.customer.lastName + " : " + "("+ time + "):";
		for(String key : menus.keySet()){
			toPrint +=  "\n\t" + key + "->" + menus.get(key);
		}
		return toPrint;
	}
	
}
