package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import fr.eni.enchere.javaproject.bo.Utilisateurs;

public class UtilisateursDAOJdbcImpl implements UtilisateursDAO{
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ?";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE noUtilisateur = ?)";
	private static final String SELECT_EMAIL = "SELECT email FROM UTILISATEURS";
	private static final String SELECT_PSEUDO = "SELECT pseudo FROM UTILISATEURS";
	
	
	
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
				
				if(rs.next()) {
					utilisateurs.setNoUtilisateur(rs.getInt(1));
				}
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}finally {
				
				try {
					
					if(rs!=null) {
						rs.close();
					}
					
					if(pstmt!=null) {
						pstmt.close();
					}
					
					if(cnx!=null) {
						cnx.close();
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				}
			}
		}
	
	
	@Override
	public void updateUser(Utilisateurs utilisateurs) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;
		
		try{
			
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
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
			
			if(rs.next()) {
				utilisateurs.setNoUtilisateur(rs.getInt(1));
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(cnx!=null) {
					cnx.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		}
	}

	@Override
	public void deleteUser(int noUtilisateur) {
		
		int id = noUtilisateur;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(cnx!=null) {
					cnx.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		}
		
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
			
		}finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(cnx!=null) {
					cnx.close();
				}
				
			} catch (Exception e2) {
				
				e2.printStackTrace();
				
			}
			
		}
		
		return listMail;
		
	}
}

																		/**Connection connection = MyResource.getConnection();
																		query = "UPDATE users SET password = ? WHERE mail = ?";
																		
																		stmt = connection.prepareStatement(query);
																		stmt.setString(1,jsonObject.getString("password"));
																		stmt.setString(2,jsonObject.getString("mail"));
																		stmt.executeUpdate();**/
