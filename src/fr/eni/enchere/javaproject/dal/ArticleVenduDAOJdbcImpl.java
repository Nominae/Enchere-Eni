package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
	
		public static ArrayList<ArticleVendu> listeVentes() throws DALException {
			Connection connexion=null;
			Statement statement=null;
			ResultSet rs=null;
			ArrayList<ArticleVendu> liste = new ArrayList<>();
			
			try {
			//getconnection
				
				while (rs.next()) {
					ArticleVendu article = new ArticleVendu();
					article.setPrixVente(rs.getInt("no_article"));
					article.setNomArticle(rs.getString("nom_article"));
					article.setDescription(rs.getString("description"));
					article.setDateDebutEnchere(LocalDateTime.parse(rs.getString("date_debut_encheres")));
					article.setDateFinEnchere(LocalDateTime.parse(rs.getString("date_fin_encheres")));
					article.setPrixInitial(rs.getInt("prix_initial"));
					article.setPrixVente(rs.getInt("prix_vente"));
					article.setNoUtilisateur(rs.getInt("no_utilisateur"));
					article.setNoCategorie(rs.getInt("no_categorie"));
					liste.add(article);

				}
			} catch (SQLException e) {		
					throw new DALException ();	
			}finally{
				try{
					if (statement!=null) statement.close();
					if (connexion!=null) connexion.close();
				} catch (SQLException e) {
						throw new DALException ();
				}
			}
			return liste;		
		}	
		
		
			
			
			
			
			
		

}
