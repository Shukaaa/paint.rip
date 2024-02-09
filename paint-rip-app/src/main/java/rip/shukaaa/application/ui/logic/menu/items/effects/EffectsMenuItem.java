package rip.shukaaa.application.ui.logic.menu.items.effects;

import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.effect.input.inputs.ComboBox;
import rip.shukaaa.api.effect.input.inputs.Slider;
import rip.shukaaa.api.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;
import rip.shukaaa.application.stores.DataStore;
import rip.shukaaa.application.ui.logic.menu.items.MenuItem;
import rip.shukaaa.application.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EffectsMenuItem extends MenuItem {
    private final String name;
    private final Effect effect;

    public EffectsMenuItem(String name, Effect effect) {
        super(Optional.empty());
        this.name = name;
        this.effect = effect;
    }

    @Override
    protected JMenuItem createItem() {
        JMenuItem item = new JMenuItem(this.name);
        item.addActionListener(e -> {
            // If the effect has no inputs, apply it and return (no JComponents needed)
            if (this.effect.getEffectInputs().length == 0) {
                applyEffect(new HashMap<>());
                return;
            }

            JDialog dialog = UiUtils.createDialog("Add " + name);
            HashMap<EffectInput, JComponent> componentMap = new HashMap<>();

            for (EffectInput input : this.effect.getEffectInputs()) {
                JComponent component = input.createJComponent();

                dialog.add(new JLabel(input.getTitle() + ":"));
                dialog.add(component);
                componentMap.put(input, component);
            }

            JButton apply = new JButton("Apply");
            dialog.add(apply);

            apply.addActionListener(e1 -> {
                HashMap<String, Object> options = new HashMap<>();
                for (Map.Entry<EffectInput, JComponent> component : componentMap.entrySet()) {
                    EffectInput effectInput = component.getKey();
                    JComponent generatedComponent = component.getValue();

                    options.put(effectInput.getTitle(), effectInput.getInputValue(generatedComponent));
                }
                applyEffect(options);
                dialog.dispose();
            });
            dialog.pack();
        });

        return item;
    }

    private void applyEffect(HashMap<String, Object> options) {
        ShukaaaImage img = DataStore.getImg();
        try {
            img.applyEffect(this.effect, options);
        } catch (EffectOptionNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        ImageManager.updateImage(img);
        DataStore.setImg(img);
    }
}
