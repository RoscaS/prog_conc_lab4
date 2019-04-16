package com.ch.smartBike;

/**
 * classe de configuration des parametres de l'application
 * nbSite: le nombre de site pour la simulation, par exemple, ecole, centre ville....
 * nbHab : le nombre d'habitant dans la ville
 * slotsPerSite: Le nomdre de borne de recharge sur chaque site
 * nbVelo: le nombre total de velo.
 */
public class Config {
    private int nbSite;
    private int nbHab;
    private int slotsPerSite;
    private int nbVelo;

    public Config() {
        this.nbSite = 5;
        this.nbHab = 5;
        this.slotsPerSite = 5;
        this.nbVelo = 30;

        // S * (B - 2) + 3 != 30
        // S * (B -2 + 3) = 30
    }

    public Config(int nbSite, int nbHab, int slotsPerSite, int nbVelo) {
        this.nbSite = nbSite;
        this.nbHab = nbHab;
        this.slotsPerSite = slotsPerSite;
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

    public int getSlotsPerSite() {
        return slotsPerSite;
    }

    public void setSlotsPerSite(int slotsPerSite) {
        this.slotsPerSite = slotsPerSite;
    }

    public int getNbVelo() {
        return nbVelo;
    }

    public void setNbVelo(int nbVelo) {
        this.nbVelo = nbVelo;
    }


}
