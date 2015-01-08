/* Title: Player.java
 * Abstract: The abstract player class allows other
 * classes to be extended from it.  The generate roshambo
 * function is listed in this class to allow the getter
 * function to access the function.
 * Author: Brian Huynh
 * ID: 7878
 * Date: 10/31/2014
 */
public abstract class Player {

	private String name;
	private int roshambo;
	
	public abstract int generateRoshambo();

	public String getName() {
		return new String(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoshambo() {
		return new Integer(generateRoshambo());
	}

	public void setRoshambo(int roshambo) {
		if(roshambo < 0 || roshambo > 2) {
			System.out.println("Invalid choice!");
			return;
		}
		this.roshambo = roshambo;
	}
	
	
	
	
	
}
