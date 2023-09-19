package rip.shukaaa.image;

public class Pixel {
		private int pixel;

		public Pixel(int pixel) {
				this.pixel = pixel;
		}

		public Pixel(int red, int green, int blue) {
				this.pixel = (255 << 24) | (red << 16) | (green << 8) | blue;
		}

		public int toInt() {
				return pixel;
		}

		public int getRed() {
				return (pixel >> 16) & 0xff;
		}

		public int getGreen() {
				return (pixel >> 8) & 0xff;
		}

		public int getBlue() {
				return (pixel) & 0xff;
		}

		public int getAlpha() {
				return (pixel >> 24) & 0xff;
		}

		public void setRed(int red) {
				pixel = (pixel & 0xff00ffff) | ((red & 0xff) << 16);
		}

		public void setGreen(int green) {
				pixel = (pixel & 0xffff00ff) | ((green & 0xff) << 8);
		}

		public void setBlue(int blue) {
				pixel = (pixel & 0xffffff00) | (blue & 0xff);
		}

		public void setAlpha(int alpha) {
				pixel = (pixel & 0x00ffffff) | ((alpha & 0xff) << 24);
		}

		public String toHex() {
				return "#" + Integer.toHexString(this.getRed()) + Integer.toHexString(this.getGreen()) + Integer.toHexString(this.getBlue());
		}

		public void setPixel(int pixel) {
				this.pixel = pixel;
		}

		public int getX(int width) {
				return (pixel % width);
		}

		public int getY(int width) {
				return (pixel / width);
		}
}
