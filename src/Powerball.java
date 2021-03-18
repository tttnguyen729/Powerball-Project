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
		// The number of Powerball tickets to be generated.
		final double NUM_ITERATIONS = Math.pow(10, 6);
		
		// Generate the winning combination.
		Powerball winningCombo = new Powerball();
		System.out.println("The winning combo is " + winningCombo.getPowerballs());
		
		// The hash map will keep track of the number of matches.
		HashMap<Integer, Integer> numMatches = start();
		int numMatch;
		
		// Generate NUM_ITERATIONS Powerball tickets and count the number of matches.
		for (int i = 0; i < (int)NUM_ITERATIONS; ++i)
		{
			// Generate the random ticket.
			Powerball random = new Powerball();
			
			// Get the number of matches
			numMatch = winningCombo.getNumMatches(random);
		
			// Increment the corresponding combination
			numMatches.put(numMatch, numMatches.get(numMatch) + 1);
		}
		
		printDashes("Printing the number of matches");   
		for (Map.Entry<Integer, Integer> entry : numMatches.entrySet()) {
			System.out.println("The number of matches for " + entry.getKey() + " is " + entry.getValue());
		}
		
		printDashes("Printing the actual probability");
		for (Map.Entry<Integer, Integer> entry : numMatches.entrySet()) {
			System.out.println("The probability for " + entry.getKey() + " is " + entry.getValue() / NUM_ITERATIONS);
		}
		
		// Theoretical probability 
		HashMap<Integer, Integer> theory = theoryProb();
		
		// The possible combinations of each matching configuration.
		final double sampleSpace = 292201338;
		
		/**
		 * Printing the theoretical probability
		 * https://stackoverflow.com/questions/5920135/printing-hashmap-in-java
		 */
		printDashes("The theoretical probability");
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			System.out.println("The chance of " + entry.getKey() + " is " + entry.getValue() / sampleSpace);
		}		
		
		printDashes("The difference between actual and theoretical probability");
		for (int entry : theory.keySet()) {
			System.out.println("The difference in probability for " + entry + " is "
					+ ((theory.get(entry) / sampleSpace) - numMatches.get(entry) / NUM_ITERATIONS));
		}
		
		// Some sanity checks
		// Adding the number of matches and ensure that it is equal to NUM_ITERATIONS
		int totalTickets = 0;
		for (Map.Entry<Integer, Integer> entry : numMatches.entrySet()) {
			totalTickets += entry.getValue();
		}
		printDashes("The total number of tickets is " + totalTickets);

		
		// Adding the theoretical probabilities and ensure that is equal to 1
		double sumProb = 0;
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			sumProb += entry.getValue() / sampleSpace;
		}		
		printDashes("The total probability is " + sumProb);
	}
	
	/**
	 * Initialize the dictionary
	 * @return
	 */
	public static HashMap<Integer, Integer> start() {
		HashMap<Integer, Integer> numMatches = new HashMap<Integer, Integer>();
		// No Powerball
		numMatches.put(0, 0);
		numMatches.put(1, 0);
		numMatches.put(2, 0);
		numMatches.put(3, 0);
		numMatches.put(4, 0);
		numMatches.put(5, 0);
		
		// With Powerball
		// The ones digit indicates number of regular balls
		numMatches.put(10, 0);
		numMatches.put(11, 0);
		numMatches.put(12, 0);
		numMatches.put(13, 0);
		numMatches.put(14, 0);
		numMatches.put(15, 0);
		
		return numMatches;
	}
	
	/**
	 * Return the theoretical combinations. 
	 * @return
	 */
	public static HashMap<Integer, Integer> theoryProb() {
		HashMap<Integer, Integer> theories = new HashMap<Integer, Integer>();
	
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
		
		return theories;
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
	
	/**
	 * Print the message with dashes
	 * @param message
	 */
	public static void printDashes(String message) {
		String dashes = "";
		for (char c : message.toCharArray()) {
			dashes += "-";
		}
		System.out.println("\n" + message + "\n" + dashes);
	}
}

