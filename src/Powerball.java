import java.util.ArrayList;
import java.util.Collections;

/**
 * A Powerball simulator
 * @author Thomas Nguyen
 * @version 1.3
 * Rules adapted from: https://www.powerball.net/rules
 */
public class Powerball {
	private static final int NUM_POWER_BALLS = 1;
	private static final int NUM_REGULAR_BALLS = 5;
	private static final int NUM_BALLS = NUM_REGULAR_BALLS + NUM_POWER_BALLS;
	
	private static final int IDX_POWERBALL = NUM_BALLS - 1;
	private static final int MAX_REGULAR_VALUE = 69;
	private static final int MAX_POWERBALL_VALUE = 26;

	private ArrayList<Integer> powerballs;
	
	/** Generates a random Powerball ticket. */
	public Powerball() {
		powerballs = new ArrayList<Integer>(0); // Initialize the array.
		while (powerballs.size() < NUM_REGULAR_BALLS) {	
			int ball = randomNumberGenerator(MAX_REGULAR_VALUE);
			if (!powerballs.contains(ball)) {
				powerballs.add(ball); // Ensure balls are unique.
			}
		}
		
		Collections.sort(powerballs); // Sort the regular balls.

		powerballs.add(randomNumberGenerator(MAX_POWERBALL_VALUE)); // Generate the Powerball.
	}

	/** Generates a random integer from 1 to max. */
	private static int randomNumberGenerator(int max) {
		return (int)Math.ceil(Math.random() * max);
	}

	/** Return the number of matches of a Powerball with another Powerball. */
	public int getNumMatches(Powerball compared) {
		int counter = 0;
		int idxBall = 0;
		int idxCompared = 0;
		
		// Check if Powerballs match.
		if (this.getBall(IDX_POWERBALL) == compared.getBall(IDX_POWERBALL)) {
			counter += 10;
		}
		
		// Count the regular matches.
		while (idxBall < NUM_REGULAR_BALLS && idxCompared < NUM_REGULAR_BALLS) {
			if (this.getBall(idxBall) == compared.getBall(idxCompared)) {
				++counter;
				++idxBall;
				++idxCompared;
			}
			else if (this.getBall(idxBall) > compared.getBall(idxCompared)) {
				++idxCompared;
			}
			else {
				++idxBall;
			}
		}
		return counter;
	}
	
	/** Return the ArrayList of Powerballs. */
	public ArrayList<Integer> getPowerballs() {
		return powerballs;
	}
	
	/** Return the ball at the given index. */
	public int getBall(int idx) {
		return powerballs.get(idx);
	}
}

