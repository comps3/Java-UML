/* Title: Account.java
 * Abstract: The Account class acts as a blueprint for
 * customers accounts. Account objects can store account
 * numbers, account type, Customer accountHolder, balance and
 * it stores the number of accounts with the class.
 */

public class Account {

	private int accNum;
	private int accType;
	private Customer accountHolder;
	private double balance;
	private static int numOfAccounts = 0;

	
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	
	public int getAccType() {
		return accType;
	}
	public void setAccType(int accType) {
		this.accType = accType;
	}
	
	public Customer getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(Customer accountHolder) {
		this.accountHolder = accountHolder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static int getNumOfAccounts() {
		return numOfAccounts;
	}

	public static void increamentNumOfAccounts() {
		Account.numOfAccounts++;
	}
	
	public static void deincrementNumOfAccounts() {
		Account.numOfAccounts--;
	}

}
