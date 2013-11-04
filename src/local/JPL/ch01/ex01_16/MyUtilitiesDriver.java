/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_16;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * MyUtilities の機能を触ってみるテスト。
 */
public class MyUtilitiesDriver {

	public static void main(String[] args) {
		MyUtilities util = new MyUtilities();
		try {
			util.getDataSet("test");
		} catch (BadDataSetException e) {
			System.out.println("BadDataSetException: " + e.getSetName());
		}
	}
	
	/** MyUtilities で BadDataSetException を発生させ、その中の getSetName() をテストします。 */
	@Test
	public void testGetSetName_case1() {
		String arg = "file_not_found";
		MyUtilities util = new MyUtilities();
		try {
			util.getDataSet(arg);
		} catch (BadDataSetException e) {
			assertTrue(e.getSetName().equals(arg));
		}
	}
	
	/** MyUtilities で BadDataSetException を発生させ、その中の getIOExceptionContext() をテストします。 */
	@Test
	public void testGetIOException_case1() {
		String arg = "file_not_found";
		MyUtilities util = new MyUtilities();
		try {
			util.getDataSet(arg);
		} catch (BadDataSetException e) {
			assertTrue(e.getIOExceptionContext() != null);
			assertTrue(e.getIOExceptionContext() instanceof IOException);
		}
	}
}
