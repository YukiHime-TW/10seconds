package bin.tenseconds.interactive;

import bin.tenseconds.weapon.Weapon;

public class Mob {
    // 可碰撞，碰撞時不會穿越彼此
    // 有血量
    // 持有武器
    // 有等級，每升一等加20血量
    // 互相撞擊時觸發攻擊，攻擊方式為對方的血量直接扣掉武器攻擊力

    public int hp;
    public Weapon weapon;
    public int level;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}