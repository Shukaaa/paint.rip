package rip.shukaaa.image;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShukaaaImage extends BufferedImage {
		private ArrayList<Pixel> pixels;
		private Pixel[][] pixels2d;

		public ShukaaaImage(BufferedImage img) {
				super(img.getWidth(), img.getHeight(), img.getType());
				this.setData(img.getData());
		}

		public ShukaaaImage(int width, int height) {
				super(width, height, BufferedImage.TYPE_INT_RGB);
		}

		//* Pixels *//

		private void checkPixels() {
				if (pixels == null) {
						pixels = new ArrayList<>();
						for (int x = 0; x < this.getWidth(); x++) {
								for (int y = 0; y < this.getHeight(); y++) {
										pixels.add(new Pixel(this.getRGB(x, y)));
								}
						}

						pixels2d = new Pixel[this.getWidth()][this.getHeight()];

						int i = 0;
						for (int x = 0; x < this.getWidth(); x++) {
								for (int y = 0; y < this.getHeight(); y++, i++) {
										pixels2d[x][y] = pixels.get(i);
								}
						}
				}
		}

		public ArrayList<Pixel> getPixels() {
				checkPixels();
				return pixels;
		}

		public Pixel[][] getPixels2d() {
				checkPixels();
				return pixels2d;
		}

		public void setPixels(ArrayList<Pixel> pixels) {
				this.pixels = pixels;

				// Update image
				int i = 0;
				for (int x = 0; x < this.getWidth(); x++) {
						for (int y = 0; y < this.getHeight(); y++, i++) {
								this.setRGB(x, y, pixels.get(i).toInt());
						}
				}
		}

		//* Resize *//

		public ShukaaaImage resizedImage(int width, int height) {
				BufferedImage resized = new BufferedImage(width, height, this.getType());
				resized.getGraphics().drawImage(this, 0, 0, width, height, null);
				return new ShukaaaImage(resized);
		}

		//* Export *//

		public void export(String name, ImageFormats format, ShukaaaImage... imgs) {
				ShukaaaImage img;

				if (imgs.length > 0) {
						img = imgs[0];
				} else {
						img = this;
				}

				try {
						File f = new File(name + "." + format.getFormat());
						ImageIO.write(img, format.getFormat(), f);
				} catch (IOException e) {
						System.err.println("Error while writing image!");
				}
		}

		public void export(String name, ImageFormats format, int width, int height) {
				ShukaaaImage resized = this.resizedImage(width, height);
				resized.export(name, format);
		}

		public void applyEffect(Effect effect, HashMap<String, Object> args) throws EffectOptionNotFoundException {
				EffectInput[] inputs = effect.getEffectInputs();

				for (EffectInput input : inputs) {
						if (!args.containsKey(input.getTitle())) {
								throw new EffectOptionNotFoundException(input.getTitle());
						}
				}

				this.setPixels(effect.apply(this, args));
		}
}
