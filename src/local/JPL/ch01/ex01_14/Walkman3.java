/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

/** 会話できるウォークマン */
public class Walkman3 extends Walkman2 {
	
	/**
	 * 双方向コミュニケーションを行います。
	 * 
	 * @throws RuntimeException イヤホンが2本刺さっていない場合
	 */
	public void communicate() {
		if (!isConnected() || !isConnected2()) {
			throw new RuntimeException("Earphones are not connected to both jack.");
		}
	}
}
