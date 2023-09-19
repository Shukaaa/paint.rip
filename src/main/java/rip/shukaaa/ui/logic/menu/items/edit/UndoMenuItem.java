package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.Main;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class UndoMenuItem extends MenuItem {
		private final UiManager uiManager;

		public UndoMenuItem(UiManager uiManager) {
				this.uiManager = uiManager;
		}

		@Override
		protected JMenuItem createItem() {
				JMenuItem undo = new JMenuItem("Undo");
				undo.addActionListener(e -> {
						Main.img.undo();
						Main.updateImage(Main.img, uiManager);
				});
				return undo;
		}
}
