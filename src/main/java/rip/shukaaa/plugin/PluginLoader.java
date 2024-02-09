package rip.shukaaa.plugin;

import rip.shukaaa.application.stores.PluginStore;
import rip.shukaaa.plugin.api.PluginEffect;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PluginLoader {
    private static final String PLUGINS_DIR = "modules/";

    public static void loadEffects() {
        File effectDir = new File(PLUGINS_DIR + "effects/");
        ArrayList<PluginEffect> plugins = new ArrayList<>();

        if (effectDir.exists() && effectDir.isDirectory()) {
            loadPluginsFromFiles(effectDir, plugins);
        }

        PluginStore.addPluginEffects(plugins);
    }

    private static void loadPluginsFromFiles(File effectDir, List<PluginEffect> plugins) {
        File[] files = effectDir.listFiles();
        if (files != null) {
            for (File jar : files) {
                loadPluginsFromFile(jar, plugins);
            }
        }
    }

    private static void loadPluginsFromFile(File jar, List<PluginEffect> plugins) {
        try {
            URL jarURL = jar.toURI().toURL();

            try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL})) {
                loadPluginsFromJarFile(classLoader, plugins);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadPluginsFromJarFile(URLClassLoader classLoader, List<PluginEffect> plugins) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Properties properties = new Properties();
        properties.load(classLoader.getResourceAsStream("module.properties"));
        String classNames = properties.getProperty("effect-classes");
        String[] classNameList = classNames.split(",");

        for (String className : classNameList) {
            loadPluginFromClassName(className, classLoader, plugins);
        }
    }

    private static void loadPluginFromClassName(String className, URLClassLoader classLoader, List<PluginEffect> plugins) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> loadedClass = classLoader.loadClass(className);

        if (PluginEffect.class.isAssignableFrom(loadedClass)) {
            PluginEffect plugin = (PluginEffect) loadedClass.getDeclaredConstructor().newInstance();
            plugins.add(plugin);
        }
    }
}
