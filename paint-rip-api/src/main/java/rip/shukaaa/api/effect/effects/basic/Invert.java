package rip.shukaaa.api.effect.effects.basic;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.effect.input.inputs.Slider;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class Invert extends Effect {
    public Invert() {
        super(new EffectInput[]{
                new Slider(0, 256, 256, "Threshold")
        }, EffectCategoryRegister.BASICS);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
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
