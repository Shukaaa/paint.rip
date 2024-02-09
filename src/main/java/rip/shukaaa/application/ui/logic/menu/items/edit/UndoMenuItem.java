package rip.shukaaa.application.ui.logic.menu.items.edit;

import rip.shukaaa.application.exceptions.ImageNotFoundException;
import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.application.keystrokes.KeyStrokeRegister;
import rip.shukaaa.application.ui.logic.menu.items.MenuItem;

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
