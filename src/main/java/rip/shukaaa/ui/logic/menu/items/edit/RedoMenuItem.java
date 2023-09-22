package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.image.ImageManager;
import rip.shukaaa.stores.DataStore;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class RedoMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem redo = new JMenuItem("Redo");
				redo.addActionListener(e -> {
						DataStore.getImg().redo();
						ImageManager.updateImage(DataStore.getImg());
				});
				return redo;
		}
}
