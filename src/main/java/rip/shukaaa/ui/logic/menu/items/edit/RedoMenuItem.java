package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.Main;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class RedoMenuItem extends MenuItem {
		private final UiManager uiManager;

		public RedoMenuItem(UiManager uiManager) {
				this.uiManager = uiManager;
		}

		@Override
		protected JMenuItem createItem() {
				JMenuItem redo = new JMenuItem("Redo");
				redo.addActionListener(e -> {
						Main.img.redo();
						Main.updateImage(Main.img, uiManager);
				});
				return redo;
		}
}