/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_05;

public abstract class Benchmark {
	abstract void benchmark();

	public final long repeat(int count) {

		long start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			benchmark();
		}
		return System.nanoTime() - start;
	}
}
