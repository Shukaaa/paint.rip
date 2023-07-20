package rip.shukaaa.image.effects;

import rip.shukaaa.image.Pixel;

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
}
