import java.util.Scanner;

/*
 * Write a program to check whether a point is
 * inside or outside of the figure below.
 * The point is given as a pair of floating-point numbers,
 * separated by a space. Your program should
 * print "Inside" or "Outside"
 */
public class PointsInsideFigure {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner( System.in );
		float xPos = scanner.nextFloat();
		float yPos = scanner.nextFloat();
		boolean isInsideFigure = false;
		
		if ( yPos >= 6 && yPos <= 8.5f ) {
			if ( xPos >= 12.5f && xPos <= 22.5f ) {
				isInsideFigure = true;
			}
		} else if ( yPos > 8.5f && yPos <= 13.5f ) {
			if ( xPos >= 12.5F && xPos <= 17.5F ) {
				isInsideFigure = true;
			} else if ( xPos >= 20 && xPos <= 22.5F) {
				isInsideFigure = true;
			}
		}
		
		String outputMsg = isInsideFigure ? "Inside" : "Outside";
		System.out.println( outputMsg );
	}

}
