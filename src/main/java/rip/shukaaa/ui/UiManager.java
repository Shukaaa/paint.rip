package rip.shukaaa.ui;

import rip.shukaaa.Main;
import rip.shukaaa.ui.components.UiImage;
import rip.shukaaa.ui.components.UiMenuBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UiManager {
    JFrame frame;
    UiImage imageLabel;
    JLabel label;

    public UiManager(String title) {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 500);

        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));

        this.imageLabel = new UiImage();
        this.frame.add(this.imageLabel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.deleteTempImages();
            }
        });

        // Add File Menu
        UiMenuBar menuBar = new UiMenuBar(this);
        this.frame.setJMenuBar(menuBar);

        this.label = new JLabel("Select an image to start (File > Open)");
        this.label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.label.setFont(label.getFont().deriveFont(20f));
        this.label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.frame.add(label);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void updateImage(String path) {
        if (label != null) this.frame.remove(label);

        this.imageLabel.updateImage(path);
        this.update();
    }

    public void update() {
        this.frame.invalidate();
        this.frame.validate();
        this.frame.repaint();
        this.frame.pack();
    }
}