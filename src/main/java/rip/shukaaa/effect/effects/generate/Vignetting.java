package rip.shukaaa.effect.effects.generate;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;
import rip.shukaaa.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Vignetting extends Effect {
    public Vignetting() {
        super(new EffectInput[]{}, EffectCategory.GENERATE);
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
            for (int y = 0; y < height; y ++) {
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
