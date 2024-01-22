package rip.shukaaa.ui.logic.menu.items.file;

import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.stores.DataStore;
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

            JSpinner widthSpinner = UiUtils.createSpinner(DataStore.getImg().getWidth(), 1, 10000, "Width");
            saveDialog.add(widthSpinner);

            JSpinner heightSpinner = UiUtils.createSpinner(DataStore.getImg().getHeight(), 1, 10000, "Height");
            saveDialog.add(heightSpinner);

            JButton saveButton = new JButton("Save");
            saveDialog.add(saveButton);
            saveButton.addActionListener(e1 -> {
                DataStore.getImg().export(fileName.getText(), (ImageFormats) fileExtension.getSelectedItem(), (int) widthSpinner.getValue(), (int) heightSpinner.getValue());
                saveDialog.dispose();
            });
            saveDialog.pack();
        });
        return save;
    }
}
