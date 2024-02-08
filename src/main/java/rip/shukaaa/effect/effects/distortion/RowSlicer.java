package rip.shukaaa.effect.effects.distortion;

import rip.shukaaa.effect.category.EffectCategoryRegister;
import rip.shukaaa.effect.effects.Effect;
import rip.shukaaa.effect.input.EffectInput;
import rip.shukaaa.effect.input.inputs.ComboBox;
import rip.shukaaa.enums.RowSlicerDirection;
import rip.shukaaa.enums.RowSlicerMode;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RowSlicer extends Effect {
    public RowSlicer() {
        super(new EffectInput[]{
                new ComboBox<>(RowSlicerDirection.values(), "Direction"),
                new ComboBox<>(RowSlicerMode.values(), "Mode")
        }, EffectCategoryRegister.DISTORTION);
    }

    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        RowSlicerDirection direction = (RowSlicerDirection) args.get("Direction");
        RowSlicerMode mode = (RowSlicerMode) args.get("Mode");

        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> pixel = image.getPixels();

        if (direction == RowSlicerDirection.ROW) {
            return rowSlice(height, width, pixel, mode);
        } else {
            return rowSlice(width, height, pixel, mode);
        }
    }

    private static ArrayList<Pixel> rowSlice(int height, int width, ArrayList<Pixel> pixel, RowSlicerMode mode) {
        ArrayList<Pixel> newPixels = new ArrayList<>(pixel.size());

        for (int y = 0; y < height; y++) {
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

        return newPixels;
    }
}
