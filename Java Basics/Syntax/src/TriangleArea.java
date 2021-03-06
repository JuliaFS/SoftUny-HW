import java.util.Scanner;

/*
 * Write a program that enters 3 points in the plane 
 * (as integer x and y coordinates), calculates and
 * prints the area of the triangle composed by these 3 points.
 * Round the result to a whole number. In case the
 * three points do not form a triangle, print "0" as result.
 */
public class TriangleArea {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner( System.in );
		int aX = scanner.nextInt();
		int aY = scanner.nextInt();
		int bX = scanner.nextInt();
		int bY = scanner.nextInt();
		int cX = scanner.nextInt();
		int cY = scanner.nextInt();
		float area = ( aX * ( bY - cY ) + bX * ( cY - aY ) + cX * ( aY - bY ) ) / 2;
		long roundedArea = Math.round( area );
		
		if ( roundedArea < 0 ) {
			roundedArea *= -1;
		} 
		System.out.println( roundedArea );
	}

}
