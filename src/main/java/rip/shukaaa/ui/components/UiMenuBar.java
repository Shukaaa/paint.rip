package rip.shukaaa.ui.components;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.keystrokes.KeyStrokeRegister;
import rip.shukaaa.ui.logic.menu.bar.MenuRegister;
import rip.shukaaa.ui.logic.menu.items.edit.RedoMenuItem;
import rip.shukaaa.ui.logic.menu.items.edit.ResetMenuItem;
import rip.shukaaa.ui.logic.menu.items.edit.UndoMenuItem;
import rip.shukaaa.ui.logic.menu.items.effects.EffectsMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.ExitMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.OpenMenuItem;
import rip.shukaaa.ui.logic.menu.items.file.SaveMenuItem;
import rip.shukaaa.ui.logic.menu.items.image.ResizeMenuItem;
import rip.shukaaa.utils.UiUtils;

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

        HashMap<String, Effect> effects = EffectRegister.effects;
        HashMap<EffectCategory, ArrayList<JMenuItem>> items = new HashMap<>();

        // Create menu items for each effect and apply a dialog for each effect
        for (Map.Entry<String, Effect> effectEntry : effects.entrySet()) {
            String name = effectEntry.getKey();
            Effect effect = effectEntry.getValue();

            JMenuItem item = new EffectsMenuItem(name, effect).getItem();
            EffectCategory category = effect.getCategory();

            if (items.containsKey(category)) {
                items.get(category).add(item);
                continue;
            }

            ArrayList<JMenuItem> menuItemList = new ArrayList<>();
            menuItemList.add(item);
            items.put(category, menuItemList);
        }

        // Sort the menu items alphabetically
        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            ArrayList<JMenuItem> list = entry.getValue();
            list.sort(Comparator.comparing(JMenuItem::getText));
        }

        // Sort the categories alphabetically
        TreeMap<EffectCategory, ArrayList<JMenuItem>> sortedItems
                = new TreeMap<>(Comparator.comparing(EffectCategory::getSubCategory));
        sortedItems.putAll(items);

        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : sortedItems.entrySet()) {
            EffectCategory category = entry.getKey();
            ArrayList<JMenuItem> menuItems = entry.getValue();

            JMenu imageMenu = this.getMenu(3);
            JMenu effectsMenu = this.getMenu(2);

            this.addMenuItems(menuItems, category, imageMenu, effectsMenu);
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

    private void addMenuItems(ArrayList<JMenuItem> menuItems, EffectCategory category, JMenu imageMenu, JMenu effectsMenu) {
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
