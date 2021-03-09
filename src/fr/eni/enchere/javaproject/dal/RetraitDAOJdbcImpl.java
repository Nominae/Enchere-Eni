package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.javaproject.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private final static String INSERT_RETRAIT = "insert into RETRAITS(no_article, rue, code_postal, ville) values (?,?,?,?);";
	private final static String SELECT_BY_ID_RETRAIT = "select * from RETRAITS where no_article = ?;";
	private final static String UPDATE_RETRAIT = "update RETRAITS SET no_article = ?, rue = ?, code_postal = ?, ville = ?";
	private final static String DELETE_RETRAIT = "delete from RETRAITS where noArticle = ?;";

	
	
	
//Méthode INSERT_RETRAIT
	public static Retrait InsertRetrait(Retrait AjoutRetrait) throws DALException {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Retrait retraitCree = null;
			
			try (Connection cnx = ConnectionProvider.getConnection()) {

				pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, AjoutRetrait.getNoArticle());
				pstmt.setString(2, AjoutRetrait.getRue());
				pstmt.setString(3, AjoutRetrait.getCodePostal());
				pstmt.setString(4, AjoutRetrait.getVille());

				rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					AjoutRetrait.setNoArticle(rs.getInt(1));
				}
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new DALException ("Impossible d'ajouter un retrait " + e.getMessage());
			
			}finally{
			
				try {
					if (rs != null) rs.close();
					if (pstmt!=null) pstmt.close();
					
			} catch (SQLException e) {
					throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
					
				}
		}
			return retraitCree;
	}
		

//Méthode UPDATE_RETRAIT 
	public void updateRetrait(Retrait MajRetrait) throws DALException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
			
			pstmt.setString(1, MajRetrait.getRue());
			pstmt.setString(2, MajRetrait.getCodePostal());
			pstmt.setString(3, MajRetrait.getVille());
			
			if(rs.next()) {
				MajRetrait.setNoArticle(rs.getInt(1));
			}
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			throw new DALException("Impossible de mettre à jour l'adresse retrait" + e.getMessage());
		
		}finally {
			
			try {
				
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
				
			}
		}
	} 	
		

//Méthode DELETE_RETRAIT 

	public void deleteRetrait(Retrait DeleteRetrait) throws DALException {
		
		PreparedStatement pstmt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			pstmt = cnx.prepareStatement(DELETE_RETRAIT);
		
	        pstmt.setInt(1, DeleteRetrait.getNoArticle());
	        
			pstmt.executeUpdate();
	
		}catch(SQLException e) {
			throw new DALException("Impossible de supprimer l'adresse de retrait" + e.getMessage());
		
		}finally {
			
			try {
				if (pstmt != null) pstmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
				
			}
		}

	}

//Méthode SELECT_BY_ID_RETRAIT
	 public static Retrait selectRetraitById(int noArticle) throws DALException {

		 ResultSet rs = null;
		 PreparedStatement pstmt = null;
		 
		 Retrait retrait = null;
		 
	     try (Connection cnx = ConnectionProvider.getConnection()){
	    	 pstmt = cnx.prepareStatement(SELECT_BY_ID_RETRAIT);
	    	 
	    	 pstmt.setInt(1, noArticle);
	         
	         if (rs.next()) {
	         	retrait.setNoArticle(rs.getInt(1));
	         }
	         
	         ResultSet resultSet = pstmt.executeQuery();
	         
	     }catch(SQLException e) {
				throw new DALException("Impossible de'afficher l'adresse de retrait" + e.getMessage());
			
			}finally {
				
				try {
					if (pstmt != null) pstmt.close();
					
				} catch (SQLException e) {
					throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
					
				}
			}   return retrait;

	} 

} 
