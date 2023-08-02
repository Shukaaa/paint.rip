package rip.shukaaa.enums;

public enum EffectCategory {
    EFFECTS("Effects", "Basics"),
    DISTORTION("Effects", "Distortion"),
    TRANSFORM("Image", "Transform");

    private final String mainCategory;
    private final String subCategory;

    EffectCategory(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }
}
