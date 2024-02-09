package rip.shukaaa.application.ui.logic.menu.items.edit;

import rip.shukaaa.application.exceptions.ImageNotFoundException;
import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.application.keystrokes.KeyStrokeRegister;
import rip.shukaaa.application.ui.logic.menu.items.MenuItem;

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
