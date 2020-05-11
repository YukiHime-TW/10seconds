package bin.tenseconds.weapon;

public class Weapon {
    // 可拾取
    // 有名字
    // 有攻擊力
    // 有等級，方便在戰鬥結束時判斷Player要不要交換武器(1最爛，往上越好)

    private int offense;

    private String name;

    private int level;

    public static int MAX_VALUE;

    public Weapon() {
        this.name = "木棍";
        this.offense = 1;
        this.level = 1;
    }

    public Weapon(String name, int offense, int level) {
        this.name = name;
        this.offense = offense;
        this.level = level;
    }

    public Weapon(String code) {
        if (code == "C8763") {
            this.name = "封弊者";
            this.offense = MAX_VALUE;
            this.level = MAX_VALUE;
        } else {
            this.name = "木棍";
            this.offense = 1;
            this.level = 1;
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