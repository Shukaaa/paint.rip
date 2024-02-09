package rip.shukaaa.application.effect.input.inputs;

import rip.shukaaa.application.effect.input.EffectInput;

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
