import java.util.HashMap;
import java.util.Map;

public class ProfitLoss extends Probability {
	private static final double PRICE_PER_TICKET = 2.00;
	
	// The number of Powerball tickets to be generated.
	final static double NUM_TICKETS = Math.pow(10, 6);
	
	/** Return the prize for each match combination. */
	public static HashMap<Integer, Integer> genPrizes() {
		HashMap<Integer, Integer> prizes = new HashMap<Integer, Integer>();
	
		// No Powerball
		prizes.put(0, 0);
		prizes.put(1, 0);
		prizes.put(2, 0);
		prizes.put(3, 7);
		prizes.put(4, 100);
		prizes.put(5, (int)(Math.pow(10, 6)));
		
		// With Powerball
		prizes.put(10, 4);
		prizes.put(11, 4);
		prizes.put(12, 7);
		prizes.put(13, 100);
		prizes.put(14, 50000);
		prizes.put(15, (int)(2 * Math.pow(10, 6))); // can grow arbitrarily large
		
		return prizes;
	}
	
	public static void main(String[] args) {
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
		
		// Prizes dictionary
		HashMap<Integer, Integer> prizes = genPrizes();
		
		printDashes("Calculating loss (payment of prizes to customer)");   
		int loss = 0;
		for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
			System.out.println("For " + entry.getKey() + " the prize is " + prizes.get(entry.getKey()) + " and the number of matches is " + entry.getValue());
			loss += prizes.get(entry.getKey()) * entry.getValue();
		}
		printDashes("The total loss is " + loss);		
		printDashes("Revenue from tickets is " + NUM_TICKETS * PRICE_PER_TICKET);
		printDashes("The total profit is " + (NUM_TICKETS * PRICE_PER_TICKET - loss));
		
		
		// FIXME: Testing
		calcTheoryLoss(matches);
	}
	
	public static void calcTheoryLoss(HashMap<Integer, Integer> numMatches) {
		HashMap<Integer, Integer> theoryProb = theoryProb();
		HashMap<Integer, Integer> prizes = genPrizes();
		
		double theoryLoss = 0.0;
		
		printDashes("Calculating theoretical loss (payment of prizes to customer)");   
		for (Map.Entry<Integer, Integer> entry : numMatches.entrySet()) {
			System.out.println("For " + entry.getKey() + " the prize is " + prizes.get(entry.getKey()) + " and the theoretical probability is " + theoryProb.get(entry.getKey()) / SAMPLE_SPACE);
			theoryLoss += NUM_TICKETS * prizes.get(entry.getKey()) * theoryProb.get(entry.getKey()) / SAMPLE_SPACE;
		}
		printDashes("The theoretical loss is " + theoryLoss);
		printDashes("Revenue from tickets is " + NUM_TICKETS * PRICE_PER_TICKET);
		printDashes("The total profit is " + (NUM_TICKETS * PRICE_PER_TICKET - theoryLoss));
	}		
}
