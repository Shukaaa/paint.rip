package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ImageManager;
import rip.shukaaa.keystrokes.KeyStrokeRegister;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.util.Optional;

public class UndoMenuItem extends MenuItem {
    public UndoMenuItem() {
        super(Optional.of(KeyStrokeRegister.undo));
    }

    @Override
    protected JMenuItem createItem() {
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(e -> {
            try {
                ImageManager.undo();
            } catch (ImageNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return undo;
    }
}
