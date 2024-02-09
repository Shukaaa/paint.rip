package rip.shukaaa.application.effect.category;

public class EffectCategory {
    String mainCategory;
    String subCategory;

    public EffectCategory(String mainCategory, String subCategory) {
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
