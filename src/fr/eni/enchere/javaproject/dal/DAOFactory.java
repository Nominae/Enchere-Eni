package fr.eni.enchere.javaproject.dal;

public abstract class DAOFactory {
	
	public static UtilisateursDAO getUtilisateursDAO() {
		
		return new UtilisateursDAOJdbcImpl();
		
	}
	
	
	
}
