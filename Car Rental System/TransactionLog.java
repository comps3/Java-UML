/*
 * Title: TransactionLog.java
 * Abstract: This class manages all transactions performed
 * on the Car Rental System.  
 */

import java.util.*;
import java.text.*;

public class TransactionLog {

	public static int transactionNum = 1111;
	public static int reservationNum = 2111;
	
	private String transactionType;
	private int transactionRecord;
	private int reservationRecord;
	private String username;
	private Date transactionDateTime;
	
	public static ArrayList<TransactionLog>login = new ArrayList();
	
	private Date pickUpDate;
	private Date returnDate;
	private String carType;
	private double totalAmount;
	
	
	SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy hh:mm'('a')'");
	
	public TransactionLog(String transactionType, String username, String password, Date current)
	{
		this.setTransactionType(transactionType);
		this.setUsername(username);
		this.setTransactionDateTime(current);
		this.setTransactionRecord(transactionNum);
		this.transactionNum++;
		this.carType = new String();

	}
	
	public TransactionLog(String transactionType, String username, Date pickup, Date dropoff, String carType, double
			totalAmount, Date current)
	{
		this.setTransactionType(transactionType);
		this.setUsername(username);

		this.setPickUpDate(pickup);
		this.setReturnDate(dropoff);
		this.setCarType(carType);
		this.setTotalAmount(totalAmount);
		this.setTransactionDateTime(current);
	
		this.setTransactionRecord(transactionNum);
		this.setReservationRecord(reservationNum);
		
		this.transactionNum++;
		this.reservationNum++;
		
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType2) {
		this.transactionType = transactionType2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTransactionRecord() {
		return transactionRecord;
	}

	public void setTransactionRecord(int transactionRecord) {
		this.transactionRecord = transactionRecord;
	}

	public int getReservationRecord() {
		return reservationRecord;
	}

	public void setReservationRecord(int reservationRecord) {
		this.reservationRecord = reservationRecord;
	}


	
}
