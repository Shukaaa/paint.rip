package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class CosSinShuffler implements Effect {
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        int modulo;

        try {
            modulo = (int) args.get("modulo");
        } catch (NullPointerException e) {
            throw new EffectOptionNotFoundException("Modulo not found");
        }

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
