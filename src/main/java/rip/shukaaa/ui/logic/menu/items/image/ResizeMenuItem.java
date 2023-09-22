package rip.shukaaa.ui.logic.menu.items.image;

import rip.shukaaa.image.ImageManager;
import rip.shukaaa.image.ShukaaaImage;
import rip.shukaaa.stores.DataStore;
import rip.shukaaa.ui.logic.menu.items.MenuItem;
import rip.shukaaa.utils.UiUtils;

import javax.swing.*;

public class ResizeMenuItem extends MenuItem {
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
								ImageManager.updateImage(img);
								resizeDialog.dispose();
						});
						resizeDialog.pack();
				});
				return resize;
		}
}
