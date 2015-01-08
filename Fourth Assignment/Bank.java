/* Title: Bank.java
 * Abstract: The bank class is the blueprint for a working
 * bank object that includes arrays to store account information
 * and customer information. It allows customers to open accounts
 * ,close accounts, get account info and bank information
 */

public class Bank {

	private String name;
	private Account [] accounts = new Account [5];
	private Customer [] customers = new Customer [5];
	private String [] typesOfAccounts = {"Checking", "Savings"};
	
	public Bank(String name) {
		this.name = name;
		
		int i = 0;
		while(i < 5) {
			accounts[i] = new Account();
			customers[i] = new Customer();
			i++;
		}
	}
	
	public boolean newCustomers(String name, String addr, int SSN)
	{
		int i= 0;
		
		if(Customer.getNumOfCustomers() == 5)
		{
			System.out.println("Sorry, there is no more space at this bank, try CitiBank");
			return false;
		}
		
		while(i < 5) {
		if(customers[i].getSsn() == SSN)
		{
			System.out.println("I'm sorry, but it looks like you have already signed up.");
			return false;
		}
			
		i++;
		}
		
		Customer newCustomer = new Customer(name, addr, SSN);
		
		if(Customer.getNumOfCustomers() > 0) {
			// Finds an empty space for a new customer
			while(customers[i].getSsn() != 0 && i < Customer.getNumOfCustomers()) {
				i++;
			}
		}
		
		customers[i] = newCustomer;
		System.out.println("Congratuations, " + name + " for signing up,"
				+ "we look forward to serving you.");
		
		return true;
	}
	
	public boolean openAccount(Customer customer, int accNum, int accType, double balance)
	{
		int i = 0;
		
		if(Account.getNumOfAccounts() == 5)
		{
			System.out.println("I'm sorry, but the Accounts are full");
			return false;
		}
		
		while(i != Account.getNumOfAccounts())
		{
			// Can not open an account with an account number that
			// already exist
			if(accounts[i].getAccNum() == accNum)
			{
				return false;
			}
			i++;
		}
		
	i = 0;
	
	if(Customer.getNumOfCustomers() != 0)
	{
		while(customers[i].getSsn() != customer.getSsn() && i != Customer.getNumOfCustomers())
		{
			i++;
		}
	}
	
	if(i < Customer.getNumOfCustomers())
	{
		System.out.println("Your SSN number is already registered "
				+ "with another account");
		return false;
	}
	
	i = 0;
	
	// Finds an empty space in the accounts array
	if(Account.getNumOfAccounts() > 0) {
		while(accounts[i].getAccNum() != 0) {
			i++;
		}
	}
	
	    customers[i] = customer;
		accounts[i].setAccountHolder(customer);
		accounts[i].setAccNum(accNum);
		accounts[i].setAccType(accType);
		accounts[i].setBalance(balance);
		
		System.out.print("Account Created - " + accounts[i].getAccountHolder().getName() 
		+ ", " + typesOfAccounts[accounts[i].getAccType() -1] + ", " + "No: " + accounts[i].getAccNum()
		+ ", " + "Balance: ");
		
		System.out.printf("%.2f", accounts[i].getBalance());
		System.out.println();
		
		Account.increamentNumOfAccounts();
		Customer.incrementNumOfCustomers();
		
		return true;
	}
	
	public boolean updateAccount(int accNum, double balance)
	{
		int i = 0;
		
		if (balance < 0)
		{
			System.out.println("You can not update account (" + 
			accNum + ") with a negative balance");
			return false;
		}
		
		// Finds account number in the array
		while(i != Account.getNumOfAccounts())
		{
			if(accounts[i].getAccNum() == accNum)
			{
				accounts[i].setAccNum(accNum);
				accounts[i].setBalance(balance);
				return true;
			}
			i++;
		}
		
		System.out.println("Unable to find account.");
		return false;
	}
	
	public boolean closeAccount(int accNum)
	{
		int i = 0;
		
		if(Account.getNumOfAccounts() == 0) {
			System.out.println("There are no accounts to close.");
			return false;
		}
		
		// Finds the account object to close
		while(i != Account.getNumOfAccounts())
		{
			if(accounts[i] != null && accounts[i].getAccNum() == accNum)
			{
				System.out.println("Account (" + accNum + "): Closed - " + accounts[i].getAccountHolder().getName()
				+ ": " + accounts[i].getAccountHolder().getSsn());
				accounts[i] = null;
				customers[i] = null;
				Account.deincrementNumOfAccounts();
				Customer.deincrementNumOfCustomers();
				return true;
			}
			i++;
		}
		
		System.out.println("Account ("+ accNum +"): Nothing to close.");
		return false;
	}
	
	// Prints details of a specific account object
	public boolean accountInfo(int accNum)
	{
		int i = 0;
		
		while(i != Account.getNumOfAccounts())
		{
			if(accounts[i] != null && accounts[i].getAccNum() == accNum)
			{
				System.out.print("Account Info: Number: " + accNum + "\n"
				+ "	"+ typesOfAccounts[accounts[i].getAccType() - 1] + "\n"
				+ "	" + "Balance: $");
				System.out.printf("%.2f", accounts[i].getBalance());
				System.out.println();
				
				System.out.println("Customer: " + accounts[i].getAccountHolder().getName() + "\n"
				+ "	  " + accounts[i].getAccountHolder().getAddress() + "\n" + 
				 "	  SSN: " + accounts[i].getAccountHolder().getSsn() + "\n");
				
				return true;
			}
			i++;
		}
		
		System.out.println("Account Info: Account (" + accNum + ") does not exist");
		
		
		return false;
	}
	
	public void bankInfo() 
	{
		int i = 0;
		double totalBalance = 0.0;
		
		System.out.println("Bank Name: " + this.name + "\n" 
		+ "Number of Customers: " + Customer.getNumOfCustomers());
		
		while(i != Customer.getNumOfCustomers())
		{
			System.out.println("		" + customers[i].getName());
			i++;
		}
		
		System.out.println("Number of Accounts: " + Account.getNumOfAccounts());	
		i = 0;
		
		while(i != Account.getNumOfAccounts())
		{
			
			System.out.print("		" + accounts[i].getAccNum() + ": ");
			System.out.printf("%.2f", accounts[i].getBalance());
			System.out.print(" - " + accounts[i].getAccountHolder().getName());
			System.out.println();
			totalBalance += accounts[i].getBalance();
			i++;
		}
		System.out.println("Bank Total Balance: ");
		System.out.printf("%.2f", totalBalance);
	}
	
}
