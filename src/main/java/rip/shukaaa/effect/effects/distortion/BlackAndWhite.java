package rip.shukaaa.effect.effects.distortion;

import rip.shukaaa.effect.category.EffectCategoryRegister;
import rip.shukaaa.effect.effects.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class BlackAndWhite extends Effect {
    public BlackAndWhite() {
        super(new EffectInput[]{
                new Slider(0, 256, 256, "Threshold")
        }, EffectCategoryRegister.DISTORTION);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        int threshold = (int) args.get("Threshold");

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
