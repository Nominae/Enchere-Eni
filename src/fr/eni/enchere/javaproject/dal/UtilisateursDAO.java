package fr.eni.enchere.javaproject.dal;

import fr.eni.enchere.javaproject.bo.*;

public interface UtilisateursDAO {

	public void insertUser(Utilisateurs utilisateurs);
	public void updateUser(Utilisateurs utilisateurs);
	public void deleteUser(int noUtilisateur);
	
	
}