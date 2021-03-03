package fr.eni.enchere.javaproject.bo;

import java.io.Serializable;

public class Retrait implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int noArticle;
	private String rue;
	private String codePostal;
	private String ville;
	
	
// Constructeur par défaut
	public Retrait() {
		
	}

// Constructeur prenant en compte tous les paramètres
	public Retrait(int noArticle, String rue, String codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
// Getters et Setters 
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
// Méthode ToString
	@Override
	public String toString() {
		return "Retrait ->\n Num Article :" + noArticle + "\n rue :" + rue + "\n Code Postal :" + codePostal + "\n Ville :" + ville;
	}
	
	
	


	
}
