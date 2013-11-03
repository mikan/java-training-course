/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_16;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyUtilities {
	public double[] getDataSet(String setName) throws BadDataSetException {
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException(setName, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// 無視:	データの読み込みは成功しているか、あるいは、
				// 		BadDataSetException をスローしようとしている。
			}
		}
	}
	
	public double[] readDataSet(InputStream stream) {
		return null;
	}
}
