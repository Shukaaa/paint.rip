package rip.shukaaa.ui.logic.menu.items;

import javax.swing.*;

public abstract class MenuItem {
		JMenuItem item;

		protected abstract JMenuItem createItem();

		public JMenuItem getItem() {
				if (item == null) {
						item = createItem();
				}

				return item;
		}
}
