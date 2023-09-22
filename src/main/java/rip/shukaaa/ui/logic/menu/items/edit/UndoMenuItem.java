package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.image.ImageManager;
import rip.shukaaa.stores.DataStore;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class UndoMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem undo = new JMenuItem("Undo");
				undo.addActionListener(e -> {
						DataStore.getImg().undo();
						ImageManager.updateImage(DataStore.getImg());
				});
				return undo;
		}
}
