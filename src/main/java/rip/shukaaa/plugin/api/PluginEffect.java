package rip.shukaaa.plugin.api;

import rip.shukaaa.application.effect.category.EffectCategory;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;

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
