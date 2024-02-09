package rip.shukaaa.application.effect.input.inputs;

import rip.shukaaa.application.effect.input.EffectInput;

public class Slider extends EffectInput {
    int min;
    int max;
    int defaultValue;

    public Slider(int min, int max, int defaultValue, String title) {
        super("slider", title);

        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
