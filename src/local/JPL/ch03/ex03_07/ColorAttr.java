/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_07;

public class ColorAttr extends Attr {
	
	private ScreenColor myColor;	// 変換された色
	
	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name) {
		this(name, "transparent");
	}
	
	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}
	
	public Object setValue(Object newValue) {
		// スーパークラスの setValue を最初に行う
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}
	
	/** 値を記述ではなく ScreenColor に設定する。 */
	public ScreenColor setValue(ScreenColor newValue) {
		// スーパークラスの setValue を最初に行う
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}
	
	public ScreenColor getColor() {
		return myColor;
	}
	
	@Override
	public boolean equals(Object obj) {
		return getValue().equals(obj);
	}
	
	@Override
	public int hashCode() {
		return getValue().hashCode();
	}
	
	protected void decodeColor() {
		if (getValue() == null) {
			myColor = null;
		} else {
			myColor = new ScreenColor(getValue());
		}
	}
}
