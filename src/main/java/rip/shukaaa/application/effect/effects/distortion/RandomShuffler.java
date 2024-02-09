package rip.shukaaa.application.effect.effects.distortion;

import rip.shukaaa.application.effect.category.EffectCategoryRegister;
import rip.shukaaa.application.effect.effects.Effect;
import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public final class RandomShuffler extends Effect {
    public RandomShuffler() {
        super(new EffectInput[0], EffectCategoryRegister.DISTORTION);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> pixels = image.getPixels();
        ArrayList<Pixel> newPixels = new ArrayList<>(pixels.size());

        for (Pixel pixel : pixels) {
            int newX = (int) (Math.random() * width);
            int newY = (int) (Math.random() * height);

            Pixel newPixel = pixels.get(newX + newY * width);
            pixel.setRed(newPixel.getRed());
            pixel.setGreen(newPixel.getGreen());
            pixel.setBlue(newPixel.getBlue());

            newPixels.add(pixel);
        }

        return newPixels;
    }
}
