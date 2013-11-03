/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

/** イヤホン2本差しウォークマン */
public class Walkman2 extends Walkman {

	private Object jack2 = null;
	
	/**
	 * 2個目のイヤホンを接続します。
	 * 
	 * @param earphones イヤホン
	 */
	public void connect2(Object earphones) {
		jack2 = earphones;
	}
	
	/**
	 * 2個目のイヤホンを外します。
	 */
	public void disconnect2() {
		if (isPlaying()) {
			stop();
		}
		jack2 = null;
	}
	
	/**
	 * 2個目のイヤホンの接続状態を取得します。
	 * 
	 * @return イヤホンが接続されていれば true、そうでなければ false。
	 */
	public boolean isConnected2() {
		return jack2 != null ? true : false;
	}
}
