package rip.shukaaa.application.stores;

import rip.shukaaa.api.PluginEffect;
import java.util.ArrayList;

public class PluginStore {
    public static ArrayList<PluginEffect> pluginEffects = new ArrayList<>();

    public static void addPluginEffects(ArrayList<PluginEffect> pluginEffects) {
        PluginStore.pluginEffects.addAll(pluginEffects);
    }
}
