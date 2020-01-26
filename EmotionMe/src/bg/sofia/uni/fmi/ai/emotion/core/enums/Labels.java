package bg.sofia.uni.fmi.ai.emotion.core.enums;

import org.jetbrains.annotations.Contract;

public enum Labels {
    ANGRY(0, "ANGRY"),
    DISGUST(1, "DISGUST"),
    FEAR(2, "FEAR"),
    HAPPY(3, "HAPPY"),
    SAD(4, "SAD"),
    SURPRISE(5, "SURPRISE"),
    NEUTRAL(6, "NEUTRAL");

    private final int id;
    private final String type;

    private static Labels[] labels = Labels.values();

    @Contract(pure = true)
    private Labels(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Contract(pure = true)
    public int getId() {
        return id;
    }

    @Contract(pure = true)
    public String getType() {
        return type;
    }
}
