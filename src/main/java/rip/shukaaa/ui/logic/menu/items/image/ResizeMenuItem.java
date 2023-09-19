package rip.shukaaa.ui.logic.menu.items.image;

import rip.shukaaa.Main;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;
import rip.shukaaa.utils.UiUtils;

import javax.swing.*;

public class ResizeMenuItem extends MenuItem {
		private final UiManager uiManager;

		public ResizeMenuItem(UiManager uiManager) {
				this.uiManager = uiManager;
		}

		@Override
		protected JMenuItem createItem() {
				JMenuItem resize = new JMenuItem("Resize");
				resize.addActionListener(e -> {
						JDialog resizeDialog = UiUtils.createDialog("Resize Image");
						resizeDialog.add(new JLabel("Width:"));
						JSpinner widthSpinner = UiUtils.createSpinner(Main.img.getWidth(), 1, 10000, "Width");
						resizeDialog.add(widthSpinner);
						resizeDialog.add(new JLabel("Height:"));
						JSpinner heightSpinner = UiUtils.createSpinner(Main.img.getHeight(), 1, 10000, "Height");
						resizeDialog.add(heightSpinner);
						JButton apply = new JButton("Apply");
						resizeDialog.add(apply);
						apply.addActionListener(e1 -> {
								Main.img = Main.img.resizedImage((int) widthSpinner.getValue(), (int) heightSpinner.getValue());
								Main.updateImage(Main.img, uiManager);
								resizeDialog.dispose();
						});
						resizeDialog.pack();
				});
				return resize;
		}
}
