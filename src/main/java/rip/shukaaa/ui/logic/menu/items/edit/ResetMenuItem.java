package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.Main;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class ResetMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem reset = new JMenuItem("Reset");
				reset.addActionListener(e -> {
						try {
								Main.resetImage();
						} catch (ImageNotFoundException ex) {
								throw new RuntimeException(ex);
						}
				});
				return reset;
		}
}
