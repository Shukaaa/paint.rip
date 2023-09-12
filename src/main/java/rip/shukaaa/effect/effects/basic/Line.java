package rip.shukaaa.effect.effects.basic;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Line extends Effect {
    public Line() {
        super(new EffectInput[]{

        }, EffectCategory.EFFECTS);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        // make all even pixels black
        ArrayList<Pixel> pixels = image.getPixels();
        for (int i = 0; i < pixels.size(); i++) {
            if (i % 2 == 0) {
                pixels.get(i).setRed(0);
                pixels.get(i).setGreen(0);
                pixels.get(i).setBlue(0);
            }
        }
        return pixels;
    }
}
