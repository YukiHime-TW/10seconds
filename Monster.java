package bin.tenseconds.interactive;

import bin.tenseconds.weapon.Weapon;

public class Monster extends Mob {
    // 會掉落武器

    public Monster(int hp, Weapon weapon) {
        this.hp = hp;
        this.weapon = weapon;
    }
}