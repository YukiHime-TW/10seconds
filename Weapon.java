package bin.tenseconds.weapon;

public class Weapon {
    // 可拾取
    // 有名字
    // 有攻擊力
    // 有等級，方便在戰鬥結束時判斷Player要不要交換武器(1最爛，往上越好)

    private int offense;

    private String name;

    private int level;

    private String description;

    public static int MAX_VALUE;

    public Weapon() {
        this.name = "拳頭";
        this.offense = 1;
        this.level = 0;
        this.description = "你的雙手，看起來沒有甚麼用。";
    }

    public Weapon(String name, int offense, int level, String description) {
        this.name = name;
        this.offense = offense;
        this.level = level;
        this.description = description;
    }

    public Weapon(String code) {
        if (code == "C8763") {
            this.name = "封弊者";
            this.offense = MAX_VALUE;
            this.level = MAX_VALUE;
            this.description = "一把藍色跟一把黑色的雙劍，感覺似曾相識....?";
        } else {
            this.name = "藍黑色的兩根木棍";
            this.offense = 2;
            this.level = 1;
            this.description = "只是一根藍色跟一根黑色的木棍而已，但是因為有兩根，所以攻擊力還是比木棍高了兩倍";
        }
    }

    public String getName() {
        return name;
    }

    public int getOffense() {
        return offense;
    }

    public int getLevel() {
        return level;
    }

}