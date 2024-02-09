package rip.shukaaa.application.ui.logic.menu.items.file;

import rip.shukaaa.application.exceptions.ImageNotFoundException;
import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.application.keystrokes.KeyStrokeRegister;
import rip.shukaaa.application.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.io.File;
import java.util.Optional;

public class OpenMenuItem extends MenuItem {
    public OpenMenuItem() {
        super(Optional.of(KeyStrokeRegister.open));
    }

    @Override
    protected JMenuItem createItem() {
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
                ImageManager.setImg(selectedFile);
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return open;
    }
}
