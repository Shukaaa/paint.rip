package rip.shukaaa.ui.logic.menu.items.effects;

import rip.shukaaa.Main;
import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.ComboBox;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;
import rip.shukaaa.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EffectsMenuItem extends MenuItem {
		private final String name;
		private final Effect effect;
		private final UiManager uiManager;

		public EffectsMenuItem(String name, Effect effect, UiManager uiManager) {
				this.name = name;
				this.effect = effect;
				this.uiManager = uiManager;
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
						HashMap<String, JComponent> componentMap = new HashMap<>();

						for (EffectInput input : this.effect.getEffectInputs()) {
								JComponent component = createJComponent(input);

								dialog.add(new JLabel(input.getTitle() + ":"));
								dialog.add(component);
								componentMap.put(input.getTitle(), component);
						}

						JButton apply = new JButton("Apply");
						dialog.add(apply);

						apply.addActionListener(e1 -> {
								HashMap<String, Object> options = new HashMap<>();
								for (Map.Entry<String, JComponent> component : componentMap.entrySet()) {
										String key = component.getKey();
										JComponent value = component.getValue();

										options.put(key, getJComponentValue(value));
								}
								applyEffect(options);
								dialog.dispose();
						});
						dialog.pack();
				});

				return item;
		}

		private JComponent createJComponent(EffectInput input) {
				switch (input.getName()) {
						case "slider" -> {
								Slider sliderInput = (Slider) input;
								return new JSlider(sliderInput.getMin(), sliderInput.getMax(), sliderInput.getDefaultValue());
						}
						case "colorchooser" -> {
								return new JColorChooser();
						}
						case "combobox" -> {
								ComboBox<Object> comboBoxInput = (ComboBox<Object>) input;
								return new JComboBox<>(comboBoxInput.getValues());
						}
						default -> throw new RuntimeException("Invalid input type: " + input.getName());
				}
		}

		private Object getJComponentValue(JComponent component) {
				switch (component.getClass().getSimpleName()) {
						case "JSlider" -> {
								return ((JSlider) component).getValue();
						}
						case "JColorChooser" -> {
								Color color = ((JColorChooser) component).getColor();
								return new Pixel(color.getRed(), color.getGreen(), color.getBlue());
						}
						case "JComboBox" -> {
								return ((JComboBox<?>) component).getSelectedItem();
						}
						default -> throw new RuntimeException("Invalid input type: " + component.getClass().getSimpleName());
				}
		}

		private void applyEffect(HashMap<String, Object> options) {
				try {
						Main.img.applyEffect(this.effect, options);
				} catch (EffectOptionNotFoundException ex) {
						throw new RuntimeException(ex);
				}
				Main.updateImage(Main.img, this.uiManager);
		}
}
