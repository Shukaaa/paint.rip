package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;

public class Transform {
    private Transform() { }

    public static ArrayList<Pixel> flip(ArrayList<Pixel> pixels) {
        ArrayList<Pixel> reversed = new ArrayList<>();
        for (int i = pixels.size() - 1; i >= 0; i--) {
            reversed.add(pixels.get(i));
        }
        return reversed;
    }

    public static void distortionFlip(ShukaaaImage image) {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                pixels.add(new Pixel(image.getRGB(y, x)));
            }
        }

        pixels = flip(pixels);
        image.setPixels(pixels);
    }
}
