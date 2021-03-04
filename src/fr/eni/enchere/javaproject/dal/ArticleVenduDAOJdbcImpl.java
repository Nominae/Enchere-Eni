package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.Date;
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
			Connection cnx = null;
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<ArticleVendu> liste = new ArrayList<>();
			
			try {
					cnx = ConnectionProvider.getConnection();
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
					throw new DALException ("Probleme - liste ventes - " + e.getMessage());	
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
				cnx = ConnectionProvider.getConnection();
				pstmt=cnx.prepareStatement(LISTERNUM);
				pstmt.setInt(1, noArticle);
				rs=pstmt.executeQuery();
				
				
				
				while (rs.next()){
				vente.setNoArticle(noArticle);
				vente.setNomArticle(rs.getString("nom_article"));
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
		
		public static void ajouter(ArticleVendu article)  throws DALException{
			Connection cnx=null;
			PreparedStatement pstmt=null;
		// liste dans la requète : nomarticle, description, date_fin_encheres, prix_initial, prix_vente ,no_utilisateur, no_categorie
			try{
				cnx = ConnectionProvider.getConnection();				
				pstmt=cnx.prepareStatement(INSERER, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setObject(3, article.getDateFinEnchere());
				pstmt.setInt(4, article.getPrixInitial());
				pstmt.setInt(5, article.getPrixVente());
				pstmt.setInt(6, article.getNoUtilisateur());
				pstmt.setInt(7, article.getNoCategorie());
				if (article.isPublier()) {
					pstmt.setInt(8, 1);
				}else {
					pstmt.setInt(8, 0);
				};
				pstmt.executeUpdate();
				
				
			
				 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                article.setNoArticle(generatedKeys.getInt(1));
			               
			            }
			            else {
			                throw new SQLException("Impossible de creer la vente, aucun ID obtenu.");
			            }
			        }				
			} catch (SQLException e){
				throw new DALException ("Probleme - ajouterVente - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch (SQLException e){
					throw new DALException ("Probleme - fermerConnexion - " + e.getMessage());
				}		
			}
		}
		
		public static ArrayList<ArticleVendu> listeVenteArticle(String nomArticle) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<ArticleVendu> listeVentes = new ArrayList<>();
			
			try {
				cnx = ConnectionProvider.getConnection();
				pstmt=cnx.prepareStatement(LISTERNOM);
				pstmt.setString(1, "%"+nomArticle+"%");
				rs=pstmt.executeQuery();
				
				while (rs.next()){
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_vente"));
					article.setNoUtilisateur(rs.getInt("no_utilisateur"));
					article.setNomArticle(rs.getString("nom_article"));
					article.setDescription(rs.getString("description"));
					article.setDateFinEnchere(LocalDateTime.parse(rs.getString("date_fin_encheres")));
					article.setPrixInitial(rs.getInt("prix_initial"));
					article.setPrixVente(rs.getInt("prix_vente"));
					article.setNoCategorie(rs.getInt("no_categorie"));
					listeVentes.add(article);

					
				}
			} catch (SQLException e) {
				
					throw new DALException ("Probleme - listeVentesArticle - " + e.getMessage());
			
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeVentes;		
		}
		
		public static int supprimerVente(int noArticle, int noUtilisateur) throws DALException{
			Connection cnx=null;
			PreparedStatement pstmt = null;
			int nblig=0;
			try{
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(SUPPRIMER);
				pstmt.setInt(1, noUtilisateur);
				pstmt.setInt(2, noArticle);
				
				nblig= pstmt.executeUpdate();
				return nblig;
			}catch(SQLException e){
				throw new DALException ("Probleme - supprimer Vente - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch(SQLException e){
					throw new DALException ("DAL - fermerConnexion - " + e.getMessage());
				}
			}
		}
		
		public static ArrayList<ArticleVendu> ventesCategorie (int no_categorie) throws DALException {
			
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<ArticleVendu> listeVentes = new ArrayList<>();
			
			try {
				cnx = ConnectionProvider.getConnection();
				pstmt=cnx.prepareStatement(LISTEVENTESCATEGORIE);
				pstmt.setInt(1, no_categorie);
				rs=pstmt.executeQuery();
				
				while (rs.next()) {
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_vente"));
					article.setNomArticle(rs.getString("nom_article"));
					article.setDescription(rs.getString("description"));
					article.setDateFinEnchere(LocalDateTime.parse(rs.getString("date_fin_encheres")));
				//	article.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
					article.setPrixInitial(rs.getInt("prix_initial"));
					article.setPrixVente(rs.getInt("prix_vente"));
					article.setNoCategorie(rs.getInt("no_categorie"));
					article.setNoUtilisateur(rs.getInt("no_utilisateur"));
					listeVentes.add(article);
				}
			} catch (SQLException e) {		
					throw new DALException ("Probleme - ventesCategorie - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeVentes;		
		}
		
}	