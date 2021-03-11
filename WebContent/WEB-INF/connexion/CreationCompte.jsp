<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.javaproject.bo.Utilisateurs"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création de compte</title>
	</head>
	<body>
		<nav>
			<a href="<%=request.getContextPath()%>/NonConnecte">ENI-Encheres</a>
		</nav>
		<br>
		<br>
		<h2>Mon profil</h2>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/creationcompte" method="post" class="form">
			    <label for="pseudo">Pseudo : </label>
			    <input type="text" name="pseudo" id="pseudo" required>
			    
			    <label for="nom">Nom : </label>
			    <input type="text" nom" name="nom" id="nom" required><br>
			    
			    <label for="prenom">Prénom : </label>
			    <input type="text" name="prenom" id="prenom" required>
			    
			    <label for="email">Email : </label>
			    <input type="email" name="email" id="email" required><br>
			    
			    <label for="telephone">Téléphone : </label>
			    <input type="tel" name="telephone" id="telephone" required>
			    
			    <label for="rue">Rue : </label>
			    <input type="text" name="rue" id="rue" required><br>
			    
			    <label for="codePostal">Code postal : </label>
			    <input type="number" name="codePostal" id="codePostal" required>
			    
			    <label for="ville">Ville : </label>
			    <input type="text" name="ville" id="ville" required><br>
			    
			    <label for="motDePasse">Mot de passe : </label>
			    <input type="password" name="motDePasse" id="motDePasse" required>
			    
			    <label for="confirmMotDePasse">Confirmation : </label>
			    <input type="password" name="confirmMotDePasse" id="confirmMotDePasse" required><br>
			    
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageListeEncheresConnecte.html"><input type="submit" value="Créer"></a>
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageAccueilNonConnecte.html"><input type="reset" value="Annuler" ></a>
			    
			</form>
		</div>
	</body>
</html>