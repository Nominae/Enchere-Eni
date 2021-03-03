package fr.eni.enchere.javaproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

import fr.eni.enchere.javaproject.bo.Categorie;

public class CategorieDAOJdbcImpl {

	private final static String SELECT_ALL = "select * from CATEGORIES";
	private final static String SELECT_BY_ID = "select * from CATEGORIES where no_categorie = ?";
	private final static String UPDATER = "update from CATEGORIES where no_categorie = ? and libellé = ? ;";
	private final static String INSERER = "insert into CATEGORIES(no_categorie, libelle (?;?);";
	private final static String SUPPRIMER = "delete from CATEGORIES where no_categorie = ? and libellé = ? ;";


// Méthode SelectAll

	public static List<Categorie> selectAll() throws DALException {
		List<Categorie> ListeCategorie = new ArrayList<Categorie>();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			{
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				ListeCategorie.add(categorie);
			}
		} catch (Exception e) {
			throw new DALException("Impossible d'afficher la catégorie", e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (cnx != null) cnx.close();
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko", e);
			}
		}
		return ListeCategorie;
	}

}
// Méthode SelectById
	public static Categorie selectByIdCat(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			{
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'afficher la catégorie", e);
		
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (cnx != null) cnx.close();
				
			} catch (SQLException e) {
				throw new DALException("Fermeture de la connexion BDD ko", e);
			}
		}
		return categorie;
	}

}

// Méthode Update

	public void update(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
				pstmt = cnx.prepareStatement(UPDATER);
					pstmt.setInt(1, categorie.getNoCategorie());
					pstmt.setString(2, categorie.getLibelle());
					}

						pstmt.executeUpdate();

					} catch (SQLException e) {
						throw new DALException("Impossible de mettre à jour la catégorie", e);

					}
	}