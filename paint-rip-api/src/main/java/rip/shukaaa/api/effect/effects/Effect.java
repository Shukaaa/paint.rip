package rip.shukaaa.api.effect.effects;

import rip.shukaaa.api.effect.category.EffectCategory;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Effect {
    public abstract ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args);

    EffectInput[] effectInputs;
    EffectCategory category;

    public Effect(EffectInput[] effectInputs, EffectCategory category) {
        this.effectInputs = effectInputs;
        this.category = category;
    }

    public Effect(EffectCategory category) {
        this.effectInputs = new EffectInput[0];
        this.category = category;
    }

    public EffectInput[] getEffectInputs() {
        return effectInputs;
    }

    public EffectCategory getCategory() {
        return category;
    }

    public String getEffectName() {
        return this.getClass().getSimpleName();
    }
}
