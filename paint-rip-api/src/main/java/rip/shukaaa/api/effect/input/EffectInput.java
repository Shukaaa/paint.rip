package rip.shukaaa.api.effect.input;

import javax.swing.*;

public abstract class EffectInput {
    String name;
    String title;

    public EffectInput(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public JComponent createJComponent() {
        return new JLabel("No JComponent for " + this.name);
    }

    public Object getInputValue(JComponent component) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
