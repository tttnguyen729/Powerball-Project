import java.util.HashMap;
import java.util.Map;

public class Probability {
	// The number of Powerball tickets to be generated.
	public final static double NUM_TICKETS = Math.pow(10, 6);
	
	// The possible combinations of each matching configuration.
	public final static double SAMPLE_SPACE = 292201338;
	
	/** Initialize the dictionary */
	public static HashMap<Integer, Integer> start() {
		HashMap<Integer, Integer> Matches = new HashMap<Integer, Integer>();
		for (int i = 0; i < 6; ++i) {
			Matches.put(i, 0); // No Powerballs
			Matches.put(i + 10, 0); // Powerballs
		}	
		return Matches;
	}

	/** Return the theoretical combinations. */
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
	 * Print the message with dashes
	 * @param message
	 */
	public static void printDashes(String message) {
		String dashes = "";
		for (int i = 0; i < message.length(); ++i) {
			dashes += "-";
		}
		System.out.println("\n" + message + "\n" + dashes);
	}

	public static void main(String[] args)
	{	
		// Generate the winning combination.
		Powerball winningCombo = new Powerball();
		System.out.println("The winning combo is " + winningCombo.getPowerballs());
		
		// The hash map will keep track of the number of matches.
		HashMap<Integer, Integer> matches = start();
		
		// Generate _ITERATIONS Powerball tickets and count the number of matches.
		for (int i = 0; i < (int)NUM_TICKETS; ++i)
		{
			// Generate the random ticket.
			Powerball random = new Powerball();
			
			// Get the number of matches
			int match= winningCombo.getNumMatches(random);
		
			// Increment the corresponding combination
			matches.put(match, matches.get(match) + 1);
		}
		
		printDashes("Printing the number of matches");   
		for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
			System.out.println("The number of matches for " + entry.getKey() + " is " + entry.getValue());
		}
		
		printDashes("Printing the actual probability");
		for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
			System.out.println("The probability for " + entry.getKey() + " is " + entry.getValue() / NUM_TICKETS);
		}
		
		// Theoretical probability 
		HashMap<Integer, Integer> theory = theoryProb();
		
		printDashes("The theoretical probability");
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			System.out.println("The chance of " + entry.getKey() + " is " + entry.getValue() / SAMPLE_SPACE);
		}		
		
		printDashes("The difference between actual and theoretical probability");
		for (int entry : theory.keySet()) {
			System.out.println("The difference in probability for " + entry + " is "
					+ ((theory.get(entry) / SAMPLE_SPACE) - matches.get(entry) / NUM_TICKETS));
		}
		
		// Adding the number of matches and ensure that it is equal to _ITERATIONS
		int totalTickets = 0;
		for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
			totalTickets += entry.getValue();
		}
		printDashes("The total number of tickets is " + totalTickets);

		
		// Adding the theoretical probabilities and ensure that is equal to 1
		double sumProb = 0;
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			sumProb += entry.getValue();
		}		
		printDashes("The sum of theoretical probabilities is " + sumProb / SAMPLE_SPACE);
	}
}
