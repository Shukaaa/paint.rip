package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class Invert implements Effect {
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        int intensity;
        try {
            intensity = (int) args.get("threshold");
        } catch (NullPointerException e) {
            throw new EffectOptionNotFoundException("Threshold not found");
        }

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
