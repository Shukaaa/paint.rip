package rip.shukaaa.ui.components;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.enums.EffectCategory;
import rip.shukaaa.ui.UiManager;
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
import java.util.*;

public class UiMenuBar extends JMenuBar {
		public UiMenuBar(UiManager uiManager) {
				//*************//
				//* File Menu *//
				//*************//

				JMenu fileMenu = new JMenu("File");
				this.add(fileMenu);

				JMenuItem open = new OpenMenuItem().getItem();
				JMenuItem save = new SaveMenuItem().getItem();
				JMenuItem exit = new ExitMenuItem().getItem();

				fileMenu.add(open);
				fileMenu.add(save);
				fileMenu.addSeparator();
				fileMenu.add(exit);

				//*************//
				//* Edit Menu *//
				//*************//

				JMenu editMenu = new JMenu("Edit");
				this.add(editMenu);

				JMenuItem reset = new ResetMenuItem().getItem();
				JMenuItem undo = new UndoMenuItem(uiManager).getItem();
				JMenuItem redo = new RedoMenuItem(uiManager).getItem();

				editMenu.add(reset);
				editMenu.addSeparator();
				editMenu.add(undo);
				editMenu.add(redo);

				//****************//
				//* Effects Menu *//
				//****************//

				// Effects get added dynamically, so we just need to create the menu here
				JMenu effectsMenu = new JMenu("Effects");
				this.add(effectsMenu);

				//***************//
				//* Image Menu *//
				//***************//

				JMenu imageMenu = new JMenu("Image");
				this.add(imageMenu);

				JLabel propertiesLabel = UiUtils.createLabelTitle("Properties: ");
				JMenuItem resize = new ResizeMenuItem(uiManager).getItem();

				imageMenu.add(propertiesLabel);
				imageMenu.add(resize);

				//***************//

				this.disableMenu(save);

				HashMap<String, Effect> effects = EffectRegister.effects;
				HashMap<EffectCategory, ArrayList<JMenuItem>> items = new HashMap<>();

				// Create menu items for each effect and apply a dialog for each effect
				for (Map.Entry<String, Effect> effectEntry : effects.entrySet()) {
						String name = effectEntry.getKey();
						Effect effect = effectEntry.getValue();

						JMenuItem item = new EffectsMenuItem(name, effect, uiManager).getItem();
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
						if (effectsMenu.getItemCount() != 0) {
								effectsMenu.addSeparator();
						}

						effectsMenu.add(UiUtils.createLabelTitle(category.getSubCategory() + ": "));
						menuItems.forEach(effectsMenu::add);
				}
		}
}
