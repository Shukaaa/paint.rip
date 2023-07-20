package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;

import java.util.ArrayList;

public final class BlackAndWhite {
    private BlackAndWhite() { }

    public static ArrayList<Pixel> blackAndWhite(ArrayList<Pixel> pixels, int threshold) {
        for (Pixel pixel : pixels) {
            if (pixel.getRed() < threshold && pixel.getGreen() < threshold && pixel.getBlue() < threshold) {
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                if (avg > 127) {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                } else {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
            }
        }

        return pixels;
    }
}
