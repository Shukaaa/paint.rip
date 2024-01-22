package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ImageManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class RedoMenuItem extends MenuItem {
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
