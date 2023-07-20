package rip.shukaaa.image;

import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.image.effects.BlackAndWhite;
import rip.shukaaa.image.effects.Grayscale;
import rip.shukaaa.image.effects.Invert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShukaaaImage extends BufferedImage {
    private ArrayList<Pixel> pixels;
    private ArrayList<Pixel> pixelBackup;

    public ShukaaaImage(BufferedImage img) {
        super(img.getWidth(), img.getHeight(), img.getType());
        this.setData(img.getData());
    }

    //* Pixels *//

    private void checkPixels() {
        if (pixels == null) {
            pixels = new ArrayList<>();
            for (int x = 0; x < this.getWidth(); x++) {
                for (int y = 0; y < this.getHeight(); y++) {
                    pixels.add(new Pixel(this.getRGB(x, y)));
                }
            }
            pixelBackup = new ArrayList<>(pixels);
        }
    }

    public ArrayList<Pixel> getPixels() {
        checkPixels();
        return pixels;
    }

    public void setPixels(ArrayList<Pixel> pixels) {
        this.pixelBackup = this.pixels;
        this.pixels = pixels;

        // Update image
        int i = 0;
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++, i++) {
                this.setRGB(x, y, pixels.get(i).toInt());
            }
        }
    }

    //* Resize *//

    private ShukaaaImage resizedImage(int width, int height) {
        BufferedImage resized = new BufferedImage(width, height, this.getType());
        resized.getGraphics().drawImage(this, 0, 0, width, height, null);
        return new ShukaaaImage(resized);
    }

    //* Export *//

    public void export(String name, ImageFormats format, ShukaaaImage... imgs) {
        ShukaaaImage img;

        if (imgs.length > 0) {
            img = imgs[0];
        } else {
            img = this;
        }

        try{
            File f = new File(name + "." + format.getFormat());
            ImageIO.write(img, format.getFormat(), f);
        } catch(IOException e){
            System.err.println("Error while writing image!");
        }
    }

    public void export(String name, ImageFormats format, int width, int height) {
        ShukaaaImage resized = this.resizedImage(width, height);
        resized.export(name, format);
    }

    public void undo() {
        if (pixelBackup != null) {
            this.setPixels(pixelBackup);
        }
    }

    public void redo() {
        if (pixelBackup != null) {
            this.setPixels(pixels);
        }
    }

    //* Effects *//

    public void invert(int threshold) {
        this.setPixels(Invert.invert(this.getPixels(), threshold));
    }

    public void grayscale(int threshold) {
        this.setPixels(Grayscale.grayscale(this.getPixels(), threshold));
    }

    public void blackAndWhite(int threshold) {
        this.setPixels(BlackAndWhite.blackAndWhite(this.getPixels(), threshold));
    }
}
