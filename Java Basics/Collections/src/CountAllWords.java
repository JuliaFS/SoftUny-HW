import java.util.Scanner;

/*
 * Write a program to count the number of words in given sentence.
 * Use any non-letter character as word separator
 */
public class CountAllWords {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String[] words = scanner.nextLine().split("\\W+");
		System.out.println(words.length);
	}

}
