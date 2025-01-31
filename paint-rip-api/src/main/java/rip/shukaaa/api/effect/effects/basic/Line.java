package rip.shukaaa.api.effect.effects.basic;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Line extends Effect {
    public Line() {
        super(new EffectInput[0], EffectCategoryRegister.BASICS);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
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
