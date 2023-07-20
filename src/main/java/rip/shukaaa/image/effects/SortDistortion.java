package rip.shukaaa.image.effects;

import rip.shukaaa.enums.RowSlicerMode;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.Comparator;

public final class SortDistortion {
    private SortDistortion() { }

    public static void rowSlicer(ShukaaaImage image, RowSlicerMode mode) {
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> pixel = image.getPixels();
        ArrayList<Pixel> newPixels = new ArrayList<>(pixel.size());

        for (int y = 0; y < height; y++) {
            System.out.println("Progress: " + (int) (((double) y / (double) height) * 100) + "%");
            ArrayList<Pixel> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(pixel.get(x + y * width));
            }

            switch (mode) {
                case BLUE -> row.sort(Comparator.comparingInt(Pixel::getBlue));
                case GREEN -> row.sort(Comparator.comparingInt(Pixel::getGreen));
                case RED -> row.sort(Comparator.comparingInt(Pixel::getRed));
            }

            newPixels.addAll(row);
        }

        System.out.println("Progress: 100%");

        image.setPixels(newPixels);
    }

    public static void cosSinShuffler(ShukaaaImage image, int modulo) {
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> pixels = image.getPixels();
        ArrayList<Pixel> newPixels = new ArrayList<>(pixels.size());

        for (Pixel pixel : pixels) {
            int x = pixel.getX(width);
            int y = pixel.getY(width);

            int newX = (int) (Math.cos(x) * modulo);
            int newY = (int) (Math.sin(y) * modulo);

            if (newX < 0) newX = -newX;
            if (newY < 0) newY = -newY;

            if (newX >= width) newX = width - 1;
            if (newY >= height) newY = height - 1;

            Pixel newPixel = pixels.get(newX + newY * width);
            pixel.setRed(newPixel.getRed());
            pixel.setGreen(newPixel.getGreen());
            pixel.setBlue(newPixel.getBlue());

            newPixels.add(pixel);
        }

        image.setPixels(newPixels);
    }


}
