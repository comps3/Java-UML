/*
 * Title: Histogram.java
 * Abstract: This program counts the occurance of unique numbers from 
 * user input.
 */

import java.util.Scanner;

public class Histogram {

	public static void main(String[] args) {
		
		// Store user input numbers
		int [] userInput;
		// Stores the number of times a number occurs 
		int [] uniqueNumCount;
		// Range of numbers supported from 0 to 9
		int range = 10;
		// Used in drawing the vertical and horizontal axis of the histograms
		int highest = 0;
	
		// User option
		char userSelect = 'Y';
		
		while (userSelect == 'Y' || userSelect == 'y') {
			
			highest = 0;
		
		// User inputs numbers
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many input values [max: 30]?");
		
		int size = keyboard.nextInt();
		
			if (size > 30 || size <= 0)
			{
				System.out.println("Sorry, that is an invalid size.");
				return;
			}
		
		// Initializing arrays
		userInput = new int[size];
		uniqueNumCount = new int[range];
		
		System.out.println("Enter " + size + " numbers.");		
		
		// Reading in user's numbers
		for(int i=0; i < size; i++)
		{
			userInput[i] = keyboard.nextInt();
		}
		
		// Counts the number of occurances then stores into the array
		for (int i=0; i < range; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (userInput[j] == i)
				{
					uniqueNumCount[i] += 1;
				}	
			}

		}
		
		// Simple print out
		System.out.println("Number 		Count ");
		
		for(int i=0; i < range; i++)
		{
			if (uniqueNumCount[i] == 0)
			{

			}
			else 
			{
			System.out.println(i + " 		" + uniqueNumCount[i]);	
			}
		}
		
		System.out.println();
		
		
		System.out.println("======== Horizontal Bars ========");
		
		// Finds the integer with the highest number of occurrences
		for(int i=0; i < range; i++)
		{
			if(uniqueNumCount[i] > 0 && uniqueNumCount[i] > highest)
			{
				highest = uniqueNumCount[i];
			}
		}
		
		System.out.print("| No| ");
		// Prints the range of the horizontal graph
		for(int i=1; i < highest+1; i++)
		{
			System.out.print(i + " ");
		}
		
		System.out.println();
		System.out.println("=================================");
		
		for(int i=0; i < range; i++)
		{
				if (uniqueNumCount[i] != 0) {
				// Prints out the unique integers the users entered  
				System.out.print("| " + i + " | ");
				}
				
				for (int j=0; j < uniqueNumCount[i]; j++)
				{
					System.out.print("* ");
					
					if (j + 1 == uniqueNumCount[i])
					{
						System.out.println();
					}
				}
				
	
		}
		
		System.out.println("=================================");
		System.out.println();
		System.out.println("======== Vertical Bars ========");
		

			for(int j=highest; j > 0; j--)
			{
				// Prints the range of the horizontal graph
				System.out.print("|  " + j + " | ");
				
				for (int i = 0; i < range; i++)
				{
					if(uniqueNumCount[i] >= j)
					{
						System.out.print(" * ");
					}
					
					else if (uniqueNumCount[i] != 0)
					{
						System.out.print("   ");
					}
					
				}
				
				System.out.println();
			}
		
		System.out.println("=================================");		
		
		System.out.print("| No |");
		for(int i=0; i < range; i++)
		{
			if (uniqueNumCount[i] != 0) {
				// Prints out the unique integers the users entered  
				System.out.print("  " + i);
			}
		}
		
		System.out.println();
		System.out.println("=================================");
		System.out.println();
		
		System.out.print("Continue (Y/N): ");
		userSelect = keyboard.next().charAt(0);
		
		}
		
		System.out.println("Bye.");
		
	}
  
}
