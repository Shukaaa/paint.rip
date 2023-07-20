package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;

import java.util.ArrayList;

public final class Grayscale {
    private Grayscale() { }

    public static ArrayList<Pixel> grayscale(ArrayList<Pixel> pixels, int threshold) {
        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
            }
        }

        return pixels;
    }
}
