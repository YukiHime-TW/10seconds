package bin.tenseconds.interactive;

import bin.tenseconds.weapon.Weapon;

public class Player extends Mob {
    // 持有武器(更強的武器自動取代)(V)
    // 倒數計時
    // 互相撞擊時觸發攻擊
    // 攻擊方式為對方的血量直接扣掉武器攻擊力(V)
    // 有等級(V)
    // 每打完一場戰鬥就加10經驗值，每升一等加20血量(V)
    // 主角初始血量: 20
    // 滿等血量: 100
    // 升到等級2所需經驗: 10
    // 升到等級3所需經驗: 20
    // 升到等級4所需經驗: 30
    // 升到等級5所需經驗: 40
    // 等級5顯示為滿等

    private int tick;
    public int experience = 0;

    public Player() {
        this.setHp(20);
        this.weapon = new Weapon();
        this.level = 1;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }

    public void changeWeapon(Monster enemy) {
        if (enemy.weapon.getLevel() > this.weapon.getLevel()) {
            // 輸出"更換武器成(武器名)"
            this.weapon = enemy.weapon;
        }
    }

    public int getExperience() {
        return experience;
    }

    public void gainExperience() {
        experience += 20;
        levelUp();
    }

    public void levelUp() {
        if (experience == (level * 10)) {

            // 輸出"恭喜升級"

            if (level++ == 5) {
                level = 5;
                // 輸出"滿等"
            } else {
                level++;
            }
            
            experience = 0;
        } else {
            return;
        }
    }

    public void attack(Monster enemy) {
        if (enemy.getHp() - this.weapon.getOffense() <= 0) {
            enemy.setHp(0);
            this.changeWeapon(enemy);
            return;
        } else {
            enemy.setHp(enemy.getHp() - this.weapon.getOffense());
            return;
        }
    }

}