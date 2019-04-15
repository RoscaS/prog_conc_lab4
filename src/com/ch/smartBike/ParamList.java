package com.ch.smartBike;
/*
 * Classe concernant les ressources statiques
 */
public class ParamList {

	private static String[] listSite = {"MAISON","ECOLE","TRAVAIL","PISCINE","MAGASIN","BOUCHERIE","BANQUE"," BAR",
			"RESTAURANT","DISCOTHEQUE","BOULANGERIE","FITNESS","PARC","JARDIN","CINEMA"};

	private static String[]  nameList ={"Oliver","Guillaume","Marcel","Louise","Marguerite","Noelle","Robert","Ben",
			"Adrienne","Hedvige","Eric","Emile","Garland","Elisabeth","Patrick "};

	public static String getSiteName(int index)
	{
		return listSite[index];
	}

	public static int getSiteNb()
	{
		return listSite.length;
	}

	public static String getName(int index)
	{
		return nameList[index];
	}

	public static int getHabNb()
	{
		return nameList.length;
	}

}
