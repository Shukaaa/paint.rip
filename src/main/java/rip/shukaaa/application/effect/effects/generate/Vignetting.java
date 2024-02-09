package rip.shukaaa.application.effect.effects.generate;

import rip.shukaaa.application.effect.category.EffectCategoryRegister;
import rip.shukaaa.application.effect.effects.Effect;
import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;
import rip.shukaaa.application.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Vignetting extends Effect {
    public Vignetting() {
        super(new EffectInput[0], EffectCategoryRegister.GENERATE);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        // add effect to the image
        Pixel[][] pixels2d = image.getPixels2d();
        int width = image.getWidth();
        int height = image.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = (int) Math.sqrt(Math.pow(centerX, 2) + Math.pow(centerY, 2));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int distance = (int) Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
                double factor = 1 - (double) distance / radius;
                if (factor < 0) factor = 0;
                pixels2d[x][y].setRed((int) (pixels2d[x][y].getRed() * factor));
                pixels2d[x][y].setGreen((int) (pixels2d[x][y].getGreen() * factor));
                pixels2d[x][y].setBlue((int) (pixels2d[x][y].getBlue() * factor));
            }
        }

        return ImageUtils.pixel2dTo1d(pixels2d);
    }
}