/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch06.ex06_05;

// めんどくさいので推奨したくないですね
public enum TrafficSignal {
	OFF {
		public Color getColor() {
			return new Color(0x000000);
		}
	},
	RED {
		public Color getColor() {
			return new Color(0xff0000);
		}
	},
	YELLOW {
		public Color getColor() {
			return new Color(0xffff00);
		}
	},
	GLEEN {
		public Color getColor() {
			return new Color(0x008000);
		}
	},
	BLINK_RED {
		public Color getColor() {
			return new Color(0xff0000);
		}
	},
	BLINK_YELLOW {
		public Color getColor() {
			return new Color(0xffff00);
		}
	};
	public abstract Color getColor();
}
