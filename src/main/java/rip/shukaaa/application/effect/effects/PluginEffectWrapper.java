package rip.shukaaa.application.effect.effects;

import rip.shukaaa.application.effect.category.EffectCategory;
import rip.shukaaa.application.effect.input.EffectInput;
import rip.shukaaa.application.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.Pixel;
import rip.shukaaa.application.image.ShukaaaImage;
import rip.shukaaa.plugin.api.PluginEffect;

import java.util.ArrayList;
import java.util.HashMap;

public class PluginEffectWrapper extends Effect {
    PluginEffect pluginEffect;
    public PluginEffectWrapper(PluginEffect pluginEffect) {
        super(new EffectInput[0], pluginEffect.getEffectGroup());
        this.pluginEffect = pluginEffect;
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        return this.pluginEffect.apply(image);
    }
}
