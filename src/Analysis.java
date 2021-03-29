import java.util.HashMap;
import java.util.Map;

public class Analysis {
	/** Initialize the dictionary */
	public static HashMap<Integer, Integer> start() {
		HashMap<Integer, Integer> numMatches = new HashMap<Integer, Integer>();
		for (int i = 0; i < 6; ++i) {
			numMatches.put(i, 0); // No Powerballs
			numMatches.put(i + 10, 0); // Powerballs
		}	
		return numMatches;
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
		for (char c : message.toCharArray()) {
			dashes += "-";
		}
		System.out.println("\n" + message + "\n" + dashes);
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
		
		printDashes("The theoretical probability");
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			System.out.println("The chance of " + entry.getKey() + " is " + entry.getValue() / sampleSpace);
		}		
		
		printDashes("The difference between actual and theoretical probability");
		for (int entry : theory.keySet()) {
			System.out.println("The difference in probability for " + entry + " is "
					+ ((theory.get(entry) / sampleSpace) - numMatches.get(entry) / NUM_ITERATIONS));
		}
		
		// Adding the number of matches and ensure that it is equal to NUM_ITERATIONS
		int totalTickets = 0;
		for (Map.Entry<Integer, Integer> entry : numMatches.entrySet()) {
			totalTickets += entry.getValue();
		}
		printDashes("The total number of tickets is " + totalTickets);

		
		// Adding the theoretical probabilities and ensure that is equal to 1
		double sumProb = 0;
		for (Map.Entry<Integer, Integer> entry : theory.entrySet()) {
			sumProb += entry.getValue();
		}		
		printDashes("The sum of theoretical probabilities is " + sumProb / sampleSpace);
	}
}
