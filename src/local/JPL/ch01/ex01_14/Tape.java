/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_14;

/**
 * テープを示すクラスです。
 */
public class Tape {
	
	/**
	 * カセットテープの規格です。
	 */
	enum Type {
		ACC, DCC, ELCASET, MC, OTHER,
	}
	
	private Type type;
	
	public Tape() {
		type = Type.ACC;
	}
	
	public Tape(Type type) {
		this.type = type;
	}
		
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
}
