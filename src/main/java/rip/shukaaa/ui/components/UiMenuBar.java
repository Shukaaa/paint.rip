package rip.shukaaa.ui.components;

import rip.shukaaa.Main;
import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.enums.RowSlicerDirection;
import rip.shukaaa.enums.RowSlicerMode;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.ui.UiManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            // Open MEnu to ask for file extension and name
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

        //****************//
        //* Effects Menu *//
        //****************//

        JMenu effectsMenu = new JMenu("Effects");
        this.add(effectsMenu);

        effectsMenu.add(createLabelTitle("Color Effects: "));

        JMenuItem invert = new JMenuItem("Invert");
        invert.addActionListener(e -> {
            JDialog invertDialog = createDialog("Add Invert");
            invertDialog.add(new JLabel("Threshold:"));
            JSlider threshold = new JSlider(0, 256, 256);
            invertDialog.add(threshold);
            JButton apply = new JButton("Apply");
            invertDialog.add(apply);
            apply.addActionListener(e1 -> {
                try {
                    Main.img.applyEffect("invert", new HashMap<>(Map.of("threshold", threshold.getValue())));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                invertDialog.dispose();
            });
            invertDialog.pack();
            Main.updateImage(Main.img, uiManager);
        });
        effectsMenu.add(invert);

        // Add Grayscale
        JMenuItem grayscaleMenuItem = new JMenuItem("Grayscale");
        grayscaleMenuItem.addActionListener(e -> {
            JDialog grayscaleDialog = createDialog("Add Grayscale");
            grayscaleDialog.add(new JLabel("Threshold:"));
            JSlider threshold = new JSlider(0, 256, 256);
            grayscaleDialog.add(threshold);
            JButton apply = new JButton("Apply");
            grayscaleDialog.add(apply);
            apply.addActionListener(e1 -> {
                try {
                    Main.img.applyEffect("grayscale", new HashMap<>(Map.of("threshold", threshold.getValue())));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                grayscaleDialog.dispose();
            });
            grayscaleDialog.pack();
        });
        effectsMenu.add(grayscaleMenuItem);

        JMenuItem melt = new JMenuItem("Melt");
        melt.addActionListener(e -> {
            JDialog meltDialog = createDialog("Add Melt");
            meltDialog.add(new JLabel("Threshold:"));
            JSlider threshold = new JSlider(0, 256, 256);
            meltDialog.add(threshold);
            JColorChooser colorChooser = new JColorChooser();
            meltDialog.add(colorChooser);
            JButton apply = new JButton("Apply");
            meltDialog.add(apply);
            apply.addActionListener(e1 -> {
                Color color = colorChooser.getColor();
                Pixel pixel = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
                try {
                    Main.img.applyEffect("melt", new HashMap<>(Map.of("threshold", threshold.getValue(), "color", pixel)));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                meltDialog.dispose();
            });
            meltDialog.pack();
        });
        effectsMenu.add(melt);

        effectsMenu.addSeparator();
        effectsMenu.add(createLabelTitle("Distortion: "));

        JMenuItem blackAndWhite = new JMenuItem("Black and White");
        blackAndWhite.addActionListener(e -> {
            JDialog blackAndWhiteDialog = createDialog("Add Black and White");
            blackAndWhiteDialog.add(new JLabel("Threshold:"));
            JSlider threshold = new JSlider(0, 256, 256);
            blackAndWhiteDialog.add(threshold);
            JButton apply = new JButton("Apply");
            blackAndWhiteDialog.add(apply);
            apply.addActionListener(e1 -> {
                try {
                    Main.img.applyEffect("blackAndWhite", new HashMap<>(Map.of("threshold", threshold.getValue())));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                blackAndWhiteDialog.dispose();
            });
            blackAndWhiteDialog.pack();
        });
        effectsMenu.add(blackAndWhite);

        JMenuItem rowSlicer = new JMenuItem("Row Slicer");
        rowSlicer.addActionListener(e -> {
            JDialog slicerDialog = createDialog("Add Row Slicer");
            slicerDialog.add(new JLabel("Mode:"));
            JComboBox<RowSlicerMode> mode = new JComboBox<>(RowSlicerMode.values());
            slicerDialog.add(mode);
            JComboBox<RowSlicerDirection> direction = new JComboBox<>(RowSlicerDirection.values());
            slicerDialog.add(direction);
            JButton apply = new JButton("Apply");
            slicerDialog.add(apply);
            apply.addActionListener(e1 -> {
                try {
                    Main.img.applyEffect("rowSlicer", new HashMap<>(Map.of(
                            "mode", Objects.requireNonNull(mode.getSelectedItem()),
                            "direction", Objects.requireNonNull(direction.getSelectedItem()))
                    ));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                slicerDialog.dispose();
            });
            slicerDialog.pack();
        });
        effectsMenu.add(rowSlicer);

        JMenuItem shuffler = new JMenuItem("Cos Sin Shuffler");
        shuffler.addActionListener(e -> {
            JDialog shufflerDialog = createDialog("Add Shuffler");
            shufflerDialog.add(new JLabel("Modulo:"));
            JSlider modulo = new JSlider(0, 256, 100);
            shufflerDialog.add(modulo);
            JButton apply = new JButton("Apply");
            shufflerDialog.add(apply);
            apply.addActionListener(e1 -> {
                try {
                    Main.img.applyEffect("cosSinShuffler", new HashMap<>(Map.of("modulo", modulo.getValue())));
                } catch (EffectOptionNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.updateImage(Main.img, uiManager);
                shufflerDialog.dispose();
            });
            shufflerDialog.pack();
        });
        effectsMenu.add(shuffler);

        JMenuItem randomShuffler = new JMenuItem("Noise Shuffler");
        randomShuffler.addActionListener(e -> {
            try {
                Main.img.applyEffect("randomShuffler", new HashMap<>());
            } catch (EffectOptionNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.updateImage(Main.img, uiManager);
        });
        effectsMenu.add(randomShuffler);

        JMenuItem distortionFLip = new JMenuItem("Distortion Flip");
        distortionFLip.addActionListener(e -> {
            try {
                Main.img.applyEffect("distortionFlip", new HashMap<>());
            } catch (EffectOptionNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.updateImage(Main.img, uiManager);
        });
        effectsMenu.add(distortionFLip);

        //******************//
        //* Transform Menu *//
        //******************//

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

        imageMenu.addSeparator();

        imageMenu.add(createLabelTitle("Transform: "));

        JMenuItem flip = new JMenuItem("Flip");
        flip.addActionListener(e -> {
            try {
                Main.img.applyEffect("flip", new HashMap<>());
            } catch (EffectOptionNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.updateImage(Main.img, uiManager);
        });
        imageMenu.add(flip);


        // Disable all menu items except for open
        for (int i = 1; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(false);
        }

        save.setEnabled(false);
    }

    public void unlockMenu() {
        for (int i = 0; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(true);

            for (int j = 0; j < menu.getItemCount(); j++) {
                try {
                    menu.getItem(j).setEnabled(true);
                } catch (NullPointerException ignored) {
                }
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
