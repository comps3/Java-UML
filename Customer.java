/* Title: Customer.java
 * Abstract: The Customer class acts as a blueprint for 
 * a customer to fill in.  Users can enter their name, 
 * address and SSN, he/she will have a new customer 
 * created for them.
 */

public class Customer {

	private String name;
	private int ssn;
	private String address;
	private static int numOfCustomers = 0;
	
	public Customer()
	{
		this.name = "NULL";
		this.ssn = 0000;
		this.address = "No one is home!";
	}
	
	public Customer(String name, String address, int ssn) {
		this.name = name;
		this.address = address;
		this.ssn = ssn;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public static int getNumOfCustomers() {
		return numOfCustomers;
	}

	public static void incrementNumOfCustomers() {
		Customer.numOfCustomers++;
	}
	public static void deincrementNumOfCustomers() {
		Customer.numOfCustomers--;
	}
}
