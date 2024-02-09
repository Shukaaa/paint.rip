package rip.shukaaa.api.effect.effects;

import rip.shukaaa.api.PluginEffect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class PluginEffectWrapper extends Effect {
    PluginEffect pluginEffect;
    public PluginEffectWrapper(PluginEffect pluginEffect) {
        super(new EffectInput[0], pluginEffect.getEffectGroup());
        this.pluginEffect = pluginEffect;
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
        return this.pluginEffect.apply(image);
    }
}
