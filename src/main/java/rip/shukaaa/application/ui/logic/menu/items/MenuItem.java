package rip.shukaaa.application.ui.logic.menu.items;

import rip.shukaaa.application.keystrokes.KeyStrokeRegister;

import javax.swing.*;
import java.util.Optional;

public abstract class MenuItem {
    JMenuItem item;
    private final Optional<KeyStroke> keyStroke;

    protected MenuItem(Optional<KeyStroke> keyStroke) {
        this.keyStroke = keyStroke;
    }

    protected abstract JMenuItem createItem();

    public JMenuItem getItem() {
        if (item == null) {
            item = createItem();
            keyStroke.ifPresent(stroke -> item.setAccelerator(stroke));
        }

        return item;
    }
}
