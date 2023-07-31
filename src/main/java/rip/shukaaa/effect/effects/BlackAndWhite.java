package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class BlackAndWhite implements Effect {
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        int threshold;
        try {
            threshold = (int) args.get("threshold");
        } catch (NullPointerException e) {
            throw new EffectOptionNotFoundException("Threshold not found");
        }

        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                if (avg > 127) {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                } else {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
            }
        }

        return pixels;
    }
}
