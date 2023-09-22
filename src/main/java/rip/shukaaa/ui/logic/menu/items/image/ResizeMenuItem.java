package rip.shukaaa.ui.logic.menu.items.image;

import rip.shukaaa.Main;
import rip.shukaaa.image.ShukaaaImage;
import rip.shukaaa.stores.DataStore;
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
						JSpinner widthSpinner = UiUtils.createSpinner(DataStore.getImg().getWidth(), 1, 10000, "Width");
						resizeDialog.add(widthSpinner);
						resizeDialog.add(new JLabel("Height:"));
						JSpinner heightSpinner = UiUtils.createSpinner(DataStore.getImg().getHeight(), 1, 10000, "Height");
						resizeDialog.add(heightSpinner);
						JButton apply = new JButton("Apply");
						resizeDialog.add(apply);
						apply.addActionListener(e1 -> {
								ShukaaaImage img = DataStore.getImg().resizedImage((int) widthSpinner.getValue(), (int) heightSpinner.getValue());
								Main.updateImage(img, uiManager);
								resizeDialog.dispose();
						});
						resizeDialog.pack();
				});
				return resize;
		}
}
