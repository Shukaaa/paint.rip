package rip.shukaaa.api.effect.effects;

import rip.shukaaa.api.effect.effects.basic.*;
import rip.shukaaa.api.effect.effects.distortion.*;
import rip.shukaaa.api.effect.effects.generate.*;
import rip.shukaaa.api.effect.effects.transform.*;

import java.util.HashMap;

public final class EffectRegister {
    public static final HashMap<String, Effect> effects = new HashMap<>() {{
        // Basic
        put("Grayscale", new Grayscale());
        put("Invert", new Invert());
        put("Melt", new Melt());
        put("Line", new Line());

        // Distortion
        put("Black and White", new BlackAndWhite());
        put("Cos-Sin-Shuffler", new CosSinShuffler());
        put("Noise Shuffler", new RandomShuffler());
        put("Row Slicer", new RowSlicer());
        put("Triangle Ruler", new TriangleRuler());

        // Transform
        put("Vertically Flip", new VerticalFlip());
        put("Horizontally Flip", new HorizontalFlip());
        put("180° Flip", new Degrees180Flip());

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
