<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<nav>
			<a href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>		
		</nav>
		<br>
		<br>
		<h2>Mon profil</h2>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/ModificationProfil" method="post" class="form">
			    <label for="pseudo">Pseudo : ${utilisateur.pseudo}</label>
			    <input type="text" value="" name="pseudo" id="pseudo">
			    
			    <label for="nom">Nom : ${utilisateur.nom}</label>
			    <input type="text" value="" id="nom"><br>
			    
			    <label for="prénom">Prénom : ${utilisateur.prenom}</label>
			    <input type="text" value="" name="prénom" id="prénom">
			    
			    <label for="email">Email : ${utilisateur.email}</label>
			    <input type="email" value="" name="email" id="email"><br>
			    
			    <label for="telephone">Téléphone : ${utilisateur.telephone}</label>
			    <input type="tel" value="" name="telephone" id="telephone">
			    
			    <label for="rue">Rue : ${utilisateur.rue}</label>
			    <input type="text" value="" name="rue" id="rue"><br>
			    
			    <label for="codePostal">Code postal : ${utilisateur.codePostal}</label>
			    <input type="number" value="" name="codePostal" id="codePostal">
			    
			    <label for="ville">Ville : ${utilisateur.ville}</label>
			    <input type="text" value="" name="ville" id="ville"><br>
			    
			    <label for="motDePasse">Nouveau mot de passe : ${utilisateur.motDePasse}</label>
			    <input type="password" value="" name="motDePasse" id="motDePasse">
			    
			    <label for="verifMdp">Confirmation : ${utilisateur.motDePasse}</label>
			    <input type="password" value="" name="verifMdp" id="verifMdp"><br>
			    
			    <p>Crédit<b> ${utilisateur.credit}</b></p>
			    
			    <a href="<%=request.getContextPath()%>/AfficherProfil"><input type="submit" value="Enregistrer"></a>
			    <a href="<%=request.getContextPath()%>/NonConnecte"><input type="reset" value="Supprimer mon compte"></a>
			    
			</form>
		</div>
	</body>
</html>