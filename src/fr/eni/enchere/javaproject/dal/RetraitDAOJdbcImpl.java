package fr.eni.enchere.javaproject.dal;

public class RetraitDAOJdbcImpl {

	private static final String INSERT_RETRAIT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_RETRAIT = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ?";
	private static final String DELETE_RETRAIT = "DELETE FROM UTILISATEURS WHERE noUtilisateur = ?)";


Insert
SelectById
update - modif adresse
update - valider le retrait ??
delete retrait


RETRAITS
no_article
rue
code_postal
ville



package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.enchere.javaproject.bo.Retrait;

public class RetraitDAOJdbcImpl {

	private final static String INSERT_RETRAIT = "insert into RETRAITS(no_article, rue, code_postal, ville) values (?,?,?,?);";
	private final static String SELECT_BY_ID_RETRAIT = "select * from RETRAITS where no_article = ?;";
	private final static String UPDATE_RETRAIT = "update RETRAITS SET no_article = ?, rue = ?, code_postal = ?, ville = ?";
	private final static String DELETE_RETRAIT = "delete from RETRAITS where no_article = ?;";

	
	
	
//Méthode INSERT_RETRAIT
	public static Retrait InsertRetrait(int no_article) throws DALException, SQLException {
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Retrait retrait = new Retrait();
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(RECHERCHERETRAITVENTE);
			pstmt.setInt(1, numVente);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
			retrait.setNoVente(numVente);
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			
			throw new DALException ("Probleme - Impossible d'affichage d' un article ( RetraitDAO ) - " + e.getMessage());
		}finally{
		
				
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				

		return retrait;
	}
}
	
	
SelectbyId
Insert
Update
Delete









}









}
