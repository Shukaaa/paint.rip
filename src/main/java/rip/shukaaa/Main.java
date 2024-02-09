package rip.shukaaa;

import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.application.ui.UiManager;
import rip.shukaaa.plugin.PluginLoader;

public class Main {
    public static void main(String[] args) {
        PluginLoader.loadEffects();
        ImageManager.uiManager = new UiManager("paint.rip");
    }
}