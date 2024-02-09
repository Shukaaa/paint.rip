package rip.shukaaa.application.ui.logic.menu.builder;

import rip.shukaaa.application.effect.effects.Effect;
import rip.shukaaa.application.effect.effects.EffectRegister;
import rip.shukaaa.application.effect.category.EffectCategory;
import rip.shukaaa.application.effect.effects.PluginEffectWrapper;
import rip.shukaaa.application.stores.PluginStore;
import rip.shukaaa.application.ui.logic.menu.items.effects.EffectsMenuItem;
import rip.shukaaa.plugin.api.PluginEffect;

import javax.swing.*;
import java.awt.image.BufferedImage;
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

        ArrayList<PluginEffect> pluginEffects = PluginStore.pluginEffects;
        for (PluginEffect pluginEffect : pluginEffects) {
            String name = pluginEffect.getEffectName();
            Effect effect = new PluginEffectWrapper(pluginEffect);

            System.out.println("Adding plugin effect: " + name);

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
