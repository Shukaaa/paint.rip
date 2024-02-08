package rip.shukaaa.effect.effects.basic;

import rip.shukaaa.effect.category.EffectCategoryRegister;
import rip.shukaaa.effect.effects.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.ColorChooser;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Melt extends Effect {
    public Melt() {
        super(new EffectInput[]{
                new Slider(0, 256, 256, "Threshold"),
                new ColorChooser("Color")
        }, EffectCategoryRegister.BASICS);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        int threshold = (int) args.get("Threshold");

        Pixel pixelColor = (Pixel) args.get("Color");

        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();

        // melt color together with the pixels
        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                pixel.setRed((avg + r) / 2);
                pixel.setGreen((avg + g) / 2);
                pixel.setBlue((avg + b) / 2);
            }
        }

        return pixels;
    }
}
