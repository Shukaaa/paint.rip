package rip.shukaaa.ui.components;

import javax.swing.*;

public class UiImage extends JLabel {
    public UiImage() {
        super(new ImageIcon());
    }

    public void updateImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.setIcon(icon);
    }
}
