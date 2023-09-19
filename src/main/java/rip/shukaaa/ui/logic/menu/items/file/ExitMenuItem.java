package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.Main;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;

public class ExitMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem exit = new JMenuItem("Exit");
				exit.addActionListener(e -> {
						Main.deleteTempImages();
						System.exit(0);
				});
				return exit;
		}
}
