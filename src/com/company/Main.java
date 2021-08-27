package com.company;

import java.util.Random;

public class Main {

    public static int bristlebackHealth = 1000; //Здоровье босса
    public static int bristlebackDamage = 40; //Урон босса
    public static String bristlebackDefenceType = " "; //Защита босса
    public static int[] heroesHealth = {400, 400, 400, 400}; //Здоровье героев
    public static int[] heroesDamage = {40, 0, 40, 40}; //Урон героев
    public static int doctor = 15; //Доктор
    public static String[] heroesAtackType = {"Invoker", "Doctor", "Sniper", "Anti-Mage"}; //Герои

    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {   //Параметры раунда
        changeBristlebackDefence();
        brislebackHit();
        if (bristlebackHealth > 0) {
            heroesHit();
        }
        fightInfo();
        hill();
    }

    public static boolean isFinished() {   //Победа босса, Героев
        if (bristlebackHealth <= 0) {
            System.out.println("Heroes Won!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Bristleback won!");
            return true;
        }
        return false;
    }

    public static void hill() { //Лечение
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] + doctor;
        }
    }

    public static void changeBristlebackDefence() { //Защита босса
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAtackType.length);
        bristlebackDefenceType = heroesAtackType[randomIndex];
    }


    public static void brislebackHit() {  //Босс наносит урон
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] - bristlebackDamage;
        }
    }

    public static void heroesHit() {   //Герои наносят урон
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bristlebackHealth > 0) {
                if (bristlebackDefenceType.equals(heroesAtackType[i])) {
                    Random random2 = new Random();
                    int koef = random2.nextInt(9) + 2;
                    if (bristlebackHealth - heroesDamage[i] * koef < 0) {
                        bristlebackHealth = 0;
                    } else {
                        bristlebackHealth = bristlebackHealth - heroesDamage[i] * koef;
                    }
                    System.out.println(heroesAtackType[i] + " critical hit: " + heroesDamage[i] * koef);
                } else {
                    if (bristlebackHealth - heroesDamage[i] < 0) {
                        bristlebackHealth = 0;
                    } else {
                        bristlebackHealth = bristlebackHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void fightInfo() {  //Информация сражения
        System.out.println("______________");
        System.out.println("Bristleback Health " + bristlebackHealth);
        System.out.println("Invoker Health " + heroesHealth[0]);
        System.out.println("Crystall Maden Health " + heroesHealth[1]);
        System.out.println("Sniper Health " + heroesHealth[2]);
        System.out.println("Anti-Mage Health " + heroesHealth[3]);
        System.out.println("______________");
    }
}





