/* Title: RoshamboApp.java
 * Abstract: This driver class runs the classic 
 * roshambo game. It allows a user to insert their name
 * and select whether they would like to play with Bart
 * or Lisa.
 * Author: Brian Huynh
 * ID: 7878
 * Date: 10/31/2014
 */

import java.util.Scanner;

public class RoshamboApp {

	public static void main(String[] args) {
		
		String userName;
		char[] options = {'R', 'P', 'S'};
		String [] longOptions = {"rock", "paper", "scissors"};
		char opponent;
		char play;
		char gameSwitch = 'Y';

		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the game of Roshambo");
		System.out.println("Enter your name: ");
		userName = input.nextLine();
		System.out.println("Would you like to play Bart or Lisa? (B/L): ");
		opponent = input.next().charAt(0);
		
		// Checks if the user entered the proper selection
		while(opponent != 'B' && opponent != 'L')
		{
			System.out.println("Improper selection.");
			System.out.println("Would you like to play Bart or Lisa? (B/L): ");
			opponent = input.next().charAt(0);
			
		}
		
		while(gameSwitch == 'Y')
		{
			System.out.println("Rock, paper, or scissors? (R/P/S): ");
			play = input.next().charAt(0);
			
			// Checks if the user entered the proper selection
			while(play != 'R' && play != 'P' && play != 'S')
			{
				System.out.println("Improper selection.");
				System.out.println("Rock, paper, or scissors? (R/P/S): ");
				play = input.next().charAt(0);
			}
			
			if(opponent == 'B') {
				
				Bart player = new Bart();
				
				int random = player.getRoshambo();
				System.out.print(userName + ": ");
				
				if(play == options[0])
				{
					System.out.println(longOptions[0]);
					System.out.println("Bart: " + longOptions[random]);
					System.out.println("Draw!");
				}
				else if(play == options[1])
				{
					System.out.println(longOptions[1]);
					System.out.println("Bart: " + longOptions[random]);
					System.out.println(userName +" wins!");
					
				}
				else if(play == options[2])
				{
					System.out.println(longOptions[2]);
					System.out.println("Bart: " + longOptions[random]);
					System.out.println("Bart wins!");
				}	
			}
			
			
			else if (opponent == 'L') {
				
				Lisa player1 = new Lisa();
				int random = player1.getRoshambo();
				System.out.print(userName + ": ");
				
				if(play == 'R')
				{
					System.out.println(longOptions[0]);
					System.out.println("Lisa: " +  longOptions[random]);
					
					if(random == 0)
					{
						System.out.println("Draw!");
					}
					else if(random == 1)
					{
						System.out.println("Lisa wins!");
					}
					else if(random == 2) 
					{
						System.out.println(userName + " wins!");
					}
					
				}
				else if(play == 'P')
				{
					System.out.println(longOptions[1]);
					System.out.println("Lisa: " +  longOptions[random]);
					
					if(random == 0)
					{
						System.out.println(userName + " wins!");
					}
					else if(random == 1)
					{
						System.out.println("Draw!");
					}
					else if(random == 2) 
					{
						System.out.println("Lisa wins!");
					}
					
				}
				else if(play == 'S')
				{
					System.out.println(longOptions[2]);
					System.out.println("Lisa: " +  longOptions[random]);
					
					if(random == 0)
					{
						System.out.println("Lisa wins!");
					}
					else if(random == 1)
					{
						System.out.println(userName + " wins!");
					}
					else if(random == 2) 
					{
						System.out.println("Draw!");
					}
				}
				
			
			}
			
			System.out.println("Play again? (Y/N): ");
			gameSwitch = input.next().charAt(0);
			System.out.println();
			
			while(gameSwitch != 'Y' && gameSwitch != 'N')
			{
				System.out.println("Improper selection.");
				System.out.println("Play again? (Y/N): ");
				gameSwitch = input.next().charAt(0);
				System.out.println();
			}
			
		}
		
	}

}
