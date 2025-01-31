package rip.shukaaa.application.ui.logic.menu.items.image;

import rip.shukaaa.application.image.ImageManager;
import rip.shukaaa.api.ShukaaaImage;
import rip.shukaaa.application.keystrokes.KeyStrokeRegister;
import rip.shukaaa.application.stores.DataStore;
import rip.shukaaa.application.ui.logic.menu.items.MenuItem;
import rip.shukaaa.application.utils.UiUtils;

import javax.swing.*;
import java.util.Optional;

public class ResizeMenuItem extends MenuItem {
    public ResizeMenuItem() {
        super(Optional.of(KeyStrokeRegister.resize));
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
                ShukaaaImage img = DataStore.getImg().resizeImage((int) widthSpinner.getValue(), (int) heightSpinner.getValue());
                ImageManager.updateImage(img);
                resizeDialog.dispose();
            });
            resizeDialog.pack();
        });
        return resize;
    }
}
