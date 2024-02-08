package rip.shukaaa.effect.effects.generate;

import rip.shukaaa.effect.category.EffectCategoryRegister;
import rip.shukaaa.effect.effects.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;
import rip.shukaaa.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Border extends Effect {
    public Border() {
        super(new EffectInput[]{
                new Slider(0, 100, 5, "Border Width in Px")
        }, EffectCategoryRegister.GENERATE);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        int borderWidth = (int) args.get("Border Width in Px");
        Pixel[][] pixels = image.getPixels2d();

        for (int i = 0; i < pixels.length; i++) {
            if (i < borderWidth || i > pixels.length - borderWidth) {
                for (int j = 0; j < pixels[i].length; j++) {
                    pixels[i][j].setRed(0);
                    pixels[i][j].setGreen(0);
                    pixels[i][j].setBlue(0);
                }
            } else {
                for (int j = 0; j < pixels[i].length; j++) {
                    if (j < borderWidth || j > pixels[i].length - borderWidth) {
                        pixels[i][j].setRed(0);
                        pixels[i][j].setGreen(0);
                        pixels[i][j].setBlue(0);
                    }
                }
            }
        }

        return ImageUtils.pixel2dTo1d(pixels);
    }
}
