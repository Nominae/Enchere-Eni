package fr.eni.enchere.javaproject.bo;

import java.sql.Date;

public class Enchere {
int no_enchere;
Date date_enchere;
int montant_enchere;
int no_article;
int no_utilisateur;
/**
 * 
 */
public Enchere() {
	super();
}
/**
 * @param no_enchere
 * @param date_enchere
 * @param montant_enchere
 * @param no_article
 * @param no_utilisateur
 */
public Enchere(int no_enchere, Date date_enchere, int montant_enchere, int no_article, int no_utilisateur) {
	super();
	this.no_enchere = no_enchere;
	this.date_enchere = date_enchere;
	this.montant_enchere = montant_enchere;
	this.no_article = no_article;
	this.no_utilisateur = no_utilisateur;
}
public int getNo_enchere() {
	return no_enchere;
}
public void setNo_enchere(int no_enchere) {
	this.no_enchere = no_enchere;
}
public Date getDate_enchere() {
	return date_enchere;
}
public void setDate_enchere(Date date_enchere) {
	this.date_enchere = date_enchere;
}
public int getMontant_enchere() {
	return montant_enchere;
}
public void setMontant_enchere(int montant_enchere) {
	this.montant_enchere = montant_enchere;
}
public int getNo_article() {
	return no_article;
}
public void setNo_article(int no_article) {
	this.no_article = no_article;
}
public int getNo_utilisateur() {
	return no_utilisateur;
}
public void setNo_utilisateur(int no_utilisateur) {
	this.no_utilisateur = no_utilisateur;
}

}
