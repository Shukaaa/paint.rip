package rip.shukaaa.application.ui.components;

import rip.shukaaa.api.effect.category.EffectCategory;
import rip.shukaaa.application.ui.logic.menu.bar.MenuRegister;
import rip.shukaaa.application.ui.logic.menu.builder.EffectMenuItemBuilder;
import rip.shukaaa.application.utils.UiUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class UiMenuBar extends JMenuBar {
    JFrame frame;

    public UiMenuBar(JFrame frame) {
        this.frame = frame;

        JMenu[] menus = MenuRegister.menus;
        for (JMenu menu : menus) {
            this.add(menu);
        }

        JMenuItem save = this.getMenu(0).getItem(1);
        this.disableMenu(save);


        TreeMap<EffectCategory, ArrayList<JMenuItem>> items = new EffectMenuItemBuilder().getItemTree();
        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            EffectCategory category = entry.getKey();
            ArrayList<JMenuItem> menuItems = entry.getValue();

            JMenu imageMenu = this.getMenu(3);
            JMenu effectsMenu = this.getMenu(2);

            this.addEffectMenuItems(menuItems, category, imageMenu, effectsMenu);
        }
    }

    public void unlockMenu() {
        for (int i = 0; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(true);

            for (int j = 0; j < menu.getItemCount(); j++) {
                try {
                    menu.getItem(j).setEnabled(true);
                } catch (NullPointerException ignored) {
                }
            }
        }

        this.getMenu(1).getItem(2).setEnabled(false);
        this.getMenu(1).getItem(3).setEnabled(false);
    }

    public void enableUndo() {
        this.getMenu(1).getItem(2).setEnabled(true);
    }

    public void enableRedo() {
        this.getMenu(1).getItem(3).setEnabled(true);
    }

    public void disableUndo() {
        this.getMenu(1).getItem(2).setEnabled(false);
    }

    public void disableRedo() {
        this.getMenu(1).getItem(3).setEnabled(false);
    }

    private void disableMenu(JMenuItem save) {
        for (int i = 1; i < this.getMenuCount(); i++) {
            JMenu menu = this.getMenu(i);
            menu.setEnabled(false);
        }
        save.setEnabled(false);
    }

    private void addEffectMenuItems(ArrayList<JMenuItem> menuItems, EffectCategory category, JMenu imageMenu, JMenu effectsMenu) {
        if (Objects.equals(category.getMainCategory(), "Image")) {
            if (imageMenu.getItemCount() != 0) {
                imageMenu.addSeparator();
            }

            imageMenu.add(UiUtils.createLabelTitle(category.getSubCategory() + ": "));
            menuItems.forEach(imageMenu::add);
        }

        if (Objects.equals(category.getMainCategory(), "Effects")) {
            JMenuItem subcategory = new JMenuItem(category.getSubCategory());
            JPopupMenu popupMenu = new JPopupMenu();

            for (JMenuItem menuItem : menuItems) {
                menuItem.addActionListener(e -> effectsMenu.setPopupMenuVisible(false));
                popupMenu.add(menuItem);
            }

            subcategory.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    popupMenu.show(subcategory, subcategory.getWidth(), -3);
                    effectsMenu.setPopupMenuVisible(true);
                }
            });

            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!popupMenu.isVisible()) {
                        popupMenu.setVisible(false);
                    }

                    if (!effectsMenu.isSelected()) {
                        effectsMenu.setPopupMenuVisible(false);
                    }
                }
            });

            effectsMenu.add(subcategory);
        }
    }
}
