package rip.shukaaa.application.ui.logic.menu.builder;

import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.application.register.EffectRegister;
import rip.shukaaa.api.effect.category.EffectCategory;
import rip.shukaaa.application.stores.PluginStore;
import rip.shukaaa.application.ui.logic.menu.items.effects.EffectsMenuItem;

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

            addItemToItemsMap(items, category, item);
        }

        ArrayList<Effect> pluginEffects = PluginStore.pluginEffects;
        for (Effect pluginEffect : pluginEffects) {
            String name = pluginEffect.getEffectName();

            System.out.println("Adding plugin effect: " + name);

            JMenuItem item = new EffectsMenuItem(name, pluginEffect).getItem();
            EffectCategory category = pluginEffect.getCategory();

            addItemToItemsMap(items, category, item);
        }

        this.sortMenuItemsAlphabetically(items);
        return createSortedTreeMap(items);
    }

    private void addItemToItemsMap(HashMap<EffectCategory, ArrayList<JMenuItem>> items, EffectCategory category, JMenuItem item) {
        if (items.containsKey(category)) {
            items.get(category).add(item);
            return;
        }

        ArrayList<JMenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(item);
        items.put(category, menuItemList);
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
