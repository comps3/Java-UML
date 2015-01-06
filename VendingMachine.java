/*
 * Title: VendingMachine.java
 * Abstract: This class acts as a blueprint for a Vending Machine instance. It 
 * contains functions that allows the user to pick, return and purchase from 
 * the four items in the machine.
 */
import java.util.Scanner;

public class VendingMachine {

	private int name;
	private String location = "UNKNOWN";
	private double total;
	private double tax;
	private double totalEarnings;
	private int [] vendingMachineItems;
	private int [] itemsSold;
	private int [] usersCart;
	private int [] boughtItems;
	
	private String[] itemNames = {"Water", "Regular Coffee", "Sun Chip", "Chocolate Bar" };
	private double [] prices = {1.50, 2.00, 1.00, 2.50};
	private int items = 4;
	
	
	VendingMachine(int name) {
		this.name = name;
		vendingMachineItems = new int [4];
		usersCart = new int [4];
		itemsSold = new int [4];
		boughtItems = new int [4];
	}
	
	VendingMachine(int name, String local) {
		this.name = name;
		this.location = local;
		vendingMachineItems = new int [items];
		usersCart = new int [items];
		itemsSold = new int [items];
		boughtItems = new int [4];
	}
	
	// Displays vending machine statistics
	public String toString()
	{
		return "Serial Number: " + name + "\n" + "Location: " + location + "\n"
				+ "Contents: " + "\n" + "  Water: " + vendingMachineItems[0] + "\n" 
				+ "  Coffee: " + vendingMachineItems[1]
				+ "\n" + "  Sun Chip: " + vendingMachineItems[2] + "\n" + "  Chocolate Bar: " + vendingMachineItems[3]
				;
	}
	
	// Checks if two vending machine objects are equivalent 
	// by checking its properties
	public boolean equals(VendingMachine two)
	{
		return this.name == two.name && this.location == two.location &&
				this.vendingMachineItems[0] == two.vendingMachineItems[0] && 
				this.vendingMachineItems[1] == two.vendingMachineItems[1] && 
				this.vendingMachineItems[2] == two.vendingMachineItems[2] &&
				this.vendingMachineItems[3] == two.vendingMachineItems[3];
	}
	
	public void setName(int name) {
		this.name = name;
	}
	
	public void setLocation(String local)
	{
		location = local;
	}
	
	public void reset(int item1, int item2, int item3, int item4) {
		vendingMachineItems[0] = item1;
		vendingMachineItems[1] = item2;
		vendingMachineItems[2] = item3;
		vendingMachineItems[3] = item4;
		
	}
	
	public void addItems(int item1, int item2, int item3, int item4) {
		vendingMachineItems[0] += item1;
		vendingMachineItems[1] += item2;
		vendingMachineItems[2] += item3;
		vendingMachineItems[3] += item4;
	}
	
	public void displayMenu() {
		System.out.println("===== Vending Machine Menu ===== ");
		System.out.println("   1. Water............$1.50");
		System.out.println("   2. Regular Coffee...$2.00");
		System.out.println("   3. Sun Chip.........$1.00");
		System.out.println("   4. Chocolate Bar....$2.50");
	}
	
	public void buyItem() {
		Scanner input = new Scanner(System.in);
		int item = 0;
		int qty = 0;
		
		
		System.out.print("Select an item number: ");
		item = input.nextInt();
		System.out.print("How many do you want to buy? ");
		qty = input.nextInt();
		System.out.println("You selected " + itemNames[item-1] + ". Quantity: " + qty);
		
		if(vendingMachineItems[item-1] == 0)
		{
			System.out.println("Selection failed. We have run out of "  + itemNames[item-1]);
			System.out.println();
			return;
		}
		else if ((vendingMachineItems[item-1] - qty) < 0)
		{
			System.out.println("Selection failed. We don't have enough "  + itemNames[item-1]);
			System.out.println();
			return;
		}
		else {
			// Moving user's desired items from Vending
			// machine stock to user's cart
			usersCart[item-1] += qty;
			vendingMachineItems[item-1] -= qty;
			return;
		}
		
	}
	
	public boolean buyItem(int item, int qty)
	{
		System.out.println("You selected " + itemNames[item-1] + ". Quantity: " + qty);
		
		if(vendingMachineItems[item-1] == 0)
		{
			System.out.println("Selection failed. We have run out of "  + itemNames[item-1]);
			System.out.println();
			return false;
		}
		else if ((vendingMachineItems[item-1] - qty) < 0)
		{
			System.out.println("Selection failed. We don't have enough "  + itemNames[item-1]);
			System.out.println();
			return false;
		}
		else {
			usersCart[item-1] += qty;
			vendingMachineItems[item-1] -= qty;
			return true;
		}
	}
	
	public void returned(int item, int qty) 
	{
		if(usersCart[item-1] == 0) {
			return;
		}
		else if ((usersCart[item-1] - qty) < 0) {
			return;
		}
		else {
			// Returns items from the users cart
			// back to vending machine
			usersCart[item-1] -= qty;
			vendingMachineItems[item-1] += qty;
			return;
		}
		
	}
	public boolean payment() {
		
		Scanner input = new Scanner(System.in);
		total = 0;
		tax = 0;
		String usersPay = null;
		double userMoney = 0.0;
		
		for (int i =0; i < items; i++)
		{
			total += usersCart[i] * prices[i];
		}
		
		tax = total * 0.1;
		total += tax;
		
		System.out.print("Enter money amount: $ ");
		usersPay = input.next();
		userMoney = Double.parseDouble(usersPay);
		
		if (userMoney < total) {
			System.out.printf("Insufficient money. $%.2f", userMoney);
			//System.out.printf("%6.2f", userMoney);
			System.out.println(" returned ");
			return false;
		}
		else if (userMoney > total) {
			System.out.printf("Sufficient money. $%.2f" ,userMoney - total);
			System.out.println("  returned ");
			totalEarnings += total;
			
			for(int i =0; i < items; i++)
			{
				itemsSold[i] += usersCart[i];
				boughtItems[i] += usersCart[i];
				usersCart[i] = 0;
			}
			
			return true;
		}
		else {
			System.out.println("Sufficient money. $0.00 returned ");
			return true;
		}	
	}
	
	public void displayReceipt()
	{
		
		for(int i =0; i < items; i++)
		{
			if(boughtItems[i] != 0) {
				System.out.printf("  " + itemNames[i] + ":$%.2f", prices[i]);  
				System.out.print(" X " + boughtItems[i]);
				System.out.printf(" =  $%.2f" , (prices[i] * boughtItems[i]));
			}
			boughtItems[i] = 0;
		}
		System.out.println();
		System.out.print("  ");
		System.out.print("Tax (10.0%):  $");
		System.out.printf("%.2f" , tax);
		System.out.println();
		System.out.print("  ");
		System.out.print("Total: $");
		System.out.printf("%.2f" , total);
		System.out.println();
	}
	
	public void status() {

		System.out.println("Serial Number: " + name);
		System.out.println("Location: " + location);
		System.out.println("Sold Items: ");
		
		for(int i = 0; i < items; i++)
		{
			System.out.println("  " + itemNames[i] + ": " + itemsSold[i]);
		}
		
		System.out.println("Current Contents: ");
		
		for(int i = 0; i < items; i++)
		{
			System.out.println("  " + itemNames[i] + ": " + vendingMachineItems[i]);
		}
		
		System.out.printf("Total Earnings: $%.2f", totalEarnings);
		System.out.println();
	}
	
}
