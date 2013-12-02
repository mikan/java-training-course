package local.JPL.ch03.ex03_05;

public abstract class Benchmark2 extends Benchmark {
	abstract void benchmark2(int param);
	
	public final long repeat2(int count, int param) {

		long start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			benchmark2(param);
		}
		return System.nanoTime() - start;
	}
}
