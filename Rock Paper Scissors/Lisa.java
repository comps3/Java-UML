/* Title: Lisa.java
 * Abstract: The 'Lisa' class defines the 
 * generateRoshambo function by returning 
 * one of the three possible game moves 
 * through a random number generator.
 * Author: Brian Huynh
 * ID: 7878
 * Date: 10/31/2014
 */

import java.util.Random;

public class Lisa extends Player {

	public int generateRoshambo() {
		Random number = new Random();
		int newNumber = number.nextInt(3);
		return newNumber;
	}

}
