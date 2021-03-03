package fr.eni.enchere.javaproject.dal;

import java.util.ArrayList;

import fr.eni.enchere.javaproject.bo.ArticleVendu;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.bo.Retrait;

public class ArticleVenduDAOJdbcImpl {
	
	private final static String LISTER = "select * from ARTICLE_VENDU;";
	private final static String LISTERNUM = "select * from ARTICLE_VENDUS where no_article = ? ;";
	private final static String LISTERNOM = "select * from ARTICLE_VENDUS where nom_article LIKE ?;";
	private final static String INSERER = "insert into ARTICLE_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, visibilite) values (?;?;?;?;?;?;?;?);";
	private final static String SUPPRIMER = "delete from ARTICLE_VENTES where no_utilisateur = ? and no_article = ? ;";
	
	
	
	
	
	
	
	//	Methode Select de toute les ventes
	
		public static ArrayList<Ventes> listeVentes() throws DALException {
			
			
			
		}

}
