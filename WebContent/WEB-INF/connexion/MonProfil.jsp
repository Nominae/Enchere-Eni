<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon Profil</title>
	</head>
	<body>
		<nav>
			<a href="<%=request.getContextPath()%>/AcceuilConnecte">ENI-Encheres</a>		
		</nav>
		<div>
			<p>Pseudo : <span>${utilisateur.pseudo}</span></p>
			<p>Nom : <span>${utilisateur.nom}</span></p>
			<p>Prénom : <span>${utilisateur.prenom}</span></p>
			<p>Email : <span>${utilisateur.email}</span></p>
			<p>Teléphone : <span>${utilisateur.telephone}</span></p>
			<p>Rue : <span>${utilisateur.rue}</span></p>
			<p>Code Postal : <span>${utilisateur.codePostal}</span></p>
			<p>Ville : <span>${utilisateur.ville}</span></p>
			<p>Crédit : <span>${utilisateur.credit}</span></p>
		</div>
		<br>
		<a href="<%=request.getContextPath()%>/ModificationProfil">Modifier</a>
	</body>
</html>