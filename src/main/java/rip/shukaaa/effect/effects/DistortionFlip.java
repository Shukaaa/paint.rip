package rip.shukaaa.effect.effects;

import rip.shukaaa.effect.Effect;
import rip.shukaaa.effect.EffectRegister;
import rip.shukaaa.exceptions.EffectOptionNotFoundException;
import rip.shukaaa.image.Pixel;
import rip.shukaaa.image.ShukaaaImage;

import java.util.ArrayList;
import java.util.HashMap;

public class DistortionFlip implements Effect {
    public ArrayList<Pixel> apply(ShukaaaImage image, HashMap<String, Object> args) throws EffectOptionNotFoundException {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                pixels.add(new Pixel(image.getRGB(y, x)));
            }
        }

        ShukaaaImage temp = new ShukaaaImage(image.getWidth(), image.getHeight());
        temp.setPixels(pixels);

        return EffectRegister.getEffect("flip").apply(temp, new HashMap<>());
    }
}
