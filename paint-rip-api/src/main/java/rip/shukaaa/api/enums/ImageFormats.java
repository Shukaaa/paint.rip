package rip.shukaaa.api.enums;

public enum ImageFormats {
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg"),
    GIF("gif"),
    BMP("bmp");

    private final String format;

    ImageFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
