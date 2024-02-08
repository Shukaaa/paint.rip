package rip.shukaaa.ui.logic.menu.builder;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.ui.logic.menu.items.effects.EffectsMenuItem;

import javax.swing.*;
import java.util.*;

public class EffectMenuItemBuilder {
    private final HashMap<String, Effect> effects;
    private final TreeMap<EffectCategory, ArrayList<JMenuItem>> itemTreeMap;

    public EffectMenuItemBuilder() {
        this.effects = EffectRegister.effects;
        this.itemTreeMap = this.createMenuItems();
    }

    private TreeMap<EffectCategory, ArrayList<JMenuItem>> createMenuItems() {
        HashMap<EffectCategory, ArrayList<JMenuItem>> items = new HashMap<>();

        for (Map.Entry<String, Effect> effectEntry : this.effects.entrySet()) {
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

        this.sortMenuItemsAlphabetically(items);
        return createSortedTreeMap(items);
    }

    private void sortMenuItemsAlphabetically(HashMap<EffectCategory, ArrayList<JMenuItem>> items) {
        for (Map.Entry<EffectCategory, ArrayList<JMenuItem>> entry : items.entrySet()) {
            ArrayList<JMenuItem> list = entry.getValue();
            list.sort(Comparator.comparing(JMenuItem::getText));
        }
    }

    private TreeMap<EffectCategory, ArrayList<JMenuItem>> createSortedTreeMap(HashMap<EffectCategory, ArrayList<JMenuItem>> items) {
        TreeMap<EffectCategory, ArrayList<JMenuItem>> sortedItemTree = new TreeMap<>(Comparator.comparing(EffectCategory::getSubCategory));
        sortedItemTree.putAll(items);
        return sortedItemTree;
    }

    public TreeMap<EffectCategory, ArrayList<JMenuItem>> getItemTree() {
        return this.itemTreeMap;
    }
}
