import java.util.HashMap;
import java.util.Scanner;

/*
 * We are given a sequence of N playing cards from a standard deck.
 * The input consists of several cards (face + suit),
 * separated by a space.
 * Write a program to calculate and print at
 * the console the frequency of each card face in
 * format "card_face -> frequency".
 * The frequency is calculated by
 * the formula appearances / N and is expressed in
 * percentages with exactly 2 digits after the decimal point.
 * The card faces with their frequency should be
 * printed in the order of
 * the card face's first appearance in the input.
 * The same card can appear multiple times in the input,
 * but it's face should be listed only once in the output.
 */
public class CardsFrequencies {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String[] cards = scanner.nextLine().split(" ");
		HashMap<String, Integer> cardsSet = new HashMap<>();
		
		for (String card : cards) {
			String cardRank = card.substring(0, card.length() - 1);
			if (!cardsSet.containsKey(cardRank)) {
				cardsSet.put(cardRank, 0);
			}
			int count = cardsSet.get(cardRank) + 1;
			cardsSet.put(cardRank, count);
		}
		
		for (String card : cards) {
			String cardRank = card.substring(0, card.length() - 1);
			if (cardsSet.containsKey(cardRank)) {
				double percentage = ((double)cardsSet.get(cardRank) / cards.length) * 100;
				System.out.printf("%s -> %.2f%%\n", cardRank, percentage);
				cardsSet.remove(cardRank);
			}
		}
	}

}
