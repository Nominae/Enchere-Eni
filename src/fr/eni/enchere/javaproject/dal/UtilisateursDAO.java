package fr.eni.enchere.javaproject.dal;

import java.util.ArrayList;
import java.sql.SQLException;


import fr.eni.enchere.javaproject.bo.*;
import fr.eni.enchere.javaproject.utils.BusinessException;


public interface UtilisateursDAO {

	public void insertUser(Utilisateurs utilisateurs);
	public Utilisateurs updateUser(Utilisateurs utilisateurs);
	public void deleteUser(int noUtilisateur);
	public ArrayList<String> selectAllEmail();
	public ArrayList<String> selectAllPseudo();
	Utilisateurs selectLogin(String EmailouPseudo, String motDePasse);
	public boolean verifEmail(String email, int id);
	public boolean verifPseudo(String pseudo, int id);
	
	public void updateCredit(Utilisateurs utilisateur) throws BusinessException, SQLException;
	
	public Utilisateurs selectPseudo(String pseudo) throws BusinessException;
	
	public Utilisateurs selectId(int id) throws BusinessException;
	
}
 