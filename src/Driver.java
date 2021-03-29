//import java.util.Arrays;
//
///**
// * Deprecated
// */
//public class Driver {
//	public static void main(String[] args)
//	{	
//		/** Generate the winning Powerball combination */
//		Powerball winningCombo = new Powerball();
//		System.out.println("The winning combo is " + winningCombo.getPowerballs());
//		
//		/** The number of Powerball tickets */
//		final int NUM_ITERATIONS = (int)Math.pow(10, 6);
//		
//		/** Array containing the frequency of each number of matches */
//		int[] numMatches = new int[16];
//		
//		/** Making NUM_ITERATIONS of Powerballs */
//		for (int i = 0; i < NUM_ITERATIONS ; ++i)
//		{
//			Powerball random = new Powerball();
//			numMatches[winningCombo.getNumMatches(random)]++;	
//		}
//		
//		System.out.println(Arrays.toString(numMatches));
//	}
//}
