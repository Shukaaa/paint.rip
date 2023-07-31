package rip.shukaaa.effect;

import rip.shukaaa.effect.effects.*;

import java.util.HashMap;

public final class EffectRegister {
    private static final HashMap<String, Effect> effects = new HashMap<>(){{
        put("blackAndWhite", new BlackAndWhite());
        put("cosSinShuffler", new CosSinShuffler());
        put("distortionFlip", new DistortionFlip());
        put("flip", new Flip());
        put("grayscale", new Grayscale());
        put("invert", new Invert());
        put("melt", new Melt());
        put("randomShuffler", new RandomShuffler());
        put("rowSlicer", new RowSlicer());
    }};

    public static Effect getEffect(String name) {
        if (!effects.containsKey(name)) {
            throw new IllegalArgumentException("Effect " + name + " does not exist");
        }

        return effects.get(name);
    }
}
