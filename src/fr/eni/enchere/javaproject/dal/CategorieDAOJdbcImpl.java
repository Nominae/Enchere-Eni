package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Categorie;
import fr.eni.enchere.javaproject.utils.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private final static String SELECT_ALL_CAT = "select * from CATEGORIES";
	private final static String SELECT_BY_ID_CAT = "select * from CATEGORIES where no_categorie = ?;";
	private final static String UPDATE_CAT = "update from CATEGORIES where no_categorie = ? and libellé = ? ;";
	private final static String INSERT_CAT = "insert into CATEGORIES(no_categorie, libelle (?;?);";
	private final static String DELETE_CAT_BY_NUM = "delete from CATEGORIES where no_categorie = ?;";
	private final static String DELETE_CAT_BY_LIBELLE = "delete from CATEGORIES where libellE = ?;";
	private static final String SELECT_ALL = "select * from CATEGORIES";



// Méthode SelectAll
	@Override
	public List<Categorie> selectAllcat() throws DALException {
		List<Categorie> ListeCategorie = new ArrayList<Categorie>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_CAT);
			
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				ListeCategorie.add(categorie);
			}
		} catch (Exception e) {
			throw new DALException("Impossible d'afficher les catégories" + e.getMessage());
		
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
			}
		}
		return ListeCategorie;
		}

	
// Méthode SelectById
	public Categorie selectByIdCat(int noCategorie) throws DALException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Categorie categorie = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			pstmt = cnx.prepareStatement(SELECT_BY_ID_CAT);
			
			pstmt.setInt(1, noCategorie);
			rs = pstmt.executeQuery();
			//TODO: CATEGORIE NULL
			while (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'afficher la catégorie par son numéro" + e.getMessage());
		
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();

				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
			}
		}
		return categorie;
	}

// Méthode UPDATE_CAT

	public void update(Categorie categorie) throws DALException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
		
			pstmt = cnx.prepareStatement(UPDATE_CAT);
			
			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.setString(2, categorie.getLibelle());
			
			if(rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour la catégorie" + e.getMessage());

		}
	}
	
// méthode INSERT_CAT
					
	public void insertCategorie(Categorie categorie) throws DALException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			pstmt = cnx.prepareStatement(INSERT_CAT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, categorie.getLibelle());
			rs = pstmt.getGeneratedKeys();
		
	        if(rs.next()) {
	        	categorie.setNoCategorie(rs.getInt(1));
	        }
	     
	        pstmt.executeUpdate();
		
		}catch(SQLException e) {
			throw new DALException("Impossible d'ajouter une catégorie" + e.getMessage());
		
		}finally {
			
			try {
				
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
				
			}
		}
	}  
	      
		

// méthode DELETE_CAT_BY_NUM

	public void deleteCategorie(int noCategorie) throws DALException {
		
		PreparedStatement pstmt = null;
	
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			pstmt = cnx.prepareStatement(DELETE_CAT_BY_NUM);
			pstmt.setInt(1, noCategorie);
			
			pstmt.executeUpdate();
		
		}catch(SQLException e) {
			throw new DALException("Impossible de supprimer une catégorie par son numéro" + e.getMessage());
	        		        
		}finally {
			
			try {
	
				if (pstmt != null) pstmt.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
				
			}
		}
	}  
	   

// Méthode DELETE_CAT_BY_LIBELLE
	
	public void deleteCategorie(String libelle) throws DALException {
		
		PreparedStatement pstmt = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){

			pstmt = cnx.prepareStatement(DELETE_CAT_BY_LIBELLE);
			pstmt.setString(1, libelle);
				
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			throw new DALException("Impossible de supprimer une catégorie par son numéro" + e.getMessage());
		        		        
		}finally {
				
			try {
		
				if (pstmt != null) pstmt.close();
					
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko" + e.getMessage());
					
				}
			}
		}
		
		@Override
	public List<Categorie> selectAll() throws BusinessException, SQLException {
		List<Categorie> categories = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				categories.add(this.categorieConstructeur(rs));
			}
		}
		return categories;
	}
	private Categorie categorieConstructeur(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(rs.getInt("no_categorie"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}
	}
		