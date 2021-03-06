import java.util.HashSet;
import java.util.Scanner;


public class StraightFlush {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String cardsLine = scanner.nextLine();
		String[] cards = cardsLine.split("\\W+");
		HashSet<String> cardsSet = new HashSet<>();
		for (String card : cards) {
			cardsSet.add(card);
		}
		
		boolean hasFlushes = false;
		for (String card : cards) {
			String[] flush = getFlush(card);
			
			if (flush == null) {
				continue;
			}
			
			boolean isValid = true;
			for (String flushCard : flush) {
				if (!cardsSet.contains(flushCard)) {
					isValid = false;
					break;
				}
			}
			
			if (isValid) {
				hasFlushes = true;
				System.out.print("[");
				System.out.print(String.join(", ", flush));
				System.out.print("]\n");
			}
		}
		
		if (!hasFlushes) {
			System.out.println("No Straight Flushes");
		}
	}
	
	private static String[] getFlush(String current) {
		String[] cardRanks = {
				"2", "3", "4", "5", "6", "7",
				"8", "9", "10", "J", "Q", "K", "A" };
		
		String cardRank = current.substring(0, current.length() - 1);
		char cardSuit = current.charAt(current.length() - 1);
		String[] flush = new String[5];
		for (int i = 0; i < cardRanks.length; i++) {
			
			if (i == cardRanks.length - 4) {
				return null;
			}
			if (cardRanks[i].equals(cardRank)) {
				for (int j = 0, k = i; j < 5; j++, k++) {
					flush[j] = cardRanks[k] + cardSuit;
				}
				return flush;
			}
		}
		
		return null;
	}
}
