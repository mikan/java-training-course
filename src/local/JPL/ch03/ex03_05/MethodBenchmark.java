/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_05;

public class MethodBenchmark extends Benchmark {
	
	/** 何もしないで、単に戻るだけ */
	@Override
	void benchmark() {
		
	}

	public static void main(String[] args) {
		int count = 100;
		if (args.length > 0) {
			count = Integer.parseInt(args[0]);
		}
		long time = new MethodBenchmark().repeat(count);
		System.out.println(count + " methods in " + time + " nanoseconds");
	}
}
