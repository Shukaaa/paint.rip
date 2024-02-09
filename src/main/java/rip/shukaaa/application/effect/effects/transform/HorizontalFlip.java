package rip.shukaaa.application.effect.effects.transform;

import rip.shukaaa.application.effect.category.EffectCategoryRegister;
import rip.shukaaa.application.effect.effects.Effect;
import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;
import rip.shukaaa.application.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class HorizontalFlip extends Effect {
    public HorizontalFlip() {
        super(new EffectInput[0], EffectCategoryRegister.TRANSFORM);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        Pixel[][] pixels2d = image.getPixels2d();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < height; y++) {
                Pixel temp = pixels2d[x][y];
                pixels2d[x][y] = pixels2d[width - x - 1][y];
                pixels2d[width - x - 1][y] = temp;
            }
        }

        return ImageUtils.pixel2dTo1d(pixels2d);
    }
}
