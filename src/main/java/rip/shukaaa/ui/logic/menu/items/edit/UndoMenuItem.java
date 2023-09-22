package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ImageManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class UndoMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem undo = new JMenuItem("Undo");
				undo.addActionListener(e -> {
						try {
								ImageManager.undo();
						} catch (ImageNotFoundException ex) {
								throw new RuntimeException(ex);
						}
				});
				return undo;
		}
}
