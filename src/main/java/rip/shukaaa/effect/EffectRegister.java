package rip.shukaaa.effect;

import rip.shukaaa.effect.effects.basic.*;
import rip.shukaaa.effect.effects.distortion.*;
import rip.shukaaa.effect.effects.generate.Border;
import rip.shukaaa.effect.effects.generate.Vignetting;
import rip.shukaaa.effect.effects.transform.Flip;

import java.util.HashMap;

public final class EffectRegister {
    public static final HashMap<String, Effect> effects = new HashMap<>(){{
        // Basic
        put("Grayscale", new Grayscale());
        put("Invert", new Invert());
        put("Melt", new Melt());
        put("Line", new Line());

        // Distortion
        put("Black and White", new BlackAndWhite());
        put("Cos-Sin-Shuffler", new CosSinShuffler());
        put("Distortion Flip", new DistortionFlip());
        put("Noise Shuffler", new RandomShuffler());
        put("Row Slicer", new RowSlicer());
        put("Triangle Ruler", new TriangleRuler());

        // Transform
        put("Flip", new Flip());

        // Generate
        put("Vignette", new Vignetting());
        put("Border", new Border());
    }};

    public static Effect getEffect(String name) {
        if (!effects.containsKey(name)) {
            throw new IllegalArgumentException("Effect " + name + " does not exist");
        }

        return effects.get(name);
    }
}
