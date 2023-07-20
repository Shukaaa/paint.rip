package rip.shukaaa.ui.components;

import javax.swing.*;
import java.net.URL;

public class UiImage extends JLabel {
    public UiImage(URL image) {
        super(new ImageIcon(image));
    }

    public void updateImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.setIcon(icon);
    }
}
