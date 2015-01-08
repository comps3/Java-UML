/*
 * Title: Calculator.java
 * Abstract: The class calculator contains the implementation 
 * all of the necessary elements of a calculator. It also implements
 * calculator functionality by allowing users to interact with the
 * buttons to get a result.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends JFrame implements ActionListener
	{
		private JMenuBar bar;
		private JMenuItem help;
		private JButton space, clear;
		private JButton zero, one, two, three, four, five, six, seven, eight, nine, decimal;
		private JButton divide, multiply, minus, addition, equals;
		private JTextArea total = new JTextArea(8,40);
		private JTextArea scratch = new JTextArea(3,40);
		private int operation = -1;
		private	double scratchLine;
		private double newStuff;
		private boolean wrapper = false;
		
		public static void main(String[] args)
		{
			Calculator gui = new Calculator();
			gui.setVisible(true);
		}
		
		public Calculator()
		{
			// Title of window
			super("Calculator");
		    setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.pack();
	        
	        bar = new JMenuBar();
	        help = new JMenuItem("Help");
	        help.addActionListener(this);
	        bar.add(help);
	        setJMenuBar(bar);
	        
			setLayout(new GridBagLayout());
			setSize(500, 500);
			
	        // Add text areas to the GUI
	        add(scratch, getConstraints(0,0,5,1, GridBagConstraints.NORTH, 0));
	        add(total, getConstraints(0,1,5,1, GridBagConstraints.NORTH, 0));
	        
	        scratch.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        total.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        
	        // Text areas should only be used for display
	        scratch.setEditable(false);
	        total.setEditable(false);
	        
	        scratch.setVisible(true);
	        total.setVisible(true);
	        
	        total.setText("0");
			
			// Set up GUI layout and inserting buttons
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(0,2,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(1,2,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(2,2,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(3,2,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(4,2,1,1, GridBagConstraints.NORTH, 0));
	        
	        clear = new JButton("C");
	        clear.addActionListener(this);
	        add(clear, getConstraints(2,3,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(0,3,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        add(space, getConstraints(1,3,1,1, GridBagConstraints.NORTH, 0));
	        space.setEnabled(false);
	        space = new JButton(" ");
	        add(space, getConstraints(3,3,1,1, GridBagConstraints.NORTH, 0));
	        space.setEnabled(false);
	        space = new JButton(" ");
	        add(space, getConstraints(4,3,1,1, GridBagConstraints.NORTH, 0));
	        space.setEnabled(false);
	        
	        seven = new JButton(" 7 ");
	        seven.addActionListener(this);
	        add(seven, getConstraints(0,4,1,1, GridBagConstraints.NORTH, 0));
	        eight = new JButton(" 8 ");
	        eight.addActionListener(this);
	        add(eight, getConstraints(1,4,1,1, GridBagConstraints.NORTH, 0));
	        nine = new JButton(" 9 ");
	        nine.addActionListener(this);
	        add(nine, getConstraints(2,4,1,1, GridBagConstraints.NORTH, 0));
	        divide = new JButton(" / ");
	        divide.addActionListener(this);
	        add(divide, getConstraints(3,4,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(4,4,1,1, GridBagConstraints.NORTH, 0));
	        
	        four = new JButton(" 4 ");
	        four.addActionListener(this);
	        add(four, getConstraints(0,5,1,1, GridBagConstraints.NORTH, 0));
	        five = new JButton(" 5 ");
	        five.addActionListener(this);
	        add(five, getConstraints(1,5,1,1, GridBagConstraints.NORTH, 0));
	        six = new JButton(" 6 ");
	        six.addActionListener(this);
	        add(six, getConstraints(2,5,1,1, GridBagConstraints.NORTH, 0));
	        multiply = new JButton(" * ");
	        multiply.addActionListener(this);
	        add(multiply, getConstraints(3,5,1,1, GridBagConstraints.NORTH, 0));
	        space = new JButton(" ");
	        space.setEnabled(false);
	        add(space, getConstraints(4,5,1,1, GridBagConstraints.NORTH, 0));
	        
	        one = new JButton(" 1 ");
	        one.addActionListener(this);
	        add(one, getConstraints(0,6,1,1, GridBagConstraints.NORTH, 0));
	        two = new JButton(" 2 ");
	        two.addActionListener(this);
	        add(two, getConstraints(1,6,1,1, GridBagConstraints.NORTH, 0));
	        three = new JButton(" 3 ");
	        three.addActionListener(this);
	        add(three, getConstraints(2,6,1,1, GridBagConstraints.NORTH, 0));
	        minus = new JButton(" - ");
	        minus.addActionListener(this);
	        add(minus, getConstraints(3,6,1,1, GridBagConstraints.NORTH, 0));
	        
	        equals = new JButton(" = ");
	        equals.addActionListener(this);
	        add(equals, getConstraints(4,6,1,2, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL));
	        
	        zero = new JButton(" 0 ");
	        zero.addActionListener(this);
	        add(zero, getConstraints(0,7,2,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL));
	        decimal = new JButton(" . ");
	        decimal.addActionListener(this);
	        add(decimal, getConstraints(2,7,1,1, GridBagConstraints.NORTH, 0));
	        addition = new JButton(" + ");
	        addition.addActionListener(this);
	        add(addition, getConstraints(3,7,1,1, GridBagConstraints.NORTH, 0));
	        
	        
	        
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			
			// Adds a character to text fields as the user
			// press each button
			if(source == help)
			{
				JOptionPane.showMessageDialog(this, "This program allows users to calculate the sum, difference, mutiple or division of two numbers.  Developed By Brian Huynh");
			}
			if(source == clear)
			{
				wrapper = false;
				total.setText("0");
				scratch.setText("");
			}
			if(source == seven)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				
				scratch.append("7");
				total.append("7");
			}
			if(source == eight)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("8");
				total.append("8");
			}
			if(source == nine)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("9");
				total.append("9");
			}
			if(source == divide)
			{
				wrapper = false;
				// Converts the string to a double 
				scratchLine = Double.parseDouble(total.getText());
				scratch.append("/");
				total.setText("0");
				operation = 0;
			}
			if(source == four)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("4");
				total.append("4");
			}
			if(source == five)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("5");
				total.append("5");
			}
			if(source == six)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("6");
				total.append("6");
			}
			if(source == multiply)
			{
				wrapper = false;
				// Converts the string to a double 
				scratchLine = Double.parseDouble(total.getText());
				scratch.append("*");
				total.setText("");
				operation = 1;
			}
			if(source == one)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("1");
				total.append("1");
			}
			if(source == two)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("2");
				total.append("2");
			}
			if(source == three)
			{
				if(!wrapper) {
					total.setText("");
					wrapper = true;
				}
				scratch.append("3");
				total.append("3");
			}
			if(source == minus)
			{
				wrapper = false;
				// Converts the string to a double 
				scratchLine = Double.parseDouble(total.getText());
				scratch.append("-");
				total.setText("");
				operation = 2;
			}
			if(source == zero)
			{
				if(!wrapper) {
				
				}
				else {
				scratch.append("0");
				total.append("0");
				}
			}
			if(source == decimal)
			{
				wrapper = true;
				scratch.append("0.");
				total.append(".");
			}
			// When user clicks add, the bottom portion of text field 
			// clears
			if(source == addition)
			{
				wrapper = false;
				// Converts the string to a double 
				scratchLine = Double.parseDouble(total.getText());
				scratch.append("+");
				total.setText("");
				operation = 3;
			}
			if(source == equals)
			{
				newStuff = Double.parseDouble(total.getText());
				scratch.setText("");
				
				// Converts the string to a double and does the user specified 
				// operation
				if(operation == 0) {
					 total.setText(Double.toString(scratchLine / newStuff));
				}
				if(operation == 1) {
					 total.setText(Double.toString(scratchLine * newStuff));
				}
				if(operation == 2) {
					 total.setText(Double.toString(scratchLine - newStuff));
				}
				if(operation == 3) {
					 total.setText(Double.toString(scratchLine + newStuff));
				}

				}
				
			}
	
			
		private GridBagConstraints getConstraints(int gridx, int gridy,
                int gridwidth, int gridheight, 
                int anchor, int fill)
			{
				GridBagConstraints c = new GridBagConstraints();
				c.insets = new Insets(5,5,5,5);
				c.ipadx = 0;
				c.ipady = 0;
				c.gridx = gridx;
				c.gridy = gridy;
				c.gridwidth = gridwidth;
				c.gridheight = gridheight;
				c.anchor = anchor;
				c.fill = fill;
				return c;
			}
		
	}


