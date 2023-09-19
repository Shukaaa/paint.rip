package rip.shukaaa.ui.components;

import rip.shukaaa.Main;
import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.ComboBox;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.edit.RedoMenuItem;
import rip.shukaaa.ui.logic.menu.items.edit.ResetMenuItem;
import rip.shukaaa.ui.logic.menu.items.edit.UndoMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.ExitMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.OpenMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.SaveMenuItem;
import rip.shukaaa.ui.logic.menu.items.image.ResizeMenuItem;
import rip.shukaaa.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class UiMenuBar extends JMenuBar {
    public UiMenuBar(UiManager uiManager) {
        //*************//
        //* File Menu *//
        //*************//

        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);

        JMenuItem open = new OpenMenuItem(uiManager).getItem();
        JMenuItem save = new SaveMenuItem().getItem();
        JMenuItem exit = new ExitMenuItem().getItem();

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        //*************//
        //* Edit Menu *//
        //*************//

        JMenu editMenu = new JMenu("Edit");
        this.add(editMenu);

        JMenuItem reset = new ResetMenuItem(uiManager).getItem();
        JMenuItem undo = new UndoMenuItem(uiManager).getItem();
        JMenuItem redo = new RedoMenuItem(uiManager).getItem();

        editMenu.add(reset);
        editMenu.addSeparator();
        editMenu.add(undo);
        editMenu.add(redo);

        //****************//
        //* Effects Menu *//
        //****************//

        // Effects get added dynamically, so we just need to create the menu here
        JMenu effectsMenu = new JMenu("Effects");
        this.add(effectsMenu);

        //***************//
        //* Image Menu *//
        //***************//

        JMenu imageMenu = new JMenu("Image");
        this.add(imageMenu);

        JLabel propertiesLabel = UiUtils.createLabelTitle("Properties: ");
        JMenuItem resize = new ResizeMenuItem(uiManager).getItem();

        imageMenu.add(propertiesLabel);
        imageMenu.add(resize);

        //***************//

        this.disableMenu(save);


        HashMap<String, Effect> effects = EffectRegister.effects;
        HashMap<EffectCategory, ArrayList<JMenuItem>> items = new HashMap<>();

        // Create menu items for each effect and apply a dialog for each effect
        for (Map.Entry<String, Effect> entry : effects.entrySet()) {
            String name = entry.getKey();
            Effect effect = entry.getValue();

            JMenuItem item = new JMenuItem(name);
            item.addActionListener(e -> {
                // If the effect has no inputs, apply it and return (no dialog needed)
                if (effect.getEffectInputs().length == 0) {
                    try {
                        Main.img.applyEffect(effect, new HashMap<>());
                    } catch (EffectOptionNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.updateImage(Main.img, uiManager);
                    return;
                }

                JDialog dialog = UiUtils.createDialog("Add " + name);
                HashMap<String, JComponent> inputMap = new HashMap<>();

                // Create dialog inputs for each effect input
                for (EffectInput input : effect.getEffectInputs()) {
                    dialog.add(new JLabel(input.getTitle() + ":"));

                    switch (input.getName()) {
                        case "slider" -> {
                            Slider sliderInput = (Slider) input;
                            JSlider slider = new JSlider(sliderInput.getMin(), sliderInput.getMax(), sliderInput.getDefaultValue());
                            dialog.add(slider);
                            inputMap.put(input.getTitle(), slider);
                        }
                        case "colorchooser" -> {
                            JColorChooser colorChooser = new JColorChooser();
                            dialog.add(colorChooser);
                            inputMap.put(input.getTitle(), colorChooser);
                        }
                        case "combobox" -> {
                            ComboBox<Object> comboBoxInput = (ComboBox<Object>) input;
                            JComboBox<Object> comboBox = new JComboBox<>(comboBoxInput.getValues());
                            dialog.add(comboBox);
                            inputMap.put(input.getTitle(), comboBox);
                        }
                    }
                }

                JButton apply = new JButton("Apply");
                dialog.add(apply);

                // Apply the effect with the given options
                apply.addActionListener(e1 -> {
                    HashMap<String, Object> options = new HashMap<>();
                    for (Map.Entry<String, JComponent> entry1 : inputMap.entrySet()) {
                        String key = entry1.getKey();
                        JComponent value = entry1.getValue();

                        switch (value.getClass().getSimpleName()) {
                            case "JSlider" -> options.put(key, ((JSlider) value).getValue());
                            case "JColorChooser" -> {
                                Color color = ((JColorChooser) value).getColor();
                                Pixel pixel = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
                                options.put(key, pixel);
                            }
                            case "JComboBox" -> options.put(key, ((JComboBox<?>) value).getSelectedItem());
                        }
                    }

                    try {
                        Main.img.applyEffect(effect, options);
                    } catch (EffectOptionNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.updateImage(Main.img, uiManager);
                    dialog.dispose();
                });

                dialog.pack();
            });

            // Separate the menu items into categories to sort them later
            EffectCategory category = effect.getCategory();
            if (items.containsKey(category)) {
                items.get(category).add(item);
            } else {
                ArrayList<JMenuItem> list = new ArrayList<>();
                list.add(item);
                items.put(category, list);
            }
        }

        // Sort the menu items by name for each category
        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            ArrayList<JMenuItem> list = entry.getValue();
            list.sort(Comparator.comparing(JMenuItem::getText));
        }

        // Sort the subcategories by name
        TreeMap<EffectCategory, ArrayList<JMenuItem>> sortedItems = new TreeMap<>(Comparator.comparing(EffectCategory::getSubCategory));
        sortedItems.putAll(items);

        // Add the menu items to the menu
        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : sortedItems.entrySet()) {
            EffectCategory category = entry.getKey();
            ArrayList<JMenuItem> menuItems = entry.getValue();

            if (Objects.equals(category.getMainCategory(), "Image")) {
                if (imageMenu.getItemCount() != 0) {
                    imageMenu.addSeparator();
                }

                imageMenu.add(UiUtils.createLabelTitle(category.getSubCategory() + ": "));
                menuItems.forEach(imageMenu::add);
            }

            if (Objects.equals(category.getMainCategory(), "Effects")) {
                if (effectsMenu.getItemCount() != 0) {
                    effectsMenu.addSeparator();
                }

                effectsMenu.add(UiUtils.createLabelTitle(category.getSubCategory() + ": "));
                menuItems.forEach(effectsMenu::add);
            }
        }
    }

    public void unlockMenu() {
        for (int i = 0; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(true);

            for (int j = 0; j < menu.getItemCount(); j++) {
                try {
                    menu.getItem(j).setEnabled(true);
                } catch (NullPointerException ignored) {}
            }
        }
    }

    private void disableMenu(JMenuItem save) {
        for (int i = 1; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(false);
        }
        save.setEnabled(false);
    }
}
