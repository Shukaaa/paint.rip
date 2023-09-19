package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.Main;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.ui.UiManager;
import rip.shukaaa.ui.logic.menu.items.MenuItem;

import javax.swing.*;
import java.io.File;

public class OpenMenuItem extends MenuItem {
		private final UiManager uiManager;

		public OpenMenuItem(UiManager uiManager) {
				this.uiManager = uiManager;
		}

		@Override
		protected JMenuItem createItem() {
				JMenuItem open = new JMenuItem("Open");
				open.addActionListener(e -> {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						fileChooser.showOpenDialog(null);

						File selectedFile = fileChooser.getSelectedFile();
						if (selectedFile == null) {
								return;
						}

						try {
								Main.setImg(selectedFile);
								Main.backupImage = selectedFile;
								Main.updateImage(Main.getImg(Main.backupImage), this.uiManager);
						} catch (ImageNotFoundException ex) {
								throw new RuntimeException(ex);
						}
				});
				return open;
		}
}
