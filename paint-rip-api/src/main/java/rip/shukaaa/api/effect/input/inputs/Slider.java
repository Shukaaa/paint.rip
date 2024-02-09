package rip.shukaaa.api.effect.input.inputs;

import rip.shukaaa.api.effect.input.EffectInput;

import javax.swing.*;

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

    @Override
    public JComponent createJComponent() {
        return new JSlider(this.getMin(), this.getMax(), this.getDefaultValue());
    }

    @Override
    public Object getInputValue(JComponent component) {
        return ((JSlider) component).getValue();
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
