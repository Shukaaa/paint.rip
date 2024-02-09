package rip.shukaaa.api.effect.input.inputs;

import rip.shukaaa.api.effect.input.EffectInput;

public class ComboBox<E> extends EffectInput {
    E[] values;

    public ComboBox(E[] values, String title) {
        super("combobox", title);

        this.values = values;
    }

    public E[] getValues() {
        return values;
    }
}
