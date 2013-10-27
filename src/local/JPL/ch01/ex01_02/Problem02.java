package local.JPL.ch01.ex01_02;

import static org.junit.Assert.fail;

import org.junit.Test;

/** 練習問題 1.2: HelloWorld でエラーを発生させる。 */
public class Problem02 {
	public static void main(String[] args) {
		
		// "Hello World!"
		System.out.println("Hello World!");
		
		// java.lang.Error
//		System.out.println("Hello World!);
		
		// "Hello World!null"
		System.out.println("Hello World!" + null);

		// java.lang.NumberFormatException
		System.out.println(Integer.parseInt("Hello World!"));
		
		// java.lang.RuntimeException
		System.out.println(new RuntimeException("Hello World!"));
		

	}
	
	//** main() のテストメソッド */
	@Test
	public void testMain() {
		try {
			main(null);
		} catch (Exception e) {
			fail();
		}
	}
}
