package rip.shukaaa.api.effect.effects.basic;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.effect.input.inputs.ColorChooser;
import rip.shukaaa.api.effect.input.inputs.Slider;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Melt extends Effect {
    public Melt() {
        super(new EffectInput[]{
                new Slider(0, 256, 256, "Threshold"),
                new ColorChooser("Color")
        }, EffectCategoryRegister.BASICS);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
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
