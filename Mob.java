package bin.tenseconds.interactive;

import bin.tenseconds.weapon.Weapon;

public class Mob {
    // 可碰撞，碰撞時不會穿越彼此
    // 有血量(V)
    // 持有武器(V)
    // 有等級(V)
    // 互相撞擊時觸發攻擊
    // 攻擊方式為對方的血量直接扣掉武器攻擊力(V)

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