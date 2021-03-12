package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.utils.BusinessException;

public class UtilisateursDAOJdbcImpl implements UtilisateursDAO {

	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_EMAIL = "SELECT email FROM UTILISATEURS";
	private static final String SELECT_PSEUDO = "SELECT pseudo FROM UTILISATEURS";
	//TODO: SELECT PASSWORD utilisé ou non ?
	private static final String SELECT_PASSWORD = "SELECT motDePasse FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_LOGIN = "SELECT * from UTILISATEURS where (email = ? or pseudo = ?) AND mot_de_passe = ?";
	private static final String VERIF_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?  AND no_utilisateur <> ?";
	private static final String VERIF_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?  AND no_utilisateur <> ?";

	// AJOUT
	private static final String UPDATE_CREDIT = "update UTILISATEURS Set credit=? where no_utilisateur=?";
	private static final String SELECT_ID = "select * from UTILISATEURS where no_utilisateur=?";

	@Override
	public void insertUser(Utilisateurs utilisateurs) {

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;

		try {

			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateurs.getPseudo());
			pstmt.setString(2, utilisateurs.getNom());
			pstmt.setString(3, utilisateurs.getPrenom());
			pstmt.setString(4, utilisateurs.getEmail());
			pstmt.setString(5, utilisateurs.getTelephone());
			pstmt.setString(6, utilisateurs.getRue());
			pstmt.setString(7, utilisateurs.getCodePostal());
			pstmt.setString(8, utilisateurs.getVille());
			pstmt.setString(9, utilisateurs.getMotDePasse());
			pstmt.setInt(10, 0);
			pstmt.setBoolean(11, false);

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				utilisateurs.setNoUtilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (cnx != null) {
					cnx.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
	}

	@Override
	public Utilisateurs updateUser(Utilisateurs utilisateur) {

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;
		
		 Utilisateurs utilisateurRecupere = null;
		
		try {

			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_USER);
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());

			pstmt.executeUpdate();

			try {
				utilisateurRecupere = DAOFactory.getUtilisateursDAO().selectId(utilisateur.getNoUtilisateur());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("1");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("2");
		} finally {

			try {

				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (cnx != null) {
					cnx.close();
				}

			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		return utilisateurRecupere;
	}

	@Override
	public boolean deleteUser (int noUtilisateur) {
		boolean verifDelete = false;
		Utilisateurs utilisateur = null;
		int id = noUtilisateur;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;
		
		try {
			System.out.println("On rentre dans le try");
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			try {
				utilisateur = DAOFactory.getUtilisateursDAO().selectId(id);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if(utilisateur == null){
		        verifDelete = true;
		      }else {
		        verifDelete = false;
		      }
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (cnx != null) {
					cnx.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
		if(verifDelete == true) {
			System.out.println("CPASLAMERDE");
		}
		return verifDelete;
	}

	public ArrayList<String> selectAllEmail() {

		ResultSet rs = null;
		Statement stmt = null;
		Connection cnx = null;

		ArrayList<String> listMail = new ArrayList<String>();

		try {

			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();

			rs = stmt.executeQuery(SELECT_EMAIL);

			while (rs.next()) {

				listMail.add(rs.getString("email"));

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}

			} catch (Exception e2) {

				e2.printStackTrace();

			}

		}

		return listMail;

	}

	public ArrayList<String> selectAllPseudo() {

		ResultSet rs = null;
		Statement stmt = null;
		Connection cnx = null;

		ArrayList<String> listPseudo = new ArrayList<String>();

		try {

			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();

			rs = stmt.executeQuery(SELECT_PSEUDO);

			while (rs.next()) {

				listPseudo.add(rs.getString("pseudo"));

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}

			} catch (Exception e2) {

				e2.printStackTrace();

			}

		}

		return listPseudo;

	}

	public Utilisateurs selectLogin(String EmailouPseudo, String motDePasse) {

		Utilisateurs utilisateur = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;

		try {
			cnx = ConnectionProvider.getConnection();

			pstmt = cnx.prepareStatement(SELECT_LOGIN);
			pstmt.setString(1, EmailouPseudo);
			pstmt.setString(2, EmailouPseudo);
			pstmt.setString(3, motDePasse);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = utilisateurBuilder(rs);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return utilisateur;
	}

	private Utilisateurs utilisateurBuilder(ResultSet rs) {

		Utilisateurs utilisateur = new Utilisateurs();

		try {

			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return utilisateur;

	}

	public boolean verifEmail(String email, int id) {

		boolean verifEmail = false;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;

		try {

			cnx = ConnectionProvider.getConnection();

			pstmt = cnx.prepareStatement(VERIF_EMAIL);
			pstmt.setString(1, email);
			pstmt.setInt(2, id);

			rs = pstmt.executeQuery();

			verifEmail = rs.next();

			System.out.println(verifEmail);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return verifEmail;

	}

	public boolean verifPseudo(String pseudo, int id) {

		boolean verifPseudo = false;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;

		try {

			cnx = ConnectionProvider.getConnection();

			pstmt = cnx.prepareStatement(VERIF_PSEUDO);
			pstmt.setString(1, pseudo);
			pstmt.setInt(2, id);

			rs = pstmt.executeQuery();

			verifPseudo = rs.next();

			System.out.println(verifPseudo);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return verifPseudo;

	}

	@Override
	public void updateCredit(Utilisateurs utilisateur) throws BusinessException, SQLException {
		
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE_CREDIT);
			stm.setDouble(1, utilisateur.getCredit());
			stm.setInt(2, utilisateur.getNoUtilisateur());
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateurs selectPseudo(String pseudo) throws BusinessException {
		
		Utilisateurs utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_PSEUDO);
			stm.setString(1, pseudo);
			stm.setString(2, pseudo);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				utilisateur = this.utilisateurConstructeur(rs);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_PSEUDO_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		return utilisateur;
		
	}

	@Override
	public Utilisateurs selectId(int id) throws BusinessException {
		
		Utilisateurs utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				utilisateur = this.utilisateurConstructeur(rs);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ID_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return utilisateur;
	}

	private Utilisateurs utilisateurConstructeur(ResultSet rs) throws SQLException {
		
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
		return utilisateur;
		
	}

}

																		/**Connection connection = MyResource.getConnection();
																		query = "UPDATE users SET password = ? WHERE mail = ?";
																		
																		stmt = connection.prepareStatement(query);
																		stmt.setString(1,jsonObject.getString("password"));
																		stmt.setString(2,jsonObject.getString("mail"));
																		stmt.executeUpdate();**/
