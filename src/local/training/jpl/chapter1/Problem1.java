package local.training.jpl.chapter1;

import local.training.ExecMethod;

public class Problem1 implements ExecMethod {
	
	/** Launcher から呼び出される起動メソッドです。 */
	@Override
	public void exec() {
		System.out.println("HelloWorld");
	}
}
