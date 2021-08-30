package com.company;

import java.util.Random;

public class Main {

    public static int bristlebackHealth = 4000; //Здоровье босса
    public static int bristlebackDamage = 50; //Урон босса
    public static String bristlebackDefenceType = " "; //Защита босса
    public static int[] heroesHealth = {450, 250, 350, 500, 600}; //Здоровье героев
    public static int[] heroesDamage = {40, 0, 40, 40, 10}; //Урон героев
    public static int doctor = 20; //Доктор
    public static String[] heroesAtackType = {"Invoker", "Doctor", "Sniper", "Anti-Mage", "Tank"}; //Герои

    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {   //Параметры раунда
        changeBristlebackDefence();
        if (bristlebackHealth > 0) {
            brislebackHit();
        }
        heroesHit();


        fightInfo();
        hill();
    }


    public static boolean isFinished() {   //Победа босса, Героев
        if (bristlebackHealth <= 0) {
            System.out.println("Heroes Won!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[4] <= 0) {
            System.out.println("Bristleback won!");
            return true;
        }
        return false;
    }

    public static void tank() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] - bristlebackDamage / 2 < 0) {
                heroesHealth[4] = 0;
            } else {
                heroesHealth[4] -= bristlebackDamage / 2;
            }
            if (heroesHealth[i] - bristlebackDamage / 2 < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bristlebackDamage / 2;
            }
        }

    }

    public static void hill() { //Лечение
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] + doctor;
                }
            }
        }
    }

    public static void changeBristlebackDefence() { //Защита босса
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAtackType.length);
        bristlebackDefenceType = heroesAtackType[randomIndex];
    }


    public static void brislebackHit() {  //Босс наносит урон
        if (heroesHealth[4] > 0) {
            tank();
        } else {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] - bristlebackDamage < 0) {
                    heroesHealth[i] = 0;
                }
                if (heroesHealth[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] - bristlebackDamage;
                }
            }
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
        System.out.println("Bristleback Health: " + bristlebackHealth);
        System.out.println("Invoker Health: " + heroesHealth[0]);
        System.out.println("Doctor Health: " + heroesHealth[1]);
        System.out.println("Sniper Health: " + heroesHealth[2]);
        System.out.println("Anti-Mage Health: " + heroesHealth[3]);
        System.out.println("Tank Health: " + heroesHealth[4]);
        System.out.println("______________");
    }
}





