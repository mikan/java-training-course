package local.JPL.ch01.ex01_02;

public class Problem02 {
	public static void main(String[] args) {
		
		// "Hello World!"
		System.out.println("Hello World!");
		
		// "Hello World!null"
		System.out.println("Hello World!" + null);

		// java.lang.NumberFormatException
		System.out.println(Integer.parseInt("Hello World!"));
		
		// java.lang.RuntimeException
		System.out.println(new RuntimeException("Hello World!"));
		
		// "-969099747"
		System.out.println("Hello World!".hashCode());
	}
}
