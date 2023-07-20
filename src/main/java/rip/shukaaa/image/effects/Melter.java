package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;

import java.util.ArrayList;

public class Melter {
    private Melter() { }

    public static ArrayList<Pixel> melt(ArrayList<Pixel> pixels, int threshold, Pixel pixelColor) {
        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();

        // melt color together with the pixels
        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                pixel.setRed((avg + r) / 2);
                pixel.setGreen((avg + g) / 2);
                pixel.setBlue((avg + b) / 2);
            }
        }

        return pixels;
    }
}
