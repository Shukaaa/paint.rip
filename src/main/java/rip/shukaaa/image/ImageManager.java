package rip.shukaaa.image;

import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.stores.DataStore;
import rip.shukaaa.ui.UiManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    public static UiManager uiManager;

    public static void updateImage(ShukaaaImage img) {
        String tempImageCount = DataStore.getTempImageCount();

        DataStore.setTempImageCount(String.valueOf(Integer.parseInt(tempImageCount) + 1));
        img.export("./temp/tempImage-" + tempImageCount, ImageFormats.PNG);
        uiManager.updateImage("./temp/tempImage-" + tempImageCount + ".png");
        DataStore.setImg(img);

        if (Integer.parseInt(tempImageCount) >= 1) {
            uiManager.enableUndo();
        }

        if (DataStore.getPossibleRedoCount() >= 1) {
            uiManager.disableRedo();
        }
    }

    public static void resetImage() throws ImageNotFoundException {
        String tempImageCount = DataStore.getTempImageCount();
        int tempImageCountInt = Integer.parseInt(tempImageCount);

        for (int i = 1; i < tempImageCountInt; i++) {
            File tempImage = new File("./temp/tempImage-" + i + ".png");
            if (!tempImage.delete()) {
                System.out.println("Failed to delete temp image");
            }
        }

        DataStore.setTempImageCount("0");
        File tempImage = new File("./temp/tempImage-0.png");
        setImg(tempImage);

        DataStore.setPossibleRedoCount(0);
        uiManager.disableRedo();
    }

    public static void undo() throws ImageNotFoundException {
        String tempImageCount = DataStore.getTempImageCount();
        int tempImageCountInt = Integer.parseInt(tempImageCount);

        if (tempImageCountInt >= 1) {
            DataStore.setTempImageCount(String.valueOf(tempImageCountInt - 2));
            File tempImage = new File("./temp/tempImage-" + (tempImageCountInt - 2) + ".png");
            setImg(tempImage);

            DataStore.setPossibleRedoCount(DataStore.getPossibleRedoCount() + 1);
            uiManager.enableRedo();
        }
    }

    public static void redo() throws ImageNotFoundException {
        String tempImageCount = DataStore.getTempImageCount();
        int tempImageCountInt = Integer.parseInt(tempImageCount);

        if (DataStore.getPossibleRedoCount() >= 1) {
            DataStore.setTempImageCount(String.valueOf(tempImageCountInt));
            File tempImage = new File("./temp/tempImage-" + (tempImageCountInt) + ".png");
            setImg(tempImage);

            DataStore.setPossibleRedoCount(DataStore.getPossibleRedoCount() - 1);
            if (DataStore.getPossibleRedoCount() == 0) {
                uiManager.disableRedo();
            }
        }
    }

    private static ShukaaaImage getImg(File f) throws ImageNotFoundException {
        try {
            BufferedImage buffImg = ImageIO.read(f);
            return new ShukaaaImage(buffImg);
        } catch (IOException e) {
            throw new ImageNotFoundException("No image found in this path!");
        }
    }

    public static void setImg(File f) throws ImageNotFoundException {
        ShukaaaImage img = getImg(f);
        String tempImageCount = DataStore.getTempImageCount();

        File tempFolder = new File("temp");
        if (!tempFolder.exists()) {
            if (!tempFolder.mkdir()) {
                System.out.println("Failed to create temp folder");
            }
        }

        if (Integer.parseInt(tempImageCount) == 0) {
            uiManager.disableUndo();
        }

        img.export("./temp/tempImage-" + tempImageCount, ImageFormats.PNG);
        uiManager.updateImage("./temp/tempImage-" + tempImageCount + ".png");
        DataStore.setTempImageCount(String.valueOf(Integer.parseInt(tempImageCount) + 1));
        DataStore.setImg(img);
    }

    public static void deleteTempImages() {
        File tempFolder = new File("temp");

        if (tempFolder.exists()) {
            File[] files = tempFolder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.delete()) {
                        System.out.println("Failed to delete temp image");
                    }
                }
            }
            if (!tempFolder.delete()) {
                System.out.println("Failed to delete temp folder");
            }
        }
    }
}
