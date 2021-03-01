import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

/**
 * A power ball simulator
 * @author Thomas Nguyen
 * @version 1.2
 * 
 * To play Powerball, you must select five numbers from a pool 
 * between 1 and 69 and one Powerball between 1 and 26. 
 * The Powerball you select can be the same as one of the five main numbers. 
 * Rules taken from: https://www.powerball.net/rules
 */
public class Powerball 
{
	private static final int NUM_POWER_BALLS = 1;
	private static final int NUM_REGULAR_BALLS = 5;
	private static final int NUM_BALLS = NUM_REGULAR_BALLS + NUM_POWER_BALLS;

	private static final int IDX_POWERBALL = NUM_BALLS - 1;
	private static final int MAX_REGULAR_VALUE= 69;
	private static final int MAX_POWERBALL_VALUE = 26;

	private ArrayList<Integer> powerballs;
	
	/**
	 * Generates a random Powerball.
	 */
	public Powerball()
	{
		powerballs = new ArrayList<Integer>(0);

		// Generating the regular balls		
		while (powerballs.size() < NUM_REGULAR_BALLS)
		{		
			powerballs.add(randomNumberGenerator(MAX_REGULAR_VALUE));
		}
		
		// Ordering the regular balls
		Collections.sort(powerballs);

		// Generating the Powerball
		powerballs.add(randomNumberGenerator(MAX_POWERBALL_VALUE));
	}
	
	/**
	 * Get the ArrayList representation of the Powerballs.
	 * @return The list of Powerballs
	 */
	public ArrayList<Integer> getPowerballs() {
		return powerballs;
	}
	
/**
 * Get the ball at the given index.
 * @param idx The index of the ball
 * @return The ball at the given index
 */
	public int getBall(int idx) {
		return powerballs.get(idx);
	}

	/**
	 * Generates a random integer from 1 to max.
	 * @param max The maximum value a number can take.
	 * @return The random number.
	 */
	public static int randomNumberGenerator(int max)
	{
		return (int)Math.ceil(Math.random() * max);
	}
	
	public static void main(String[] args)
	{	
		Powerball winningCombo = new Powerball();
		
		System.out.println(winningCombo.getPowerballs());
		
		for (int i = 0; i < 10; ++i)
		{
			Powerball random = new Powerball();
			System.out.println(random.getPowerballs());
		}
	}
}

