package rip.shukaaa.api.effect.effects.transform;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;
import rip.shukaaa.api.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class VerticalFlip extends Effect {
    public VerticalFlip() {
        super(new EffectInput[0], EffectCategoryRegister.TRANSFORM);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
        Pixel[][] pixels2d = image.getPixels2d();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height / 2; y++) {
                Pixel temp = pixels2d[x][y];
                pixels2d[x][y] = pixels2d[x][height - y - 1];
                pixels2d[x][height - y - 1] = temp;
            }
        }

        return ImageUtils.pixel2dTo1d(pixels2d);
    }
}
