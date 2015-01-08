/*
 * Title: CarRentalSystem.java
 * Abstract: This file contains the inner works of a real-life
 * car rental system. Users are able to do all things through this 
 * program. Enjoy!
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CarRentalSystem {

    public static void main(String[] args) {
        
        JFrame frame = new MainMenuFrame();
        frame.setVisible(true);
        
        // Logs 'admin2' account as the first account
        TransactionLog.login.add(new TransactionLog("new account", "admin2", "admin2" , new Date()));
        // Adds 'admin2' login credentials to the login system
        LoginSystem.userCredentials.add(new LoginSystem("admin2", "admin2"));
        
    }
}

 
class MainMenuFrame extends JFrame {
    
    public MainMenuFrame()
    {
        setTitle("CSUMB Car Rental Reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new MainMenuPanel();
        this.add(panel);
        this.setSize(600, 400);
        
    }
}


class MainMenuPanel extends JPanel 
{
    
    private JButton makeAccount,
                    makeReservation,
                    cancelReservation;
        
    private class MakeAccountListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new MakeAccountFrame();
            frame.setVisible(true);
        }
    }
    
    private class MakeReservationListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new MakeReservationFrame();
            frame.setVisible(true);
        }
    }
    
    private class CancelReservationListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new LoginFrame();
            frame.setVisible(true);
        }
    }
        
    public MainMenuPanel()
    {
        this.setLayout(new GridBagLayout());
        
        // Initializing and customizing buttons for the main
        // menu window
        makeAccount = new JButton("Create Account");
        makeReservation = new JButton("Make Reservation");
        cancelReservation = new JButton("Cancel Reservation");
        
        makeAccount.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        makeReservation.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        cancelReservation.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        
        makeAccount.setForeground(Color.blue);
        makeReservation.setForeground(Color.blue);
        cancelReservation.setForeground(Color.blue);
        
        // Connect buttons to ActionListners
        makeAccount.addActionListener(new MakeAccountListener());
        makeReservation.addActionListener(new MakeReservationListener());
        cancelReservation.addActionListener(new CancelReservationListener());
        
        // Setting up the buttons position
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(makeAccount, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        this.add(makeReservation, gc);
        gc.gridx = 4;
        gc.gridy = 2;
        this.add(cancelReservation, gc);
 
    }
}


class MakeAccountFrame extends JFrame 
{
    public MakeAccountFrame()
    {
        setTitle("Create Account");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new MakeAccountPanel();
        this.add(panel);        
        this.setSize(600, 400);
    }    
}


class MakeAccountPanel extends JPanel 
{    
    private JButton submitButton,
                    cancelButton;
    private JTextField username,
    				   password;
    private JLabel usernameL, 
    			   passwordL;
    private int tries = 0;
    boolean flag = true;

    
    // Listener for the Submit button
    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
	         // Checks if the users username and password have not fulfilled login
           // credential requirements.
        	 if(username.getText().length() < 5 && password.getText().length() < 5)
             {
        		 tries++;
        		 if(tries == 2)
        		 {
        			JOptionPane.showMessageDialog(null, "You have exceeded the maximum amount of tries.");
             		backToMain(); 
        		 }
        		 else {
             	JOptionPane.showMessageDialog(null, "The username AND password needs to five characters long.");
        		 }
             	
             }
        	 else if(username.getText().length() < 5)
            {
        		 tries++;
        		 if(tries == 2)
        		 {
        			JOptionPane.showMessageDialog(null, "You have exceeded the maximum amount of tries.");
             		backToMain(); 
        		 }
        		 else {
            	JOptionPane.showMessageDialog(null, "The username needs to five characters long.");
        		 }
   
            }
        	 else if(password.getText().length() < 5)
            {
        		 tries++;
        		 if(tries == 2)
        		 {
        			JOptionPane.showMessageDialog(null, "You have exceeded the maximum amount of tries.");
             		backToMain(); 
        		 }
        		 else {
            	JOptionPane.showMessageDialog(null, "The password needs to five characters long.");
        		 }
            	
            }
        	 
        	 if(LoginSystem.userCredentials.isEmpty())
        	 {
        		 LoginSystem userCred = new LoginSystem(username.getText(), password.getText());
        		 LoginSystem.userCredentials.add(userCred);
        		 TransactionLog newUser = new TransactionLog("new account" , username.getText(), password.getText(), new Date());
            	 TransactionLog.login.add(newUser);
            	 JOptionPane.showMessageDialog(null, "Your accout was created successfully!.");
          		 backToMain(); 
        		 
        	 }
        	 else {
        		 
             // Check if username already exists
        		 for(int i = 0; i < LoginSystem.userCredentials.size(); i++)
        		 {
        			 if(username.getText().equals(LoginSystem.userCredentials.get(i).getUsername()))
        			 { 
        				 JOptionPane.showMessageDialog(null, "The username has already been taken. Please enter a new username and password");
        				 username.setText("");
        				 password.setText("");
        			     tries++;
        			     flag = false;
        			     break;
        			 }
        			 
        		 }
        		 
           		 if(tries == 2)
    	   		 {
    	        	backToMain();
    	        	return;
    	   		 }
        		 
             // Insert users login credentials into 'LoginSystem' and 'TransactionLog'
        		 if(flag == true) {
	        		 LoginSystem userCred = new LoginSystem(username.getText(), password.getText());
	        		 LoginSystem.userCredentials.add(userCred);
	        		 TransactionLog newUser = new TransactionLog("new account" , username.getText(), password.getText(), new Date());
	            	 TransactionLog.login.add(newUser);
	            	 JOptionPane.showMessageDialog(null, "Your accout was created successfully!.");
	          		 backToMain(); 
        		 }
        		 
        		 flag = true;
          		 
        	 }
        }
    }

    // Listener for the Cancel button
    private class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            backToMain();
        }
    }
    
    // Goes back to the main menu frame
    public void backToMain()
    {
        (SwingUtilities.getWindowAncestor(this)).dispose(); 
    }    
    
    
    // Creates the new MakeAccountPanel
    public MakeAccountPanel()
    {
        this.setLayout(new GridBagLayout());
        
        // Initialize, customize, and insert GUI elements
        // onto window
        usernameL = new JLabel("Username: ");
        username = new JTextField(15);
        passwordL = new JLabel("Password: ");
        password = new JTextField(15);
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        
        usernameL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        passwordL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        submitButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
      
        usernameL.setForeground(Color.blue);
        passwordL.setForeground(Color.blue);
        submitButton.setForeground(Color.blue);
        cancelButton.setForeground(Color.blue);
        
        submitButton.addActionListener(new SubmitListener());
        cancelButton.addActionListener(new CancelListener());
                
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(usernameL, gc);
        gc.gridx = 1;
        this.add(username, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(passwordL, gc);
        gc.gridx = 1;
        this.add(password, gc);
        gc.gridy = 2;
        this.add(submitButton, gc);
        gc.gridy = 3;
        this.add(cancelButton, gc);
        
    }
}

class MakeReservationFrame extends JFrame 
{
    public MakeReservationFrame()
    {
        setTitle("Make Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new MakeReservationPanel();
        this.add(panel);        
        this.setSize(1024, 768);
    }    
}

class MakeReservationPanel extends JPanel 
{   
    private JRadioButton firstCarRadioButton,
    					 secondCarRadioButton,
    					 thirdCarRadioButton;
    private JButton submitButton,
                    cancelButton;
    private JLabel monthLabel, monthLabel2,
    			   yearLabel, yearLabel2,
    			   carHeading, firstCar,
    			   secondCar, thirdCar, firstCarPrice,
    			   secondCarPrice, thirdCarPrice; 
    
    private JList dates, dates2, hours, hours2, amPm, amPm2;
    
    private JTextField username,
	   password;
	private JLabel usernameL, 
		passwordL;
	
	private boolean flag = true;
	private int tries = 0;
	private String finalMsg;
	private double totalOwed = 0.0;
	
	int days = 0;
	int	pHours = 0;
	int dHours = 0;
	int finalHours = 0;
	int carSelected = 0;
	String pickupInfo;
	String dropoffInfo;
	Date pickupDate = new Date();
	Date dropoffDate = new Date();
	String carSelectedName;
	
	SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    // Listener for the Submit button
    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        
        	flag = true;	
        	
   		 for(int i = 0; i < LoginSystem.userCredentials.size(); i++)
   		 {
   			 if(username.getText().equals(LoginSystem.userCredentials.get(i).getUsername()) && !username.getText().equals("admin2"))
   			 { 
   				 if(password.getText().equals(LoginSystem.userCredentials.get(i).getPassword())) {
	   				 
   					 JOptionPane.showMessageDialog(null, "Your login information is valid");
   					 finalMsg = "Username: " + username.getText() + "\n" + "Pickup Date & Time: 12/" + (String)dates.getSelectedValue() 
   							 + "/14 " + (String)hours.getSelectedValue() + ":00 " + (String)amPm.getSelectedValue() + "\n";
   					 finalMsg += "Dropoff Date & Time: 12/" + (String)dates2.getSelectedValue() + "/14 " + (String)hours2.getSelectedValue() +
   							  ":00 " + (String)amPm2.getSelectedValue() + "\n";
   					
   					 days = Integer.parseInt((String)dates2.getSelectedValue()) - Integer.parseInt((String)dates.getSelectedValue());
   					 
   					 if(amPm.getSelectedValue().equals("PM"))
   					 {
   						 pHours = Integer.parseInt((String)hours.getSelectedValue()) + 12;
   						 pickupInfo = "12/" + (String)dates.getSelectedValue() + "/14 " + Integer.toString(pHours) + ":00:00";
   					 }
   					 else {
   						pickupInfo = "12/" + (String)dates.getSelectedValue() + "/14 " + hours.getSelectedValue() + ":00:00";
   					 }
   					
   					 if(amPm2.getSelectedValue().equals("PM"))
   					 {
   						dHours = Integer.parseInt((String)hours2.getSelectedValue()) + 12;
   						dropoffInfo = "12/" + (String)dates2.getSelectedValue() + "/14 " + Integer.toString(dHours) + ":00:00";
   						
   					 }
   					 else {
   						dropoffInfo = "12/" + (String)dates2.getSelectedValue() + "/14 " + hours2.getSelectedValue() + ":00:00";
   					 }
   					 
   					 // Check this!
   					finalHours = Integer.parseInt((String)hours2.getSelectedValue()) - Integer.parseInt((String)hours.getSelectedValue());
   					 
   					 if(days < 0)
   					 {
   					   JOptionPane.showMessageDialog(null, "Your pickup date can not happen before your dropoff date!");
   					   flag = false;
   					   break;
   					 }
   					 
   					 if(finalHours < 0 && days == 0)
   					 {
   					   JOptionPane.showMessageDialog(null, "Your pickup time can not happen before your dropoff time!");
       				   flag = false;
       				   break;
   					 }
   					
   					
   					 try {
						pickupDate = ft.parse(pickupInfo);
						dropoffDate = ft.parse(dropoffInfo);
						
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
   					 
   					if(firstCarRadioButton.isSelected())
   					{
   						carSelected = 0;
   					}
   					if(secondCarRadioButton.isSelected())
   					{
   						carSelected = 1;
   					}
   					if(thirdCarRadioButton.isSelected())
   					{
   						carSelected = 2;
   					}
   					
   					 
   					 // Check if the car selected is available at selected time
   					 for(int j = 0; j < TransactionLog.login.size(); j++)
   					 {
   						 
   						 if(carSelected == 0)
   						 {
   							 if(TransactionLog.login.get(j).getCarType().equals("Minivan"))
   							 {  
   								 if(TransactionLog.login.get(j).getPickUpDate().before(pickupDate)
   									&& TransactionLog.login.get(j).getReturnDate().after(pickupDate))
   								 {
   									 JOptionPane.showMessageDialog(null, "There was another person who reserved the same vehicle before you!");
   									 System.out.println(TransactionLog.login.get(j).getPickUpDate().toString());
   									 return;
   								 }
   							 }
   							 
   						 }
   						 
   						 else if(carSelected == 1)
   						 {
   							 if(TransactionLog.login.get(j).getCarType().equals("Sedan"))
   							 {
   								 if(TransactionLog.login.get(j).getPickUpDate().before(pickupDate)
   									&& TransactionLog.login.get(j).getReturnDate().after(pickupDate))
   								 {
   									 JOptionPane.showMessageDialog(null, "There was another person who reserved the same vehicle before you!");
   									 System.out.println(TransactionLog.login.get(j).getPickUpDate().toString());
   									 return;
   								 }

   								 
   							 }
   							 
   						 }
   						 
   						 else {
   							 if(TransactionLog.login.get(j).getCarType().equals("Truck"))
   							 {
   								 if(TransactionLog.login.get(j).getPickUpDate().before(pickupDate)
   									&& TransactionLog.login.get(j).getReturnDate().after(pickupDate))
   								 {
   									 JOptionPane.showMessageDialog(null, "There was another person who reserved the same vehicle before you!");
   									 System.out.println(TransactionLog.login.get(j).getPickUpDate().toString());
   									 return;
   								 }
   								 
   							 }
   							 
   						 }
   						 
   					 }
   					 
   					 if(firstCarRadioButton.isSelected())
   					 {
   						 finalMsg += "Vehicle: Minivan" + "\n";
   						 carSelectedName = "Minivan";
   						 if(days == 0)
   	   					 {
   							 totalOwed = 50;
   	   					 } 
   						 else {
   							 totalOwed = days * 50;
   							 
   							 if(finalHours > 0)
   							 {
   								 totalOwed += 50;
   							 }
   							 
   						 }
   						 
   					 }
   					 if(secondCarRadioButton.isSelected())
   					 {
   						 finalMsg += "Vehicle: Sedan" + "\n";
 						 carSelectedName = "Sedan";
   						 
   						 if(days == 0)
   	   					 {
   							 totalOwed = 25;
   	   					 } 
   						 else {
   							 totalOwed = days * 25;
   							 
   							 if(finalHours > 0)
   							 {
   								 totalOwed += 25;
   							 }
   						 }
   					 }
   					 if(thirdCarRadioButton.isSelected())
   					 {
   						 finalMsg += "Vehicle: Truck" + "\n";
   						 carSelectedName = "Truck";
 						 
   						 if(days == 0)
   	   					 {
   							 totalOwed = 35;
   	   					 } 
   						 else {
   							 totalOwed = days * 35;
   							 
   							 if(finalHours > 0)
   							 {
   								 totalOwed += 35;
   							 }
   						 }
   					 }
   					 
   					 finalMsg += "Reservation Number: " + TransactionLog.reservationNum + " \n";
   					 finalMsg += "Total Cost: $" + totalOwed;
   					 
   					 JDialog.setDefaultLookAndFeelDecorated(true);
   				    int response = JOptionPane.showConfirmDialog(null, finalMsg, "Confirm Rental Reservation",
   				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   				    
   				   if (response == JOptionPane.NO_OPTION) {
   					   
   				    } 
   				   	
   				   else if (response == JOptionPane.YES_OPTION) {
   				    	JOptionPane.showMessageDialog(null, "Your reservation was created successfully!.");
   				    	TransactionLog.login.add(new TransactionLog("reservation", username.getText(), pickupDate, dropoffDate
   				    			,carSelectedName, totalOwed, new Date()));
   				    	backToMain();
   				    } 
   				   
   				   else if (response == JOptionPane.CLOSED_OPTION) {
   				       
   				    }
   					 
	   				 flag = false;
	   				 break;
   				 }
   			 }
   			 
   			 
   		 }
   		 
   		 if(flag == true && tries < 1)
   		 {
   			JOptionPane.showMessageDialog(null, "Your login information is invalid");
   			tries++;
   		 }
   		 else if(flag == true && tries == 1) {
   			tries++;
   		 }
   		 
   		 if(tries == 2)
   		 {
   			JOptionPane.showMessageDialog(null, "You have exceeded the maximum amount of tries.");
   			backToMain();
   		 }
        	
        }
    }

    // Listener for the Cancel button
    private class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            backToMain();
        }
    }
    
    // Goes back to the main menu frame
    public void backToMain()
    {
        (SwingUtilities.getWindowAncestor(this)).dispose(); 
    }    
    
    
    // Creates the new MakeAccountPanel
    public MakeReservationPanel()
    {
    	String [] dayArray = new String[32];
    	String [] timeArray = new String[14];
    	
    	for(int i= 1; i < 32; i++)
    	{
    		dayArray[i] = new Integer(i).toString();
    	}
    	
    	for(int i = 1; i < 13; i++)
    	{
    		timeArray[i] = new Integer(i).toString();
    	}
    	

    	String [] timeMode = {"AM", "PM"};
    	
    	dates = new JList(dayArray);
    	dates.setFixedCellWidth(40);
    	dates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dates.setVisibleRowCount(30);
        JScrollPane datesScrollPane = new JScrollPane(dates);
        
    	dates2 = new JList(dayArray);
    	dates2.setFixedCellWidth(40);
    	dates2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dates2.setVisibleRowCount(30);
        JScrollPane datesScrollPane2 = new JScrollPane(dates2);
        
        hours = new JList(timeArray);
        hours.setFixedCellWidth(40);
        hours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hours.setVisibleRowCount(30);
        JScrollPane hoursList = new JScrollPane(hours);
        
        hours2 = new JList(timeArray);
        hours2.setFixedCellWidth(40);
        hours2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hours2.setVisibleRowCount(30);
        JScrollPane hoursList2 = new JScrollPane(hours2);
        
        amPm = new JList(timeMode);
        amPm.setFixedCellWidth(40);
        amPm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        amPm.setVisibleRowCount(3);
        JScrollPane timeModeList = new JScrollPane(amPm);
        
        amPm2 = new JList(timeMode);
        amPm2.setFixedCellWidth(40);
        amPm2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        amPm2.setVisibleRowCount(3);
        JScrollPane timeModeList2 = new JScrollPane(amPm2);
    	
        setLayout(new GridBagLayout());
        
        //Sets the buttons
        monthLabel = new JLabel("December ");
        monthLabel2 = new JLabel("December");
        yearLabel = new JLabel("2014");
        yearLabel2 = new JLabel("2014");
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        carHeading = new JLabel("Cars");
        firstCar = new JLabel("Minivan");
        secondCar = new JLabel("Sedan");
        thirdCar = new JLabel("Truck");
        firstCarPrice = new JLabel("$50.00");
        secondCarPrice = new JLabel("$25.00");
        thirdCarPrice = new JLabel("$35.00");
        firstCarRadioButton = new JRadioButton();
        secondCarRadioButton = new JRadioButton();
        thirdCarRadioButton = new JRadioButton();
        
        usernameL = new JLabel("Username: ");
        username = new JTextField(15);
        passwordL = new JLabel("Password: ");
        password = new JTextField(15);
        
        monthLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        monthLabel2.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        yearLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        yearLabel2.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
      
        monthLabel.setForeground(Color.blue);
        monthLabel2.setForeground(Color.blue);
        yearLabel.setForeground(Color.blue);
        yearLabel2.setForeground(Color.blue);
        
        
        
        carHeading.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        firstCar.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        secondCar.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        thirdCar.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
      
        carHeading.setForeground(Color.blue);
        firstCar.setForeground(Color.blue);
        secondCar.setForeground(Color.blue);
        thirdCar.setForeground(Color.blue);
        
        
        firstCarPrice.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        secondCarPrice.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        thirdCarPrice.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));

        firstCarPrice.setForeground(Color.blue);
        secondCarPrice.setForeground(Color.blue);
        thirdCarPrice.setForeground(Color.blue);
        
        
        usernameL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        passwordL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        submitButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
      
        usernameL.setForeground(Color.blue);
        passwordL.setForeground(Color.blue);
        submitButton.setForeground(Color.blue);
        cancelButton.setForeground(Color.blue);
        
        submitButton.addActionListener(new SubmitListener());
        cancelButton.addActionListener(new CancelListener());
                

        add(monthLabel, getConstraints(0,0,1,1, GridBagConstraints.NORTH));
        add(datesScrollPane, getConstraints(1,0,2,1, GridBagConstraints.NORTH));
        add(yearLabel, getConstraints(3,0,1,1, GridBagConstraints.NORTH));
        add(hoursList, getConstraints(4,0,1,1, GridBagConstraints.NORTH));
        add(timeModeList, getConstraints(5,0,1,1, GridBagConstraints.NORTH));
        
        // Add hour list, time mode for both fields.
        add(monthLabel2, getConstraints(0,1,1,1, GridBagConstraints.NORTH));
        add(datesScrollPane2, getConstraints(1,1,2,1, GridBagConstraints.NORTH));
        add(yearLabel2, getConstraints(3,1,1,1, GridBagConstraints.NORTH));
        add(hoursList2, getConstraints(4,1,1,1, GridBagConstraints.NORTH));
        add(timeModeList2, getConstraints(5,1,1,1, GridBagConstraints.NORTH));
        
        add(carHeading, getConstraints(1,4,1,1,GridBagConstraints.NORTH));
        
        add(firstCar, getConstraints(0,5,1,1, GridBagConstraints.NORTH));
        add(firstCarPrice, getConstraints(1,5,1,1,GridBagConstraints.NORTH));
        add(firstCarRadioButton, getConstraints(2,5,1,1, GridBagConstraints.NORTH));
        
        add(secondCar, getConstraints(0,6,1,1, GridBagConstraints.NORTH));
        add(secondCarPrice, getConstraints(1,6,1,1,GridBagConstraints.NORTH));
        add(secondCarRadioButton, getConstraints(2,6,1,1,GridBagConstraints.NORTH));

        add(thirdCar, getConstraints(0,7,1,1, GridBagConstraints.NORTH));
        add(thirdCarPrice, getConstraints(1,7,1,1, GridBagConstraints.NORTH));
        add(thirdCarRadioButton, getConstraints(2,7,1,1, GridBagConstraints.NORTH));
        
        add(usernameL, getConstraints(0,8,1,1, GridBagConstraints.NORTH));
        add(username, getConstraints(1,8,1,1, GridBagConstraints.NORTH));
        
        add(passwordL, getConstraints(0,9,1,1, GridBagConstraints.NORTH));
        add(password, getConstraints(1,9,1,1, GridBagConstraints.NORTH));
        
        add(submitButton, getConstraints(2,11,1,1,GridBagConstraints.NORTH));
        add(cancelButton, getConstraints(2,12,1,1, GridBagConstraints.NORTH));
        
    }
    
    private GridBagConstraints getConstraints(int gridx, int gridy,
            int gridwidth, int gridheight, 
            int anchor)
	{
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(5, 5, 5, 5);
			c.ipadx = 0;
			c.ipady = 0;
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = gridwidth;
			c.gridheight = gridheight;
			c.anchor = anchor;
			return c;
	}
}

class LoginFrame extends JFrame 
{
    public LoginFrame()
    {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new LoginPanel();
        this.add(panel);        
        this.setSize(600, 400);
    }    
}


class LoginPanel extends JPanel 
{    
    private JButton submitButton,
                    cancelButton;
    private JTextField username,
    				   password;
    private JLabel usernameL, 
    			   passwordL;
    private int tries = 0;
    
    boolean flag = true;
    boolean found = true;
    String adminMsg = new String();
    
    // Listener for the Submit button
    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        	 for(int i = 0; i < LoginSystem.userCredentials.size(); i++)
       		 {
       			 if(username.getText().equals(LoginSystem.userCredentials.get(i).getUsername()) && !username.getText().equals("admin2"))
       			 { 
       				 if(password.getText().equals(LoginSystem.userCredentials.get(i).getPassword())) {
       				 
	       		     	 for(int j = 0; j < TransactionLog.login.size(); j++)
	       		   		 {
	       		   			 if(username.getText().equals(TransactionLog.login.get(j).getUsername()) && TransactionLog.login.get(j).getReservationRecord() != 0)
	       		   			 { 
		       		   			 JFrame frame = new CancelReservationFrame(username.getText());
		        		         frame.setVisible(true);
		        		         flag = false;
		        		         found = false;
	       		   			 }
	       		   		 }
       		          
	       		    if(found == true) { 	 
	       		     	JOptionPane.showMessageDialog(null, "You do not have any reservations on file.");
	       		     	flag = false;
	       		     	backToMain();
	       		    }
	       		    
       				 }
       			 }
       			 else if(username.getText().equals(LoginSystem.userCredentials.get(i).getUsername()) && username.getText().equals("admin2"))
       			 {
       				if(password.getText().equals(LoginSystem.userCredentials.get(i).getPassword())) {
       					
       					flag = false;
       				 
       					for(int j = 0; j < TransactionLog.login.size(); j++) {
       						
       						if(TransactionLog.login.get(j).getTransactionType().equals("new account"))
       						{
       							adminMsg += TransactionLog.login.get(j).getTransactionRecord() + " "
       									+ TransactionLog.login.get(j).getTransactionType() + " "
       									+ TransactionLog.login.get(j).getUsername() + " "
       									+ TransactionLog.login.get(j).getTransactionDateTime() + "\n\n";
       						}
       						if(TransactionLog.login.get(j).getTransactionType().equals("reservation"))
       						{
       							adminMsg += TransactionLog.login.get(j).getTransactionRecord() + " "
       									+ TransactionLog.login.get(j).getTransactionType() + " "
       									+ TransactionLog.login.get(j).getUsername() + " \n"
       									+ TransactionLog.login.get(j).getPickUpDate().toString() + " \n"
       									+ TransactionLog.login.get(j).getReturnDate().toString() + " \n"
       									+ TransactionLog.login.get(j).getReservationRecord() + " "
       									+ TransactionLog.login.get(j).getCarType() + " $"
       									+ TransactionLog.login.get(j).getTotalAmount() + " \n"
       									+ TransactionLog.login.get(j).getTransactionDateTime() + "\n\n";
       						}
       						if(TransactionLog.login.get(j).getTransactionType().equals("cancelation"))
       						{
       							adminMsg += TransactionLog.login.get(j).getUsername() + " "
       									+   TransactionLog.login.get(j).getCarType() + " "
       									+ 	TransactionLog.login.get(j).getPickUpDate().toString() + " \n"
       									+ 	TransactionLog.login.get(j).getReturnDate().toString() + " \n"
       									+ 	TransactionLog.login.get(j).getTransactionType() + " "
       									+ 	TransactionLog.login.get(j).getTransactionDateTime() + "\n\n"; 
       						}
       						
       					}
       					
       					int response = JOptionPane.showConfirmDialog(null, adminMsg + "\n" + "Would you like to exit?"
               			  , "Rental Car Log Information",
       				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       				    
       				   if (response == JOptionPane.NO_OPTION) {
       					  backToMain();
       				    } 
       				   	
       				   else if (response == JOptionPane.YES_OPTION) {
       					   backToMain();
       				   }
       					
       				}
       			 }
       		 }
        	 
       		 if(flag == true && tries < 1)
       		 {
       			JOptionPane.showMessageDialog(null, "Your login information is invalid");
       			tries++;
       		 }
       		 else if(flag == true && tries == 1) {
       			tries++;
       		 }
       		 
       		 if(tries == 2)
       		 {
       			JOptionPane.showMessageDialog(null, "You have exceeded the maximum amount of tries.");
       			backToMain();
       		 }
    	   				 
        }
    }

    // Listener for the Cancel button
    private class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            backToMain();
        }
    }
    
    // Goes back to the main menu frame
    public void backToMain()
    {
        (SwingUtilities.getWindowAncestor(this)).dispose(); 
    }    
    
    
    // Creates the new MakeAccountPanel
    public LoginPanel()
    {
        this.setLayout(new GridBagLayout());
        
        //Sets the buttons
        usernameL = new JLabel("Username: ");
        username = new JTextField(15);
        passwordL = new JLabel("Password: ");
        password = new JTextField(15);
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        
        usernameL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        passwordL.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        submitButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
      
        usernameL.setForeground(Color.blue);
        passwordL.setForeground(Color.blue);
        submitButton.setForeground(Color.blue);
        cancelButton.setForeground(Color.blue);
        
        submitButton.addActionListener(new SubmitListener());
        cancelButton.addActionListener(new CancelListener());
                
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(usernameL, gc);
        gc.gridx = 1;
        this.add(username, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(passwordL, gc);
        gc.gridx = 1;
        this.add(password, gc);
        gc.gridy = 2;
        this.add(submitButton, gc);
        gc.gridy = 3;
        this.add(cancelButton, gc);
        
    }
}

class CancelReservationFrame extends JFrame 
{
    public CancelReservationFrame(String username)
    {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new CancelReservationPanel(username);
        this.add(panel);        
        this.setSize(600, 400);
    }    
}


class CancelReservationPanel extends JPanel 
{    
    private JButton submitButton,
                    cancelButton;
  
    private JLabel userReservations;
    
	JList displayUserReservations;
	JLabel userReservationLabel;
	
    private int tries = 0;
    boolean flag = true;
    private String user;

    
    // Listener for the Submit button
    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        	
        	String finalMsg = "Are you sure you would like to remove this Reservation #" 
         + (String)displayUserReservations.getSelectedValue()  +	"?";
        	
        	  int response = JOptionPane.showConfirmDialog(null, finalMsg
        			  , "Confirm Rental Reservation Cancellation",
				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    
				   if (response == JOptionPane.NO_OPTION) {
					   backToMain();
				    } 
				   	
				   else if (response == JOptionPane.YES_OPTION) {
				    	 
				    	for(int i = 0; i < TransactionLog.login.size(); i++)
				    	 {
				    		 if(displayUserReservations.getSelectedValue()
				    			.equals(Integer.toString(TransactionLog.login.get(i).getReservationRecord())))
				    		 {
				    			 TransactionLog.login.add(new TransactionLog("cancelation", user, 
				    					 TransactionLog.login.get(i).getPickUpDate(),  TransactionLog.login.get(i).getReturnDate(),
				    					 TransactionLog.login.get(i).getCarType(), 0.0, new Date()));
				    			 TransactionLog.login.remove(i);
							    JOptionPane.showMessageDialog(null, "Your reservation was successfully deleted!.");
				    			 backToMain();
				    			 return;
				    		 }
				    		 
				    	 }
				    	
				    } 
				   
				   else if (response == JOptionPane.CLOSED_OPTION) {
					   backToMain();
				    }
        	
        }
    }

    // Listener for the Cancel button
    private class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            backToMain();
        }
    }
    
    // Goes back to the main menu frame
    public void backToMain()
    {
        (SwingUtilities.getWindowAncestor(this)).dispose(); 
    }    
    
    
    // Creates the new MakeAccountPanel
    public CancelReservationPanel(String user)
    {
        this.setLayout(new GridBagLayout());
        this.user = user;
    	String [] userReservations = new String[15]; 

        
        int rail = 0;
        
        //Generating a JScrollPane of number of user reservations
        
     	 for(int i = 0; i < TransactionLog.login.size(); i++)
   		 {
   			 if(user.equals(TransactionLog.login.get(i).getUsername()) && TransactionLog.login.get(i).getReservationRecord() != 0
   				&& TransactionLog.login.get(i).getTransactionType().equals("reservation"))
   			 { 
   				userReservations[rail] = Integer.toString(TransactionLog.login.get(i).getReservationRecord());
   				rail++;
   			 }
   		 }
     	 
     	 displayUserReservations = new JList(userReservations);
     	 userReservationLabel = new JLabel(user + " Reservations");
     	 displayUserReservations.setFixedCellWidth(40);
     	 displayUserReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         displayUserReservations.setVisibleRowCount(4);
         JScrollPane userReservationList = new JScrollPane(displayUserReservations);
     	 
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        
        submitButton.addActionListener(new SubmitListener());
        cancelButton.addActionListener(new CancelListener());
         
        add(userReservationLabel, getConstraints(1,0,1,1,GridBagConstraints.NORTH));
        add(userReservationList, getConstraints(2,0,2,1, GridBagConstraints.NORTH));
        add(submitButton, getConstraints(2,1,1,1,GridBagConstraints.NORTH));
        add(cancelButton, getConstraints(2,2,1,1, GridBagConstraints.NORTH));
        
    }
    
    private GridBagConstraints getConstraints(int gridx, int gridy,
            int gridwidth, int gridheight, 
            int anchor)
	{
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(5, 5, 5, 5);
			c.ipadx = 0;
			c.ipady = 0;
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = gridwidth;
			c.gridheight = gridheight;
			c.anchor = anchor;
			return c;
	}
}
