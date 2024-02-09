package rip.shukaaa.application.ui.logic.menu.bar;

import javax.swing.*;

public class MenuFactory {
    public static JMenu createMenu(String name, JMenuItem[][] itemLists) {
        JMenu menu = new JMenu(name);

        for (int i = 0; i < itemLists.length; i++) {
            for (JMenuItem jMenuItem : itemLists[i]) {
                menu.add(jMenuItem);
            }

            if (i != itemLists.length - 1) {
                menu.addSeparator();
            }
        }

        return menu;
    }
}
