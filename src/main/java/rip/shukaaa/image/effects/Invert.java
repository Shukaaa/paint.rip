package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;

import java.util.ArrayList;

public final class Invert {
    private Invert() { }

    public static ArrayList<Pixel> invert(ArrayList<Pixel> pixels, int intensity) {
        for (Pixel pixel : pixels) {
            if (pixel.getRed() < intensity && pixel.getGreen() < intensity && pixel.getBlue() < intensity) {
                pixel.setRed(255 - pixel.getRed());
                pixel.setGreen(255 - pixel.getGreen());
                pixel.setBlue(255 - pixel.getBlue());
            }
        }

        return pixels;
    }
}
