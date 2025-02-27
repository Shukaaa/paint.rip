package rip.shukaaa.api.effect.effects.distortion;

import rip.shukaaa.api.effect.category.EffectCategoryRegister;
import rip.shukaaa.api.effect.effects.Effect;
import rip.shukaaa.api.effect.input.EffectInput;
import rip.shukaaa.api.effect.input.inputs.Slider;
import rip.shukaaa.api.Pixel;
import rip.shukaaa.api.ShukaaaImage;
import rip.shukaaa.api.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class TriangleRuler extends Effect {
    public TriangleRuler() {
        super(new EffectInput[]{
                new Slider(0, 100, 20, "Intensity"),
        }, EffectCategoryRegister.DISTORTION);
    }

    @Override
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) {
        int intensity = (int) args.get("Intensity");
        Pixel[][] pixels = image.getPixels2d();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length - 1; j++) {
                if (pixels[i][j].getRed() > intensity && pixels[i][j].getGreen() > intensity && pixels[i][j].getBlue() > intensity) {
                    if (i > 0) {
                        pixels[i - 1][j].setRed(pixels[i - 1][j].getRed() + intensity);
                        pixels[i - 1][j].setGreen(pixels[i - 1][j].getGreen() + intensity);
                        pixels[i - 1][j].setBlue(pixels[i - 1][j].getBlue() + intensity);
                    }
                    if (i < pixels.length - 1) {
                        pixels[i + 1][j].setRed(pixels[i + 1][j].getRed() + intensity);
                        pixels[i + 1][j].setGreen(pixels[i + 1][j].getGreen() + intensity);
                        pixels[i + 1][j].setBlue(pixels[i + 1][j].getBlue() + intensity);
                    }
                    if (j > 0) {
                        pixels[i][j - 1].setRed(pixels[i][j - 1].getRed() + intensity);
                        pixels[i][j - 1].setGreen(pixels[i][j - 1].getGreen() + intensity);
                        pixels[i][j - 1].setBlue(pixels[i][j - 1].getBlue() + intensity);
                    }
                    if (j < pixels[i].length - 1) {
                        pixels[i][j + 1].setRed(pixels[i][j + 1].getRed() + intensity);
                        pixels[i][j + 1].setGreen(pixels[i][j + 1].getGreen() + intensity);
                        pixels[i][j + 1].setBlue(pixels[i][j + 1].getBlue() + intensity);
                    }
                }
            }
        }

        return ImageUtils.pixel2dTo1d(pixels);
    }
}
