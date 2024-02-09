package rip.shukaaa.application.stores;

import rip.shukaaa.api.effect.effects.Effect;

import java.util.ArrayList;

public class PluginStore {
    public static ArrayList<Effect> pluginEffects = new ArrayList<>();

    public static void addPluginEffects(ArrayList<Effect> pluginEffects) {
        PluginStore.pluginEffects.addAll(pluginEffects);
    }
}
