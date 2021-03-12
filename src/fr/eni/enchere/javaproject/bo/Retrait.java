package fr.eni.enchere.javaproject.bo;


public class Retrait  {

	
	 Integer noRetrait;
	 String rue;
	 String codePostal;
	 String ville;
	
	
// Constructeur par défaut
	public Retrait() {
		
	}

// Constructeur prenant en compte tous les paramètres
	public Retrait(int noRetrait, String rue, String codePostal, String ville) {
		super();
		this.noRetrait = noRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
// Getters et Setters 
	public Integer getNoRetrait() {
		return noRetrait;
	}

	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
// Méthode ToString
	@Override
	public String toString() {
		return "Retrait ->\n Num Article :" + noRetrait + "\n rue :" + rue + "\n Code Postal :" + codePostal + "\n Ville :" + ville;
	}

	
	
	


	
}