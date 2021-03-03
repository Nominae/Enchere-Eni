package fr.eni.enchere.javaproject.bo;

import java.io.Serializable;

public class Categorie	implements Serializable {
		
	
		private static final long serialVersionUID = 1L;
		private int noCategorie;
		private String libelle;
		
		// Constructeur par défaut
		public Categorie() {
			
		}
		
		//Constructeur prenant en compte tous les paramètres
		public Categorie(int noCategorie, String libelle) 
		{
			super();
			this.noCategorie = noCategorie;
			this.libelle = libelle;
		}
		
		//Getters et Setters
		public int getNoCategorie() {
			return noCategorie;
		}

		public void setNoCategorie(int noCategorie) {
			this.noCategorie = noCategorie;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		//méthode ToString
		@Override
		public String toString() {
			return "Categorie ->\n Nom Categorie = " + noCategorie + "\n libelle =" + libelle;
		}
		
		
		
		

	

}
