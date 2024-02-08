package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ImageManager;
import rip.shukaaa.keystrokes.KeyStrokeRegister;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.util.Optional;

public class RedoMenuItem extends MenuItem {
    public RedoMenuItem() {
        super(Optional.of(KeyStrokeRegister.redo));
    }

    @Override
    protected JMenuItem createItem() {
        JMenuItem redo = new JMenuItem("Redo");
        redo.addActionListener(e -> {
            try {
                ImageManager.redo();
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return redo;
    }
}
