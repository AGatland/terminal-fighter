package com.booleanuk;

import java.sql.SQLOutput;
import java.util.Scanner;
public class Main {
    static int prevActionP = 0;
    static int prevActionE = 0;

    public static void main(String[] args) {
        Fighter[] playerFighters = new Fighter[]{new Fighter("Swordy",500,75,75,75),
                new Fighter("Tanky",700,50,50,100),
                new Fighter("Sneaky",400,100,100,50)};

        // Enemy has boosted stats, since their action is random
        Fighter[] enemyFighters = new Fighter[]{new Fighter("Swordy",600,85,85,85),
                new Fighter("Tanky",800,60,60,110),
                new Fighter("Sneaky",500,110,110,60)};

        Scanner in = new Scanner(System.in);

        System.out.println("Hello and welcome to the fighting game!");

        System.out.println("\nChoose your fighter:");
        for(int i = 1; i-1 < playerFighters.length; i++) {
            System.out.println(i+". "+playerFighters[i-1].getName());
        }
        int fighter = in.nextInt();
        System.out.println("\nChoose your enemy:");
        for(int i = 1; i-1 < enemyFighters.length; i++) {
            System.out.println(i+". "+enemyFighters[i-1].getName());
        }
        int enemy = in.nextInt();

        System.out.println("\nPlayer: "+playerFighters[fighter-1].getName());
        System.out.println("Enemy: "+enemyFighters[enemy-1].getName());
        System.out.println("\nBear in mind, you can not choose the same action twice in a row");
        game(playerFighters[fighter-1], enemyFighters[enemy-1]);
    }

    public static void game(Fighter player, Fighter enemy) {
        while(player.getHp() > 0 && enemy.getHp() > 0) {
            double[] hp = gameRound(player, enemy);
            player.setHp(hp[0]);
            enemy.setHp(hp[1]);
        }
        if (player.getHp() < 0 && enemy.getHp() < 0) {
            System.out.println("\nIt's a DRAW");
        } else if (player.getHp() > 0) {
            System.out.println("\nYou WIN");
        } else {
            System.out.println("\nYou LOSE");
        }
    }

    public static double[] gameRound(Fighter player, Fighter enemy) {
        Scanner actionChooser = new Scanner(System.in);
        String[] actionStrings = new String[]{"Quick attack", "Heavy attack",
                            "Defend", "Dodge"};

        System.out.println("\nChoose action:");
        if(prevActionE != 1) {System.out.println("1. " + actionStrings[0]);}
        if(prevActionE != 2) {System.out.println("2. " + actionStrings[1]);}
        if(prevActionE != 3) {System.out.println("3. " + actionStrings[2]);}
        if(prevActionE != 4) {System.out.println("4. " + actionStrings[3]);}
        int action = actionChooser.nextInt();

        while(action == prevActionP) {
            System.out.println("\nPlease choose another action from your previous one");
            System.out.println("Choose action:");
            if(prevActionP != 1) {System.out.println("1. " + actionStrings[0]);}
            if(prevActionP != 2) {System.out.println("2. " + actionStrings[1]);}
            if(prevActionP != 3) {System.out.println("3. " + actionStrings[2]);}
            if(prevActionP != 4) {System.out.println("4. " + actionStrings[3]);}
            action = actionChooser.nextInt();
        }
        prevActionP = action;

        int aiAction = (int) ((Math.random() * (4 - 1)) + 1);
        while(aiAction == prevActionE) {
            aiAction = (int) ((Math.random() * (4 - 1)) + 1);
        }
        prevActionE = aiAction;

        System.out.println("\nYou chose to: " + actionStrings[action-1] +
                "\nEnemy chose to: "+actionStrings[aiAction-1]);

        switch(action) {
            case 1:
                if (aiAction == 1 || aiAction == 2) {
                    enemy.receiveAttack(player.getDmg()/2);
                    if (aiAction == 1) {
                        player.receiveAttack(enemy.getDmg()/2);
                    } else {
                        player.receiveAttack(enemy.getDmg());
                    }
                } else {
                    double[] data = player.quickAttack();
                    double val;
                    if (aiAction == 3) {
                         val = enemy.defend(data[0]);
                    } else {
                        val = enemy.dodge(data[0],data[1]);
                    }
                    enemy.receiveAttack(val);
                }
                break;
            case 2:
                if (aiAction == 1 || aiAction == 2) {
                    enemy.receiveAttack(player.getDmg());
                    if (aiAction == 1) {
                        player.receiveAttack(enemy.getDmg()/2);
                    } else {
                        player.receiveAttack(enemy.getDmg());
                    }
                } else {
                    double[] data = player.heavyAttack();
                    double val;
                    if (aiAction == 3) {
                        val = enemy.defend(data[0]);
                    } else {
                        val = enemy.dodge(data[0],data[1]);
                    }
                    enemy.receiveAttack(val);
                }
                break;
            case 3:
                if (aiAction == 1 || aiAction == 2) {
                    double val;
                    if (aiAction == 1) {
                        val = player.defend(enemy.getDmg()/2);
                        player.receiveAttack(val);
                    } else {
                        val = player.defend(enemy.getDmg());
                        player.receiveAttack(val);
                    }
                }
                else {

                    }
                break;

            case 4:
                if (aiAction == 1 || aiAction == 2) {
                    double val;
                    if (aiAction == 1) {
                        val = player.dodge(enemy.getDmg()/2, enemy.getDex());
                        player.receiveAttack(val);
                    } else {
                        val = player.dodge(enemy.getDmg(), enemy.getDex()/2);
                        player.receiveAttack(val);
                    }
                }
                else {

                }
                break;

        }
        System.out.println("\nPlayer health: " + player.getHp() +
                "\nEnemy health: " + enemy.getHp());
        return new double[]{player.getHp(), enemy.getHp()};
    }

}