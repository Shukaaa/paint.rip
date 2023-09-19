package rip.shukaaa.ui.logic.menu.items.edit;

import rip.shukaaa.Main;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class ResetMenuItem extends MenuItem {
		private final UiManager uiManager;

		public ResetMenuItem(UiManager uiManager) {
				this.uiManager = uiManager;
		}

		@Override
		protected JMenuItem createItem() {
				JMenuItem reset = new JMenuItem("Reset");
				reset.addActionListener(e -> {
						try {
								Main.updateImage(Main.getImg(Main.backupImage), uiManager);
						} catch (ImageNotFoundException ex) {
								throw new RuntimeException(ex);
						}
				});
				return reset;
		}
}
