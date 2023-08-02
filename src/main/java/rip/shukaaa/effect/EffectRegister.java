package rip.shukaaa.effect;

import rip.shukaaa.effect.effects.*;

import java.util.HashMap;

public final class EffectRegister {
    public static final HashMap<String, Effect> effects = new HashMap<>(){{
        put("Black and White", new BlackAndWhite());
        put("Cos-Sin-Shuffler", new CosSinShuffler());
        put("Distortion Flip", new DistortionFlip());
        put("Flip", new Flip());
        put("Grayscale", new Grayscale());
        put("Invert", new Invert());
        put("Melt", new Melt());
        put("Noise Shuffler", new RandomShuffler());
        put("Row Slicer", new RowSlicer());
    }};

    public static Effect getEffect(String name) {
        if (!effects.containsKey(name)) {
            throw new IllegalArgumentException("Effect " + name + " does not exist");
        }

        return effects.get(name);
    }
}
