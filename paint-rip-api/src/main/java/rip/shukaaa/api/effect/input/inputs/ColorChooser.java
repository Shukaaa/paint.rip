package rip.shukaaa.api.effect.input.inputs;

import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.effect.input.EffectInput;

import javax.swing.*;
import java.awt.*;

public class ColorChooser extends EffectInput {
    public ColorChooser(String title) {
        super("colorchooser", title);
    }

    @Override
    public JComponent createJComponent() {
        return new JColorChooser();
    }

    @Override
    public Object getInputValue(JComponent component) {
        Color color = ((JColorChooser) component).getColor();
        return new Pixel(color.getRed(), color.getGreen(), color.getBlue());
    }
}
