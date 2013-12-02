/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_05;

public class MethodBenchmark2 extends Benchmark2 {
	
	@Override
	void benchmark() {
	}
	
	@Override
	void benchmark2(int param) {
		for (int i = 0; i < param; i++) {

		}
	}

	public static void main(String[] args) {
		int count = 100;
		if (args.length > 0) {
			count = Integer.parseInt(args[0]);
		}
		long time = new MethodBenchmark2().repeat2(count, 100);
		System.out.println(count + " methods in " + time + " nanoseconds");
	}
}
