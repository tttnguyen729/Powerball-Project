import java.util.ArrayList;
import java.util.Collections;

/**
 * A power ball simulator
 * @author Thomas Nguyen
 * @version 1.2
 * 
 * To play Powerball, you must select five numbers from a pool 
 * between 1 and 69 and one Powerball between 1 and 26. 
 * 
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
	
	/**
	 * Return the number of matches of a Powerball
	 * with another Powerball.
	 * 
	 * If the Powerball matches, it will count 
	 * as ten matches
	 * @param compared The Powerball to be compared with
	 * @return The number of matches
	 */
	public int getNumMatches(Powerball compared)
	{
		int counter = 0;
		
		// Checking if the Powerballs match
		if (this.getBall(IDX_POWERBALL) == compared.getBall(IDX_POWERBALL))
		{
			counter += 10;
		}
		
		// Counting the regular matches
		int idxBall = 0;
		int idxCompared = 0;
		
		while (idxBall < NUM_REGULAR_BALLS && idxCompared < NUM_REGULAR_BALLS)
		{
			if (this.getBall(idxBall) == compared.getBall(idxCompared))
			{
				++counter;
				++idxBall;
				++idxCompared;
			}
			else if (this.getBall(idxBall) > compared.getBall(idxCompared))
			{
				++idxCompared;
			}
			else
			{
				++idxBall;
			}
		}
		
		return counter;
	}
}

