package rip.shukaaa.api.effect.effects.distortion;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.effect.input.inputs.Slider;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class CosSinShuffler extends Effect {
    public CosSinShuffler() {
        super(new EffectInput[]{
                new Slider(0, 255, 100, "Modulo")
        }, EffectCategoryRegister.DISTORTION);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
        int modulo = (int) args.get("Modulo");

        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> pixels = image.getPixels();
        ArrayList<Pixel> newPixels = new ArrayList<>(pixels.size());

        for (Pixel pixel : pixels) {
            int x = pixel.getX(width);
            int y = pixel.getY(width);

            int newX = (int) (Math.cos(x) * modulo);
            int newY = (int) (Math.sin(y) * modulo);

            if (newX < 0) newX = -newX;
            if (newY < 0) newY = -newY;

            if (newX >= width) newX = width - 1;
            if (newY >= height) newY = height - 1;

            Pixel newPixel = pixels.get(newX + newY * width);
            pixel.setRed(newPixel.getRed());
            pixel.setGreen(newPixel.getGreen());
            pixel.setBlue(newPixel.getBlue());

            newPixels.add(pixel);
        }

        return newPixels;
    }
}
