package rip.shukaaa.application.effect.effects;

import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;
import rip.shukaaa.application.effect.category.EffectCategory;

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
