package fr.eni.enchere.javaproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

import fr.eni.enchere.javaproject.bo.Categorie;

public class CategorieDAOJdbcImpl {

	private static final String NUMCATEGORIE= "select * from CATEGORIES where no_categorie = ? ;";
	private static final String LIBELLECATEGORIE= "select * from CATEGORIES where libelle = ? ;";
	
	
// Connexion


// recherche d'une catégorie à partir du NUMCATEGORIE via le Libellé

	public static Categorie libelleCat (int id) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Categorie cate = null;
		
		try {
			con = getConnection();
			stmt = con.prepareStatement(NUMCATEGORIE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
					cate = new Categorie();
					cate.setLibelle(rs.getString("libelle"));
					
		} catch (SQLException e) {
			throw new DALException ("Impossible d'afficher le libellé", e);
		
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
			
				
			}
		}
		} return cate;
		
	}

// recherche d'une catégorie à partir du LIBELLECATEGORIE via le numéro de catégorie
	public static Categorie numCategorie (String libelle) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Categorie cate = null;
		
		try {
			con = getConnection();
			stmt = con.prepareStatement(LIBELLECATEGORIE);
			stmt.setInt(1, libelle);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
					cate = new Categorie();
					cate.setnumCategorie(rs.getString("no_categorie"));
					cate.setLibelle("libelle");
			}
		} catch (SQLException e) {
			throw new DALException ("Impossible d'afficher le numéro de catégorie", e);
		
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
			
				
			}
		}return cate;
		} 

	
	