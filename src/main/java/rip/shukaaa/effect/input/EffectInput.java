package rip.shukaaa.effect.input;

public abstract class EffectInput {
    String name;
    String title;

    public EffectInput(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
