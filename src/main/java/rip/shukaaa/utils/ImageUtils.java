package rip.shukaaa.utils;

import rip.shukaaa.image.Pixel;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageUtils {
		public static ArrayList<Pixel> pixel2dTo1d(Pixel[][] pixels) {
				ArrayList<Pixel> pixels1d = new ArrayList<>();
				for (Pixel[] pixel : pixels) {
						pixels1d.addAll(Arrays.asList(pixel));
				}
				return pixels1d;
		}
}
