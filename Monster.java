package bin.tenseconds.interactive;

import bin.tenseconds.weapon.Weapon;

public class Monster extends Mob {
    // 會掉落武器

    private String name;

    public Monster(int hp, Weapon weapon, String name) {
        this.name = name;
        this.hp = hp;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void attack(Player player) {
        player.setHp(player.getHp() - this.weapon.getOffense());
        if(player.getHp()<=0){
            player.setHp(0);
            return;
        }
    }
}