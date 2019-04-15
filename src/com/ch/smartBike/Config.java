package com.ch.smartBike;

/**
 * classe de configuration des parametres de l'application
 * nbSite: le nombre de site pour la simulation, par exemple, ecole, centre ville....
 * nbHab : le nombre d'habitant dans la ville
 * nbBorne: Le nomdre de borne de recharge sur chaque site
 * nbVelo: le nombre total de velo.
 */
public class Config {
    private int nbSite;
    private int nbHab;
    private int nbBorne;
    private int nbVelo;

    public Config() {
        this.nbSite = 5;
        this.nbHab = 5;
        this.nbBorne = 5;
        this.nbVelo = 30;



        // 5 * (5 - 2) + 3 = 18

        // 4 slots
        // 7 vélos
    }

    public Config(int nbSite, int nbHab, int nbBorne, int nbVelo) {
        this.nbSite = nbSite;
        this.nbHab = nbHab;
        this.nbBorne = nbBorne;
        this.nbVelo = nbVelo;
    }

    public int getNbSite() {
        return nbSite;
    }

    public void setNbSite(int nbSite) {
        this.nbSite = nbSite;
    }

    public int getNbHab() {
        return nbHab;
    }

    public void setNbHab(int nbHab) {
        this.nbHab = nbHab;
    }

    public int getNbBorne() {
        return nbBorne;
    }

    public void setNbBorne(int nbBorne) {
        this.nbBorne = nbBorne;
    }

    public int getNbVelo() {
        return nbVelo;
    }

    public void setNbVelo(int nbVelo) {
        this.nbVelo = nbVelo;
    }


}
