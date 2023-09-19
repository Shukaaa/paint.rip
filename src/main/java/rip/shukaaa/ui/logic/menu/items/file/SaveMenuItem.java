package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.Main;
import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.ui.logic.menu.items.MenuItem;
import rip.shukaaa.utils.UiUtils;

import javax.swing.*;

public class SaveMenuItem extends MenuItem {
		@Override
		protected JMenuItem createItem() {
				JMenuItem save = new JMenuItem("Save");
				save.addActionListener(e -> {
						JDialog saveDialog = UiUtils.createDialog("Save Image");
						JTextField fileName = new JTextField();
						fileName.setColumns(10);
						fileName.setBorder(BorderFactory.createTitledBorder("File Name"));
						saveDialog.add(fileName);
						JComboBox<ImageFormats> fileExtension = new JComboBox<>(ImageFormats.values());
						fileExtension.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
						saveDialog.add(fileExtension);

						JSpinner widthSpinner = UiUtils.createSpinner(Main.img.getWidth(), 1, 10000, "Width");
						saveDialog.add(widthSpinner);

						JSpinner heightSpinner = UiUtils.createSpinner(Main.img.getHeight(), 1, 10000, "Height");
						saveDialog.add(heightSpinner);

						JButton saveButton = new JButton("Save");
						saveDialog.add(saveButton);
						saveButton.addActionListener(e1 -> {
								Main.img.export(fileName.getText(), (ImageFormats) fileExtension.getSelectedItem(), (int) widthSpinner.getValue(), (int) heightSpinner.getValue());
								saveDialog.dispose();
						});
						saveDialog.pack();
				});
				return save;
		}
}
