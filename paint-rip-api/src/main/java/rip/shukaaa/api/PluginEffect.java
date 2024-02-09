package rip.shukaaa.api;

import rip.shukaaa.api.effect.category.EffectCategory;

import java.util.ArrayList;

public abstract class PluginEffect {
    String effectName;
    EffectCategory effectGroup;

    public abstract ArrayList<Pixel> apply(ShukaaaImage image);

    public String getEffectName() {
        return effectName;
    }

    public EffectCategory getEffectGroup() {
        return effectGroup;
    }
}
