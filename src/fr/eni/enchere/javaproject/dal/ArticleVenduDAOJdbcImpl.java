package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.eni.enchere.javaproject.bo.ArticleVendu;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.dal.DALException;

public class ArticleVenduDAOJdbcImpl {
	
	private final static String LISTER = "select * from ARTICLE_VENDU;";
	private final static String LISTERNUM = "select * from ARTICLE_VENDUS where no_article = ? ;";
	private final static String LISTERNOM = "select * from ARTICLE_VENDUS where nom_article LIKE ?;";
	private final static String INSERER = "insert into ARTICLE_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, visibilite) values (?;?;?;?;?;?;?;?);";
	private final static String SUPPRIMER = "delete from ARTICLE_VENDUS where no_utilisateur = ? and no_article = ? ;";
	private final static String LISTEVENTESCATEGORIE= "select * from ARTICLE_VENDUS where no_categorie = ? ;";
	
	
	
	
	
	
	//	Methode Select de toute les ventes
	
		public static ArrayList<ArticleVendu> listeVentes() throws DALException {
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<ArticleVendu> liste = new ArrayList<>();
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
					stmt=cnx.createStatement();
					rs=stmt.executeQuery(LISTER);
				
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
					if (stmt!=null) stmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ();
				}
			}
			return liste;		
		}	
		
		
		
		public static ArticleVendu  AfficherArticle(int noArticle) throws DALException {
			// Recherche d'un article avec le noArticle
			
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArticleVendu vente = new ArticleVendu();
			try {
				pstmt=cnx.prepareStatement(LISTERNUM);
				pstmt.setInt(1, noArticle);
				rs=pstmt.executeQuery();
				
				
				
				while (rs.next()){
				vente.setNoArticle(noArticle);
				vente.setNomArticle(rs.getString("nomarticle"));
				vente.setDescription(rs.getString("description"));
				vente.setDateFinEnchere(LocalDateTime.parse(rs.getString("date_fin_encheres")));
				vente.setPrixInitial(rs.getInt("prix_initial"));
				vente.setPrixVente(rs.getInt("prix_vente"));
				vente.setNoCategorie(rs.getInt("no_categorie"));
				vente.setNoUtilisateur(Integer.parseInt(rs.getString("no_utilisateur")));
				vente.setFinEnchere(rs.getInt("fin_enchere"));
				}
			
			} catch (SQLException e) {
				throw new DALException ();
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ();
				}
				}

			return vente;
			
			}
		
}	