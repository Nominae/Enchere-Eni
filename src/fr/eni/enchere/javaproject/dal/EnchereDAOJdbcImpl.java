package fr.eni.enchere.javaproject.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.enchere.javaproject.bo.Enchere;
import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.bo.ArticleVendu;
import fr.eni.enchere.javaproject.dal.ConnectionProvider;
import java.util.ArrayList;

/**
 * 
 * @author jbodet2019
 *
 */

public class EnchereDAOJdbcImpl {
	
	private final static String RECHERCHER_UN_NO_UTILISATEUR = "select no_utilisateur from ENCHERES where no_vente = ? ORDER BY montantEnchere DESC;";
	private final static String INSERERENCHERE = "insert into ENCHERES(date_enchere, no_utilisateur, no_vente, montantEnchere) values (?,?,?,?);";
	private final static String VERIFICATION_UTILISATEUR_ENCHERIR = "select * FROM ENCHERES WHERE no_utilisateur = ? AND no_vente = ? ;";
	private final static String RECHERCHE_NO_ARTICLE_PAR_NO_UTILISATEUR = "select no_vente FROM ENCHERES WHERE no_utilisateur = ? ;";
	private final static String RECHERCHELISTEENCHEREPARNO_ARTICLE = "select * FROM ENCHERES WHERE no_vente = ? ;";
	private final static String MODIFIER = "update ENCHERES set date_enchere = ?, montantEnchere = ? WHERE no_utilisateur= ? AND no_vente = ?;";
	private final static String SUPPRESSION_ENCHERE = "delete FROM ENCHERES WHERE no_utilisateur= ? AND no_vente = ?;";
	private final static String SUPPRESSION_ENCHERE_COMPLETE = "delete FROM ENCHERES WHERE no_vente = ?;";
	private final static String RECHERCHER_MEILLEURE_VENTE = "select * from ENCHERES where no_vente = ? ORDER BY montantEnchere DESC";

	public static Enchere rechercheUnUserEncheri(int noArticle) throws DALException {
		// Méthode recherchant un noUtilisateur d'une personne qui encheri
		
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Enchere enchere = new Enchere();
		
		
		try {
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(RECHERCHER_UN_NO_UTILISATEUR);
			pstmt.setInt(1, noArticle);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {	
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
			
			}
		} catch (SQLException e) {
			
			throw new DALException ("Probleme - Impossible de prendre un n° utilisateur ( EnchereDAO ) - " + e.getMessage());
		}finally{
			
			try {
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		return enchere;
	
	}
	
	//méthode pour créer une enchère
	
	
	
	public static void ajouterEnchere(String date, Enchere enchere) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
	
		try{
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(INSERERENCHERE);
			pstmt.setString(1, date);
			pstmt.setInt(2, enchere.getNoUtilisateur());
			pstmt.setInt(3, enchere.getNoArticle()); 
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			

		} catch (SQLException e){
			throw new DALException ("Probleme - ajouter une enchère - " + e.getMessage());
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			}catch (SQLException e){
				throw new DALException ("Probleme - fermerConnexion - " + e.getMessage());
			}		
		}
	}
	
	public static Enchere verificationUserEncherir(int noArticle, int numUser) throws DALException {
		// Permet de vérifier si l'utilisateur à enchéri au moins une fois

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere verificationEnchere = new Enchere();
		
		try {
			ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(VERIFICATION_UTILISATEUR_ENCHERIR);
			pstmt.setInt(1, numUser);
			pstmt.setInt(2, noArticle);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				verificationEnchere.setNoUtilisateur(numUser);
				verificationEnchere.setNoArticle(noArticle);
				verificationEnchere.setMontantEnchere(rs.getInt("montantEnchere"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Probleme - VerificationUserEncherir - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
		
		return verificationEnchere;
	}
	
	public static ArrayList<Integer> quelleVenteEncherie (int no_user) throws DALException {
		//chercher les ventes sur lesquelles l'utilisateur a ench�ri
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(RECHERCHE_NO_ARTICLE_PAR_NO_UTILISATEUR);
			
			pstmt.setInt(1, no_user);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("no_article"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Probleme - VerificationUserEncherir - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		return list;
	}
	
	
	
	
	//Méthode pour obtenir la liste d'enchère à partir du num vente
	public static ArrayList<Enchere> listeEncheres (int noArticle) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Enchere> listeEnchere = new ArrayList<>();
			
			try {
				ConnectionProvider.getConnection();
				pstmt=cnx.prepareStatement(RECHERCHELISTEENCHEREPARNO_ARTICLE);					
				pstmt.setInt(1, noArticle);	
				rs=pstmt.executeQuery();
				
				while (rs.next()) {
					Enchere enchereZ = new Enchere();
					
					enchereZ.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchereZ.setNoArticle(noArticle);
					enchereZ.setMontantEnchere(rs.getInt("montant_enchere"));
					listeEnchere.add(enchereZ);
					
				}
			} catch (SQLException e) {		
					throw new DALException ("Probleme - listeEnchere - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeEnchere;		
		}

	public static void modificationEnchere(String dateTime, int noUtilisateur, int noArticle,
			int proposEnchere) throws DALException {
		// Permet de modifier une enchère
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, dateTime);
			pstmt.setInt(2, proposEnchere);
			pstmt.setInt(3, noUtilisateur);
			pstmt.setInt(4, noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - modifierEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
	}

	public static void suppressionEnchere(int noUtilisateur, int noArticle) throws DALException {
		//Suppression d'une enchère pour ensuite l'ajouter de nouveau avec les bonnes valeurs
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(SUPPRESSION_ENCHERE);
			pstmt.setInt(1, noUtilisateur);
			pstmt.setInt(2, noArticle);	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - suppressionEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
	}

	public static void ajouterEnchereSuiteSuppression(String date, int noUtilisateur, int noArticle, int proposEnchere) throws DALException {
		//Création d'une enchère suite à la suppression dans la base
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
	
		try{
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(INSERERENCHERE);
			pstmt.setString(1, date);
			pstmt.setInt(2, noUtilisateur);
			pstmt.setInt(3, noArticle); 
			pstmt.setInt(4, proposEnchere);
			pstmt.executeUpdate();
			

		} catch (SQLException e){
			throw new DALException ("Probleme - ajouter une enchère suite suppression - " + e.getMessage());
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			}catch (SQLException e){
				throw new DALException ("Probleme - fermerConnexion suite suppression - " + e.getMessage());
			}		
		}
	
		
	}

	public static void suppressionEnchereComplete(int noArticle) throws DALException {
		// Suppression totale de l'enchere avec tous les users.
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(SUPPRESSION_ENCHERE_COMPLETE);
			pstmt.setInt(1, noArticle);	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - suppressionEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
		
		
	}

	public static Enchere rechercheMeilleureMontant(int noArticle) throws DALException {
		// Recherche de la meilleure vente afin de MAJ prixVente de la table VENTE
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Enchere enchere = new Enchere();
		
		
		try {
			ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(RECHERCHER_MEILLEURE_VENTE);
			pstmt.setInt(1, noArticle);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {	
				enchere.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setNoArticle(noArticle);
			
			}
		} catch (SQLException e) {
			
			throw new DALException ("Probleme - Impossible de prendre un n° utilisateur ( EnchereDAO ) - " + e.getMessage());
		}finally{
			
			try {
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
	}
		return enchere;
	}
	
}
	