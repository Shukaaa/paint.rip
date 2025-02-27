package rip.shukaaa.application.ui.logic.menu.bar;

import rip.shukaaa.application.ui.logic.menu.items.edit.RedoMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.edit.ResetMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.edit.UndoMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.file.ExitMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.file.OpenMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.file.SaveMenuItem;
import rip.shukaaa.application.ui.logic.menu.items.image.ResizeMenuItem;

import javax.swing.*;

public final class MenuRegister {
    public static final JMenu[] menus = {
            MenuFactory.createMenu("File", new JMenuItem[][]{
                    {new OpenMenuItem().getItem(), new SaveMenuItem().getItem()},
                    {new ExitMenuItem().getItem()}
            }),
            MenuFactory.createMenu("Edit", new JMenuItem[][]{
                    {new ResetMenuItem().getItem()},
                    {new UndoMenuItem().getItem(), new RedoMenuItem().getItem()}
            }),
            // Effects get added dynamically, so we just need to create the menu here
            MenuFactory.createMenu("Effects", new JMenuItem[][]{
                    {}
            }),
            MenuFactory.createMenu("Image", new JMenuItem[][]{
                    {new ResizeMenuItem().getItem()}
            })
    };
}
