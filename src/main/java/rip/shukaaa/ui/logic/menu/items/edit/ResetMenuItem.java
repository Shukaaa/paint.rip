package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ImageManager;
import rip.shukaaa.keystrokes.KeyStrokeRegister;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.util.Optional;

public class ResetMenuItem extends MenuItem {
    public ResetMenuItem() {
        super(Optional.of(KeyStrokeRegister.reset));
    }

    @Override
    protected JMenuItem createItem() {
        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(e -> {
            try {
                ImageManager.resetImage();
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return reset;
    }
}
