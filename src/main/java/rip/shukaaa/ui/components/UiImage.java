package rip.shukaaa.ui.components;

import javax.swing.*;
import java.net.URL;

public class UiImage extends JLabel {
    public UiImage(URL image) {
        super(new ImageIcon(image));
    }

    public void updateImage(String path) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() > 1900 || icon.getIconHeight() > 1000) {
            if (icon.getIconWidth() > icon.getIconHeight()) {
                icon = new ImageIcon(icon.getImage().getScaledInstance(1900, -1, java.awt.Image.SCALE_SMOOTH));
            } else {
                icon = new ImageIcon(icon.getImage().getScaledInstance(-1, 1000, java.awt.Image.SCALE_SMOOTH));
            }
        }

        this.setIcon(icon);
    }
}
