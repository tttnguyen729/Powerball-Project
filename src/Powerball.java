import java.util.ArrayList;
import java.util.Scanner;

/**
 * A power ball simulator
 * @author Thomas Nguyen
 * @version 1.0
 * 
 * To play Powerball, you must select five numbers from a pool between 1 and 69 and one Powerball between 1 and 26. 
 * The Powerball you select can be the same as one of the five main numbers. 
 * Rules taken from: https://www.powerball.net/rules
 */

public class Powerball 
{
	private static final int NUM_BALLS = 6;
	private static final int IDX_POWERBALL = NUM_BALLS - 1;
	private static final int MAX_REGULAR_VALUE= 69;
	private static final int MAX_POWERBALL_VALUE = 26;

	private ArrayList<Integer> powerballs;
	
	/**
	 * Constructor for an empty Powerball.
	 */
	public Powerball()
	{
		powerballs = new ArrayList<Integer>(NUM_BALLS);
	}
	
	/**
	 * Constructor for the winning Powerball.
	 * @param x
	 */
	public Powerball(boolean x)
	{
		powerballs = new ArrayList<Integer>(NUM_BALLS);

		// Generating the regular balls
		for (int i = 0; i < NUM_BALLS - 1; ++i)
		{
			powerballs.add(randomNumberGenerator(MAX_REGULAR_VALUE));
		}
		
		// Generating the Powerball
		powerballs.add(MAX_POWERBALL_VALUE);
	}
	
	/**
	 * @return the powerballs
	 */
	public ArrayList<Integer> getPowerballs() {
		return powerballs;
	}

	/**
	 * Generates a random integer from 1 to max.
	 * @param max The maximum value a number can take.
	 * @return the random integer.
	 */
	public static int randomNumberGenerator(int max)
	{
		return (int)Math.ceil(Math.random() * max);
	}
	
	public static void main(String[] args)
	{
		boolean WinningCombination = true;
		Powerball winningCombo = new Powerball(WinningCombination);
		System.out.println(winningCombo.getPowerballs());
	}
	
	// TODO: Work on a prompt asking a user to select 5 balls and a powerball
	// TODO: Compare their pick to the winning combo
	// TODO: Research criterion for prizes
	
	public static int[] prompt()
	{
		Powerball ticket = new Powerball();
		
		// Take in the user's choice of numbers
		Scanner scnr = new Scanner(System.in);
		
		// Prompt for regular ball
		System.out.println("Please enter a number between 1 and 69");
		
		while ()
		
		return ticket.getPowerballs();
	}

}

