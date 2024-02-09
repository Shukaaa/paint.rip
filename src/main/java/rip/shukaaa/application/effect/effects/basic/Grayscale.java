package rip.shukaaa.application.effect.effects.basic;

import rip.shukaaa.application.effect.category.EffectCategoryRegister;
import rip.shukaaa.application.effect.effects.Effect;
import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.effect.input.inputs.Slider;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class Grayscale extends Effect {
    public Grayscale() {
        super(new EffectInput[]{
                new Slider(0, 256, 256, "Threshold")
        }, EffectCategoryRegister.BASICS);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();
        int threshold = (int) args.get("Threshold");

        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
            }
        }

        return pixels;
    }
}
