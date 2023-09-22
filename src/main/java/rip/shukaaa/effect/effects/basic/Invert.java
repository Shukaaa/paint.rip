package rip.shukaaa.effect.effects.basic;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class Invert extends Effect {
		public Invert() {
				super(new EffectInput[]{
						new Slider(0, 256, 256, "Threshold")
				}, EffectCategory.EFFECTS);
		}

		public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
				ArrayList<Pixel> pixels = image.getPixels();

				int intensity = (int) args.get("Threshold");

				for (Pixel pixel : pixels) {
						if (pixel.getRed() < intensity && pixel.getGreen() < intensity && pixel.getBlue() < intensity) {
								pixel.setRed(255 - pixel.getRed());
								pixel.setGreen(255 - pixel.getGreen());
								pixel.setBlue(255 - pixel.getBlue());
						}
				}

				return pixels;
		}
}
