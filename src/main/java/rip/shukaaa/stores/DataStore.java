package rip.shukaaa.stores;

import rip.shukaaa.image.ShukaaaImage;

public class DataStore {
		private static ShukaaaImage img = null;
		private static String tempImageCount = "0";

		public static void setImg(ShukaaaImage img) {
				DataStore.img = img;
		}

		public static ShukaaaImage getImg() {
				return img;
		}

		public static void setTempImageCount(String tempImageCount) {
				DataStore.tempImageCount = tempImageCount;
		}

		public static String getTempImageCount() {
				return tempImageCount;
		}
}
