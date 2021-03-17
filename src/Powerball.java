import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

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
		int ball;

		// Generating the regular balls		
		while (powerballs.size() < NUM_REGULAR_BALLS)
		{	
			ball = randomNumberGenerator(MAX_REGULAR_VALUE);
			if (!powerballs.contains(ball)) {
				powerballs.add(ball);
			}
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
		
		System.out.println("The winning combo is " + winningCombo.getPowerballs());
		
		final double NUM_ITERATIONS = Math.pow(10, 3);
		int[] numMatches = new int[16];
		for (int i = 0; i < (int)NUM_ITERATIONS; ++i)
		{
			Powerball random = new Powerball();
//			System.out.println(random.getPowerballs() + " " + 
//			winningCombo.getNumMatches(random));	
			++numMatches[winningCombo.getNumMatches(random)];
		}
		
		System.out.println(Arrays.toString(numMatches));
		System.out.println();

		printProbability(numMatches, NUM_ITERATIONS);
		
		
		System.out.println("The difference between the actual and theoretical probabilities: ");
		System.out.println(diffActualTheory(numMatches, 0, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 1, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 2, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 3, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 4, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 5, NUM_ITERATIONS));
		
		System.out.println(diffActualTheory(numMatches, 10, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 11, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 12, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 13, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 14, NUM_ITERATIONS));
		System.out.println(diffActualTheory(numMatches, 15, NUM_ITERATIONS));
	}
	
	public static void printProbability(int[] numMatches, double NUM_ITERATIONS) {
		System.out.println("The chance of getting no balls is " + numMatches[0] / NUM_ITERATIONS);
		System.out.println("The chance of getting one ball is " + numMatches[1] / NUM_ITERATIONS);
		System.out.println("The chance of getting two balls is " + numMatches[2] / NUM_ITERATIONS);
		System.out.println("The chance of getting three balls is " + numMatches[3] / NUM_ITERATIONS);
		System.out.println("The chance of getting four balls is " + numMatches[4] / NUM_ITERATIONS);
		System.out.println("The chance of getting five balls is " + numMatches[5] / NUM_ITERATIONS);

		System.out.println();
		System.out.println("The chance of getting only the Powerball is " + numMatches[10] / NUM_ITERATIONS);
		System.out.println("The chance of getting one ball and the Powerball is " + numMatches[11] / NUM_ITERATIONS);
		System.out.println("The chance of getting two balls and the Powerball is " + numMatches[12] / NUM_ITERATIONS);
		System.out.println("The chance of getting three balls and the Powerball is " + numMatches[13] / NUM_ITERATIONS);
		System.out.println("The chance of getting four balls and the Powerball is " + numMatches[14] / NUM_ITERATIONS);
		System.out.println("The chance of getting five balls and the Powerball is " + numMatches[15] / NUM_ITERATIONS);
		System.out.println();
	}
	
	public static double diffActualTheory(int[] numMatches, int index, double NUM_ITERATIONS) {
		HashMap<Integer, Integer> theories = new HashMap<Integer, Integer>();
		
		// The possible combinations of each matching configuration.
		final double sampleSpace = 292201338;

		// No Powerball
		theories.put(0, 190612800);
		theories.put(1, 79422000);
		theories.put(2, 10416000);
		theories.put(3, 504000);
		theories.put(4, 8000);
		theories.put(5, 25);
		
		// With Powerball
		// The ones digit indicates number of regular balls
		theories.put(10, 7624512);
		theories.put(11, 3176880);
		theories.put(12, 416640);
		theories.put(13, 20160);
		theories.put(14, 320);
		theories.put(15, 1);
		
		/**
		 * https://stackoverflow.com/questions/5920135/printing-hashmap-in-java
		 */
		for (Map.Entry<Integer, Integer> entry : theories.entrySet()) {
			System.out.println("The chance of " + entry.getKey() + " is " + entry.getValue() / sampleSpace);
		}


		
		return (numMatches[index] / NUM_ITERATIONS) - (theories.get(index) / sampleSpace);
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

