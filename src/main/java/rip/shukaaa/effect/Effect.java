package rip.shukaaa.effect;

import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Effect {
    public abstract ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException;
    EffectInput[] effectInputs;
    EffectCategory category;

    public Effect(EffectInput[] effectInputs, EffectCategory category) {
        this.effectInputs = effectInputs;
        this.category = category;
    }

    public EffectInput[] getEffectInputs() {
        return effectInputs;
    }

    public EffectCategory getCategory() {
        return category;
    }
}
