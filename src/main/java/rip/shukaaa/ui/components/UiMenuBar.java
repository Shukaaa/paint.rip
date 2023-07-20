package rip.shukaaa.ui.components;

import rip.shukaaa.Main;
import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.ui.UiManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.io.File;

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
            Main.updateImage(Main.originalImg, uiManager);
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
                Main.img.invert(threshold.getValue());
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
                Main.img.grayscale(threshold.getValue());
                Main.updateImage(Main.img, uiManager);
                grayscaleDialog.dispose();
            });
            grayscaleDialog.pack();
        });
        effectsMenu.add(grayscaleMenuItem);

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
                Main.img.blackAndWhite(threshold.getValue());
                Main.updateImage(Main.img, uiManager);
                blackAndWhiteDialog.dispose();
            });
            blackAndWhiteDialog.pack();
        });
        effectsMenu.add(blackAndWhite);
    }

    private JLabel createLabelTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(label.getFont().deriveFont(14.0f));
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
