/*
 * Title: Hangman.java
 * Abstract: This program allows users to play a classic game
 * of Hangman. Users enters the directory of a text file which includes 
 * one word, the program will allow users to guess the word that was in
 * the file.  The user also has the option of asking for a hint but
 * only four hints will be provided.  
 */


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Hangman {
	
	public static boolean winCheck(char []users, String answer)
	{
		for(int i=0; i < answer.length(); i++)
		{
			if(users[i] != answer.charAt(i))
			{
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		System.out.println("--------- Welcome to Hangman ---------");
	
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter a file name: ");
		filePath = keyboard.next();
		
		File file = new File(filePath);
		String secretWord = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		char []usersPlay;
		char []incorrectGuesses;
		char []uniqueChars;
		char []alphabet = { 'A', 'B', 'C', 'D', 'E', 'F' , 'G', 'H', 'I', 'J', 'K',  'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		int guessesLeft = 4;
		int userChoice = 0;
		int incrementor = 0;
		int incrementorChars = 0;
		int sizeOfUnique = 0;
		char usersGuess;
		boolean found = false;
		
		try {
		     fis = new FileInputStream(file);

		      // Here BufferedInputStream is added for fast reading.
		      bis = new BufferedInputStream(fis);
		      dis = new DataInputStream(bis);

		      // dis.available() returns 0 if the file does not have more lines.
		      while (dis.available() != 0) {

		      // this statement reads the line from the file and print it to
		        // the console.
		        secretWord = dis.readLine();
		      }

		      // dispose all the resources after using them.
		      fis.close();
		      bis.close();
		      dis.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    } catch (NullPointerException e) {
		      e.printStackTrace();
		    }
		
			if (secretWord == null)
			{
				System.out.println("Unable to find a word in the text file.");
				return;
			}
			
			secretWord = secretWord.toUpperCase();
		
		// Initializes arrays
		usersPlay = new char[secretWord.length()];
		uniqueChars = new char[secretWord.length()];
		incorrectGuesses = new char[guessesLeft];
		
		// Filling up user play field with dashes
		for(int i=0; i < secretWord.length(); i++)
		{
			usersPlay[i] = '_';
		}
		
		// Finds the unique characters in secret word
		// using an array containing the entire alphabet
		for(int i= 0; i< secretWord.length(); i++) {
			
			for(int j = 0; j < 26; j++)
			{
				if (secretWord.charAt(i) == alphabet[j])
				{
					found = true;
					uniqueChars[incrementorChars] = alphabet[j];
					break;
				}
			}
			
			if (found == true) {
				incrementorChars++;
				found = false;
			}
			
		}
		
		sizeOfUnique = incrementorChars; 
		
		// Done borrowing incrementor, number has been reset
		incrementorChars = 0;
		found = false;
		
		while(guessesLeft != 0) 
		{
			found = false;
			
			System.out.print("So far, the word is: ");
			
			// Print out the user's play board
			for(int i=0; i < secretWord.length(); i++)
			{
				System.out.print(usersPlay[i] + " ");
			}
			
			System.out.println();
			
			System.out.println("You have " + guessesLeft + " incorrect guesses left. ");
			System.out.print("Enter either 1 for guessing or 2 for hint: ");
			
			userChoice = keyboard.nextInt();
			
			if (userChoice == 1) {
				
				System.out.print("Enter your guess: ");
				
				usersGuess = keyboard.next().charAt(0);
		
				usersGuess = Character.toUpperCase(usersGuess);
				
				// Check array if the guess has been made
				for(int i = 0; i < 4; i++) {
					if (usersGuess == incorrectGuesses[i])
					{
						System.out.println("Not valid input. You already guessed " + usersGuess + ".");
						guessesLeft--;
						found = true;
						break;
					}
					
				}
				
				if (found == true)
				{
					System.out.println();
					continue;
				}
				
				// Checks if the user has already correctly guessed a letter
				for(int i= 0; i < secretWord.length(); i++) 
				{
					if (usersGuess == usersPlay[i])
					{
						System.out.println("Not valid input. You already guessed " + usersGuess + ".");
						found = true;
						break;
					}
					
				}
				
				if (found == true)
				{
					System.out.println();
					continue;
				}
					// Searches secret word if user guess equals to it
					for(int i=0; i < secretWord.length(); i++)
					{
						if (secretWord.charAt(i) == usersGuess)
						{
							found = true;
							usersPlay[i] = usersGuess;
						}
					}
					
					if(found == true)
					{
						System.out.println("That's right! " + usersGuess + " is in the word. ");
						found = false;
						
						if (winCheck(usersPlay, secretWord) == true)
						{
							break;
						}
						
					}
					
					else {
						System.out.println("Sorry, " + usersGuess + " isn't in the word. ");
						incorrectGuesses[incrementor] = usersGuess;
						incrementor++;
						guessesLeft--;
								
					}
					
				System.out.println();
			}
			
			// User gets hints here
			else if (userChoice == 2) {

				// Removes characters from the array of unique characters
				// that user has already guessed
				for(int i= 0; i < sizeOfUnique; i++)
				{
					found = false;
					
					for(int j=0; j < secretWord.length(); j++)
					{
						
						if (uniqueChars[i] == usersPlay[j] && uniqueChars[i] != ' ')
						{
							uniqueChars[i] = ' ';
							found = true;
						}

					}
					
					if (found == false && uniqueChars[i] != ' ')
					{
						incrementorChars = i;
						break;
						
					}
					
				}
				
				System.out.println("OK! The hint is " + uniqueChars[incrementorChars]);
				
				for(int i=0; i < secretWord.length(); i++)
				{
					if (secretWord.charAt(i) == uniqueChars[incrementorChars])
					{
						usersPlay[i] = secretWord.charAt(i);
					}
					
				}
				
				guessesLeft--;
				System.out.println("But since you used the hint, you can guess " + guessesLeft + " more times.");
				incrementorChars++;
				
				if (winCheck(usersPlay, secretWord) == true)
				{
					break;
				}
				
			}
			
			
			else {
				System.out.println("Incorrect choice, Please enter either 1 for guessing or 2 for hint. ");
				continue;
			}
			
		}
		
		System.out.println();
		
		if (winCheck(usersPlay, secretWord) == true) {
			System.out.print("Congratulations! The word was ");
		}
		else {
			System.out.print("You failed. The word was ");
		}
		
		for(int i=0; i < secretWord.length(); i++)
		{
			System.out.print(secretWord.charAt(i) + " ");
		}
		
	}

}

