package fr.eni.enchere.javaproject.bll;

import fr.eni.enchere.javaproject.bo.Utilisateurs;
import fr.eni.enchere.javaproject.dal.DAOFactory;
import fr.eni.enchere.javaproject.dal.UtilisateursDAO;

public class UtilisateursManager {
	
	private UtilisateursDAO utilisateursDAO;
		
	public UtilisateursManager() {
		
		this.utilisateursDAO = DAOFactory.getUtilisateursDAO();
		
	}
	
	public Utilisateurs insertUser(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		
		Utilisateurs utilisateurs = new Utilisateurs();
	
		utilisateurs.setPseudo(pseudo);
		utilisateurs.setNom(nom);
		utilisateurs.setPrenom(prenom);
		utilisateurs.setEmail(email);
		utilisateurs.setTelephone(telephone);
		utilisateurs.setRue(rue);
		utilisateurs.setCodePostal(codePostal);
		utilisateurs.setVille(ville);
		utilisateurs.setMotDePasse(motDePasse);
		utilisateurs.setCredit(0);
		utilisateurs.setAdministrateur(false);
		this.utilisateursDAO.insertUser(utilisateurs);
		return utilisateurs;
		
	}
	
	/**void insertUser() {
			
		Utilisateurs utilisateurs = new Utilisateurs();
		
		utilisateurs.setPseudo("Lol");
		utilisateurs.setNom("HRQ");
		utilisateurs.setPrenom("Robb");
		utilisateurs.setEmail("Robin@gmail.com");
		utilisateurs.setTelephone("0606060606");
		utilisateurs.setRue("1 rue de lol");
		utilisateurs.setCodePostal("77");
		utilisateurs.setVille("Paris");
		utilisateurs.setMotDePasse("123456");
		utilisateurs.setCredit(0);
		utilisateurs.setAdministrateur(false);
		this.utilisateursDAO.insertUser(utilisateurs);
			
			
		}**/
	
}
