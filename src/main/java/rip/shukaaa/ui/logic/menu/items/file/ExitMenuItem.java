package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.image.ImageManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class ExitMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem exit = new JMenuItem("Exit");
				exit.addActionListener(e -> {
						ImageManager.deleteTempImages();
						System.exit(0);
				});
				return exit;
		}
}
