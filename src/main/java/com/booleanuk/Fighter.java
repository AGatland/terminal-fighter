package com.booleanuk;


class Fighter{

    // Not necessary when no DB
    //String fighterId;
    String name;
    double hp;
    double dmg;
    double dex;
    double def;

    public Fighter(String name, double hp, double dmg, double dex, double def) {
        this.hp = hp;
        this.dmg = dmg;
        this.dex = dex;
        this.def = def;
        this.name = name;
    }

    public double[] quickAttack() {
        return new double[]{dmg/2, dex};
    }

    public double[] heavyAttack() {
        return new double[]{dmg, dex/2};
    }

    public double defend(double dmgE) {
        if (dmgE > def) {
            return dmgE - def;
        }
        return 0;
    }

    public double dodge(double dmgE, double dexE) {
        if (dexE > dex) {
            return dmgE*0.75;
        }
        return 0;
    }

    public void receiveAttack(double dmg) {
        setHp(getHp()-dmg);
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public double getDmg() {
        return dmg;
    }

    public double getDex() {
        return dex;
    }

    public double getDef() {
        return def;
    }
}
