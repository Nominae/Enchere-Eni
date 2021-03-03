package fr.eni.enchere.javaproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.jdi.connect.spi.Connection;

public class CategorieDAOJdbcImpl {

	private static final String NUMCATEGORIE= "select * from CATEGORIES where no_categorie = ? ;";
	private static final String LIBELLECATEGORIE= "select * from CATEGORIES where libelle = ? ;";
	
	
// Connexion


// recherche d'une catégorie à partir du NUMCATEGORIE

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
			throw new DALException ("Impossible d'afficher le libellé", e)
		


		} finally {
			try {
				
			} catch (SQLException e2) {
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
