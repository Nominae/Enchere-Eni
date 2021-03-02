package fr.eni.enchere.javaproject.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Enchere implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	
// Constructeur par défaut
	public Enchere() {
		
	}
	
	
	
// Constructeur prenant en compte tous les paramètres
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}
	
// Getters et Setters
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}




	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}




	public int getMontantEnchere() {
		return montantEnchere;
	}




	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}




	public int getNoArticle() {
		return noArticle;
	}




	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}




	public int getNoUtilisateur() {
		return noUtilisateur;
	}




	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}



// méthode ToString
	@Override
	public String toString() {
		return "Enchere ->\n dateEnchere :" + dateEnchere + "\n Montant Enchere :" + montantEnchere + "\n Num Article :" + noArticle
				+ "\n Num Utilisateur :" + noUtilisateur;
	}
	
	
	
	
	
	
	
	
	
}
