/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

import static org.junit.Assert.*;

import org.junit.Test;

/** 初代ウォークマン */
public class Walkman {

	private boolean playing = false;	// 再生状態
	private Tape tape = null;			// テープ種類、nullで空
	private Object jack = null;			// 端子状態

	/**
	 * 新しいテープをセットします。
	 * 
	 * @param tape テープ
	 * @return 取り出したテープ。空だった場合は null。
	 * @throws NullPointerException 入れるテープがない場合
	 * @throws IllegalArgumentException 異なる規格のテープを挿入した場合
	 */
	public Tape setTape(Tape tape) {
		if (tape == null) {
			throw new NullPointerException("Missing tape.");
		}
		if (!tape.getType().equals(Tape.Type.ACC)) {
			throw new IllegalArgumentException("Unsupported tape.");
		}
		if (isPlaying()) {
			stop();
		}
		Tape releaseTape = this.tape;
		this.tape = tape;
		return releaseTape;
	}
	
	/**
	 * イヤホンを接続します。
	 * 
	 * @param earphones イヤホン
	 */
	public void connect(Object earphones) {
		this.jack = earphones;
	}
	
	/**
	 * イヤホンを外します。
	 */
	public void disconnect() {
		if (isPlaying()) {
			stop();
		}
		jack = null;
	}
	
	/**
	 * イヤホンの接続状態を取得します。
	 * 
	 * @return イヤホンが接続されていれば true、そうでなければ false。
	 */
	public boolean isConnected() {
		return jack != null ? true : false;
	}
	
	/**
	 * 音楽を再生します。
	 * 
	 * @throws RuntimeException カセットがない場合、イヤホンが刺さっていない場合
	 */
	public void start() {
		if (tape == null) {
			throw new RuntimeException("Casettle is empty.");
		}
		if (isConnected()) {
			throw new RuntimeException("Jack is empty.");
		}
		playing = true;
	}
	
	/**
	 * 音楽の再生を停止します。
	 */
	public void stop() {
		playing = false;
	}
	
	/**
	 * 再生状態を取得します。
	 * @return
	 */
	public boolean isPlaying() {
		return playing;
	}
	
	/** 正しい規格のテープを正常に受け付けるか試します。 */
	@Test
	public void testSetTape_normalInput() {
		Tape testTape = new Tape();
		Walkman walkman = new Walkman();
		walkman.setTape(testTape);
		Tape accTape = new Tape(Tape.Type.ACC);
		walkman.setTape(accTape);
	}
	
	/** 規格外のテープが通ってしまわないか試します。 */
	@Test
	public void testSetTape_illegalTypes() {
		Tape elcastTape = new Tape(Tape.Type.ELCASET);
		Walkman walkman = new Walkman();
		try {
			walkman.setTape(elcastTape);
			fail("規格外が通ってしまった。");
		} catch (IllegalArgumentException e) {
			// Test success
		}
	}
	
	/** テープの取り出し結果が正しいか試します。 */
	@Test
	public void testSetTape_retrurn() {
		Tape testTape1 = new Tape();
		Tape tsetTape2 = new Tape();
		Tape testTape3 = new Tape();
		Walkman walkman = new Walkman();
		assertNull(walkman.setTape(testTape1));		// null が返れば OK
		assertNotNull(walkman.setTape(tsetTape2));	// tape1 が返ってくる (null でない) はず
		assertTrue(walkman.setTape(testTape3).equals(tsetTape2));	// tape2 が返ってくるはず
	}
}
