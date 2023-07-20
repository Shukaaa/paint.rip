package rip.shukaaa.ui;

import rip.shukaaa.Main;
import rip.shukaaa.ui.components.UiImage;
import rip.shukaaa.ui.components.UiMenuBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class UiManager {
    JFrame frame;
    UiImage imageLabel;
    JLabel label;
    UiMenuBar menuBar;

    public UiManager(String title) {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 500);

        // set icon, it's located in src\main\resources\icon.png
        URL image = getClass().getResource("/paintrip.png");
        assert image != null;
        ImageIcon icon = new ImageIcon(image);
        this.frame.setIconImage(icon.getImage());

        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));

        this.imageLabel = new UiImage(image);
        this.frame.add(this.imageLabel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.deleteTempImages();
            }
        });

        // Add File Menu
        this.menuBar = new UiMenuBar(this);
        this.frame.setJMenuBar(menuBar);

        this.label = new JLabel("Select an image to start (File > Open)");
        this.label.setFont(label.getFont().deriveFont(20f));
        this.label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.frame.add(label);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void updateImage(String path) {
        if (label != null) {
            this.frame.remove(label);
            this.menuBar.unlockMenu();
        }

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
