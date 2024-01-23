package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.image.ImageManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.util.Optional;

public class ExitMenuItem extends MenuItem {
    public ExitMenuItem() {
        super(Optional.empty());
    }

    @Override
    protected JMenuItem createItem() {
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            ImageManager.deleteTempImages();
            System.exit(0);
        });
        return exit;
    }
}
