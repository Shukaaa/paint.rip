package rip.shukaaa.ui.components;

import rip.shukaaa.Main;
import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.ComboBox;
import rip.shukaaa.effect.input.inputs.Slider;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.ui.UiManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.io.File;
import java.util.*;

public class UiMenuBar extends JMenuBar {
    public UiMenuBar(UiManager uiManager) {
        //*************//
        //* File Menu *//
        //*************//

        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(null);

            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile == null) {
                return;
            }

            try {
                Main.setImg(selectedFile);
                Main.backupImage = selectedFile;
                Main.updateImage(Main.getImg(Main.backupImage), uiManager);
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        fileMenu.add(open);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> {
            JDialog saveDialog = createDialog("Save Image");
            JTextField fileName = new JTextField();
            fileName.setColumns(10);
            fileName.setBorder(BorderFactory.createTitledBorder("File Name"));
            saveDialog.add(fileName);
            JComboBox<ImageFormats> fileExtension = new JComboBox<>(ImageFormats.values());
            fileExtension.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            saveDialog.add(fileExtension);

            JSpinner widthSpinner = createSpinner(Main.img.getWidth(), 1, 10000, "Width");
            saveDialog.add(widthSpinner);

            JSpinner heightSpinner = createSpinner(Main.img.getHeight(), 1, 10000, "Height");
            saveDialog.add(heightSpinner);

            JButton saveButton = new JButton("Save");
            saveDialog.add(saveButton);
            saveButton.addActionListener(e1 -> {
                Main.img.export(fileName.getText(), (ImageFormats) fileExtension.getSelectedItem(), (int) widthSpinner.getValue(), (int) heightSpinner.getValue());
                saveDialog.dispose();
            });
            saveDialog.pack();
        });
        fileMenu.add(save);

        fileMenu.addSeparator();

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            Main.deleteTempImages();
            System.exit(0);
        });
        fileMenu.add(exit);

        //*************//
        //* Edit Menu *//
        //*************//

        JMenu editMenu = new JMenu("Edit");
        this.add(editMenu);

        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(e -> {
            try {
                Main.updateImage(Main.getImg(Main.backupImage), uiManager);
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        editMenu.add(reset);

        editMenu.addSeparator();

        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(e -> {
            Main.img.undo();
            Main.updateImage(Main.img, uiManager);
        });
        editMenu.add(undo);

        JMenuItem redo = new JMenuItem("Redo");
        redo.addActionListener(e -> {
            Main.img.redo();
            Main.updateImage(Main.img, uiManager);
        });
        editMenu.add(redo);

        JMenu effectsMenu = new JMenu("Effects");
        this.add(effectsMenu);

        //**************//
        //* Image Menu *//
        //**************//

        JMenu imageMenu = new JMenu("Image");
        this.add(imageMenu);

        imageMenu.add(createLabelTitle("Properties: "));
        JMenuItem resize = new JMenuItem("Resize");
        resize.addActionListener(e -> {
            JDialog resizeDialog = createDialog("Resize Image");
            resizeDialog.add(new JLabel("Width:"));
            JSpinner widthSpinner = createSpinner(Main.img.getWidth(), 1, 10000, "Width");
            resizeDialog.add(widthSpinner);
            resizeDialog.add(new JLabel("Height:"));
            JSpinner heightSpinner = createSpinner(Main.img.getHeight(), 1, 10000, "Height");
            resizeDialog.add(heightSpinner);
            JButton apply = new JButton("Apply");
            resizeDialog.add(apply);
            apply.addActionListener(e1 -> {
                Main.img = Main.img.resizedImage((int) widthSpinner.getValue(), (int) heightSpinner.getValue());
                Main.updateImage(Main.img, uiManager);
                resizeDialog.dispose();
            });
            resizeDialog.pack();
        });
        imageMenu.add(resize);

        // Disable all menu items except for open
        for (int i = 1; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(false);
        }

        save.setEnabled(false);

        HashMap<String, Effect> effects = EffectRegister.effects;
        HashMap<EffectCategory, ArrayList<JMenuItem>> items = new HashMap<>();

        for (Map.Entry<String, Effect> entry : effects.entrySet()) {
            String name = entry.getKey();
            Effect effect = entry.getValue();

            JMenuItem item = new JMenuItem(name);
            item.addActionListener(e -> {
                JDialog dialog = createDialog("Add " + name);
                HashMap<String, JComponent> inputMap = new HashMap<>();

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

            EffectCategory category = effect.getCategory();
            if (items.containsKey(category)) {
                items.get(category).add(item);
            } else {
                ArrayList<JMenuItem> list = new ArrayList<>();
                list.add(item);
                items.put(category, list);
            }
        }

        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            ArrayList<JMenuItem> list = entry.getValue();
            list.sort(Comparator.comparing(JMenuItem::getText));
        }

        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            EffectCategory category = entry.getKey();
            ArrayList<JMenuItem> menuItems = entry.getValue();

            if (Objects.equals(category.getMainCategory(), "Image")) {
                if (imageMenu.getItemCount() != 0) {
                    imageMenu.addSeparator();
                }

                imageMenu.add(createLabelTitle(category.getSubCategory() + ": "));
                menuItems.forEach(imageMenu::add);
            }

            if (Objects.equals(category.getMainCategory(), "Effects")) {
                if (effectsMenu.getItemCount() != 0) {
                    effectsMenu.addSeparator();
                }

                effectsMenu.add(createLabelTitle(category.getSubCategory() + ": "));
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

    private JLabel createLabelTitle(String title) {
        JLabel label = new JLabel(title);
        // add bold
        label.setFont(label.getFont().deriveFont(14.0f).deriveFont(Font.ITALIC));
        label.setBorder(BorderFactory.createEmptyBorder(0, 8, 4, 0));
        return label;
    }

    private JDialog createDialog(String title) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.setTitle(title);
        dialog.setVisible(true);
        return dialog;
    }

    private JSpinner createSpinner(int value, int min, int max, String title) {
        SpinnerModel spinnerModel = new SpinnerNumberModel(value, min, max, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setBorder(BorderFactory.createTitledBorder(title));
        spinner.setUI(new BasicSpinnerUI() {
            protected Component createNextButton() {
                return null;
            }

            protected Component createPreviousButton() {
                return null;
            }
        });
        return spinner;
    }
}
