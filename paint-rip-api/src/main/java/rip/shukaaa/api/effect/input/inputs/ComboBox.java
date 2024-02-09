package rip.shukaaa.api.effect.input.inputs;

import rip.shukaaa.api.effect.input.EffectInput;

import javax.swing.*;

public class ComboBox<E> extends EffectInput {
    E[] values;

    public ComboBox(E[] values, String title) {
        super("combobox", title);

        this.values = values;
    }

    @Override
    public JComponent createJComponent() {
        return new JComboBox<>(this.getValues());
    }

    @Override
    public Object getInputValue(JComponent component) {
        return ((JComboBox<?>) component).getSelectedItem();
    }

    public E[] getValues() {
        return values;
    }
}
