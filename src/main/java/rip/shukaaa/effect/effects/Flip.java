package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class Flip extends Effect {
    public Flip() {
        super(new EffectInput[0], EffectCategory.TRANSFORM);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = image.getPixels();

        ArrayList<Pixel> reversed = new ArrayList<>();
        for (int i = pixels.size() - 1; i >= 0; i--) {
            reversed.add(pixels.get(i));
        }
        return reversed;
    }
}
