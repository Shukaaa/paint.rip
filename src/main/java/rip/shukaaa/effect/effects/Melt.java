package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Melt implements Effect {
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        int threshold;
        try {
            threshold = (int) args.get("threshold");
        } catch (NullPointerException e) {
            throw new EffectOptionNotFoundException("Threshold not found");
        }

        Pixel pixelColor;
        try {
            pixelColor = (Pixel) args.get("color");
        } catch (NullPointerException e) {
            throw new EffectOptionNotFoundException("Color not found");
        }

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
