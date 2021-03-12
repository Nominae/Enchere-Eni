package fr.eni.enchere.javaproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.javaproject.bo.Retrait;
import fr.eni.enchere.javaproject.utils.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String SELECT_VERIF_EXISTANCE = "select * from RETRAITS where rue=? and code_postal=? and ville=? ";
	private static final String INSERT = "insert into RETRAITS (rue, code_postal, ville) " + "values(?,?,?)";
	private static final String SELECT_ID = "select * from RETRAITS where no_retrait=?";
	private static final String UPDATE = "update RETRAITS Set rue= ?,code_postal=?, ville=?  where no_retrait=?";
	private static final String DELETE = "delete from RETRAITS where no_retrait=?";
	private static final String SELECT_ALL = "select * from RETRAITS";




	
	@Override
	public Retrait selectId(int id) throws BusinessException {
		Retrait retraits = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				retraits = this.retraitsConstructeur(rs);
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

		return retraits;
	}




	@Override
	public Retrait insert(Retrait retrait) throws BusinessException {
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		Retrait retraitCourant = this.selectVerifExistant(retrait);

		if (retraitCourant == null) {
			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				stm.setString(1, retrait.getRue());
				stm.setString(2, retrait.getCodePostal());
				stm.setString(3, retrait.getVille());
				stm.executeUpdate();
				ResultSet rs = stm.getGeneratedKeys();

				if (rs.next()) {
					retrait.setNoRetrait(rs.getInt(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERTION_RETRAIT);
				throw businessException;
			}
			return retrait;
		} else {
			return retraitCourant;
		}
	}
	
	private Retrait retraitsConstructeur(ResultSet rs) throws SQLException {
		Retrait retrait = new Retrait();
		retrait.setNoRetrait(rs.getInt("no_retrait"));
		retrait.setRue(rs.getString("rue"));
		retrait.setCodePostal(rs.getString("code_postal"));
		retrait.setVille(rs.getString("ville"));
		return retrait;
	}
	
	public Retrait selectVerifExistant(Retrait retrait) throws BusinessException {
		if (retrait != null) {
			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement stm = cnx.prepareStatement(SELECT_VERIF_EXISTANCE);
				stm.setString(1, retrait.getRue());
				stm.setString(2, retrait.getCodePostal());
				stm.setString(3, retrait.getVille());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					retrait = this.retraitsConstructeur(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
				throw businessException;
			}
			if (retrait.getNoRetrait() == null) {
				retrait = null;
			}

		}
		return retrait;
	}
	
	@Override
	public void update(Retrait retrait, int id) throws BusinessException, SQLException {
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setString(1, retrait.getRue());
			stm.setString(2, retrait.getCodePostal());
			stm.setString(3, retrait.getVille());
			stm.setInt(4, id);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO ERREUR UPDATE NON VALIDE 
			e.printStackTrace();
		}

	}
		
	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(DELETE);
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR);
			throw businessException;
		}

	}
	@Override
	public List<Retrait> selectAll() throws BusinessException {
		List<Retrait> retrait = new ArrayList<Retrait>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				retrait.add(this.retraitsConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrait;
	}



} 