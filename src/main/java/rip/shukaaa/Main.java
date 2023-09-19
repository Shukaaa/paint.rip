package rip.shukaaa;

import rip.shukaaa.enums.ImageFormats;
import rip.shukaaa.exceptions.ImageNotFoundException;
import rip.shukaaa.image.ShukaaaImage;
import rip.shukaaa.ui.UiManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
		public static File backupImage = null;
		public static ShukaaaImage img = null;
		public static String tempImageCount = "0";
		public static UiManager uiManager;

		public static void main(String[] args) {
				uiManager = new UiManager("paint.rip");
		}

		public static void updateImage(ShukaaaImage img, UiManager uiManager) {
				if (!new File("./temp/tempImage-" + tempImageCount + ".png").delete()) {
						System.out.println("Failed to delete temp image");
				}
				tempImageCount = String.valueOf(Integer.parseInt(tempImageCount) + 1);
				img.export("./temp/tempImage-" + tempImageCount, ImageFormats.PNG);
				uiManager.updateImage("./temp/tempImage-" + tempImageCount + ".png");
		}

		public static ShukaaaImage getImg(File f) throws ImageNotFoundException {
				try {
						BufferedImage buffImg = ImageIO.read(f);
						img = new ShukaaaImage(buffImg);
						return img;
				} catch (IOException e) {
						throw new ImageNotFoundException("No image found in this path!");
				}
		}

		public static void setImg(File f) throws ImageNotFoundException {
				ShukaaaImage img = getImg(f);

				// check if folder temp exists and if not create it
				File tempFolder = new File("temp");
				if (!tempFolder.exists()) {
						if (!tempFolder.mkdir()) {
								System.out.println("Failed to create temp folder");
						}
				}

				img.export("./temp/tempImage-" + tempImageCount, ImageFormats.PNG);
				uiManager.updateImage("./temp/tempImage-" + tempImageCount + ".png");
				tempImageCount = String.valueOf(Integer.parseInt(tempImageCount) + 1);
		}

		public static void deleteTempImages() {
				// delete temp folder
				File tempFolder = new File("temp");

				if (tempFolder.exists()) {
						File[] files = tempFolder.listFiles();
						if (files != null) {
								for (File f : files) {
										if (!f.delete()) {
												System.out.println("Failed to delete temp image");
										}
								}
						}
						if (!tempFolder.delete()) {
								System.out.println("Failed to delete temp folder");
						}
				}
		}
}