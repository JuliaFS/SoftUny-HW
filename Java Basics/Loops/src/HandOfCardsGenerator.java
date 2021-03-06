import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/* Problem 6
 * Write a program to generate n random hands of
 * 5 different cards form a standard suit of 52 cards.
 */
public class HandOfCardsGenerator {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();
		int n = scanner.nextInt();
		
		String[] deck = getDeck();
		for (int i = 0; i < n; i++) {
			ArrayList<String> currentHand = new ArrayList<String>();
			while (currentHand.size() < 5) {
				int randomIndex = rand.nextInt(deck.length - 1);
				if (!currentHand.contains(deck[randomIndex])) {
					currentHand.add(deck[randomIndex]);
				}
			}
			
			for (String card : currentHand) {
				System.out.printf("%s ", card);
			}
			System.out.println();
		}
	}
	
	private static String[] getDeck() {
		String[] ranks = { "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "J", "Q", "K", "A" };
		char[] suits = { '\u2660', '\u2665', '\u2666', '\u2663' };
		String[] deck = new String[ranks.length * suits.length];
		int count = 0;
		for (String rank : ranks) {
			for (char suit : suits) {
				deck[count++] = rank + suit;
			}
		}
		
		return deck;
	}

}
