package diet;


public class Customer {
	String firstName;
	String lastName;
	String email;
	String phone;

	public Customer(String firstName,String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString(){
		String result = this.firstName + " " + this.lastName;
		return result;
	}
}
