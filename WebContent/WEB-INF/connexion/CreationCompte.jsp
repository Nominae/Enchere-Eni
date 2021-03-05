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
			<a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageAccueilNonConnecte.html">ENI-Encheres</a>
		</nav>
		<br>
		<br>
		<h2>Mon profil</h2>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/connexion" method="post" class="form">
			    <label for="pseudo">Pseudo : </label>
			    <input type="text" value="<%=request.getParameter("pseudo")%>" name="pseudo" id="pseudo" required>
			    
			    <label for="nom">Nom : </label>
			    <input type="text" value="<%=request.getParameter("nom")%>" name="nom" id="nom" required><br>
			    
			    <label for="prenom">Prénom : </label>
			    <input type="text" value="<%=request.getParameter("prenom")%>" name="prenom" id="prenom" required>
			    
			    <label for="email">Email : </label>
			    <input type="email" value="<%=request.getParameter("email")%>" name="email" id="email" required><br>
			    
			    <label for="telephone">Téléphone : </label>
			    <input type="tel" value="<%=request.getParameter("telephone")%>" name="telephone" id="telephone" required>
			    
			    <label for="rue">Rue : </label>
			    <input type="text" value="<%=request.getParameter("rue")%>" name="rue" id="rue" required><br>
			    
			    <label for="codePostal">Code postal : </label>
			    <input type="number" value="<%=request.getParameter("codePostal")%>" name="codePostal" id="codePostal" required>
			    
			    <label for="ville">Ville : </label>
			    <input type="text" value="<%=request.getParameter("ville")%>" name="ville" id="ville" required><br>
			    
			    <label for="motDePasse">Mot de passe : </label>
			    <input type="password" value="<%=request.getParameter("motDePasse")%>" name="motDePasse" id="motDePasse" required>
			    
			    <label for="motDePasse">Confirmation : </label>
			    <input type="password" value="<%=request.getParameter("motDePasse")%>" name="motDePasse" id="motDePasse" required><br>
			    
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageListeEncheresConnecte.html"><input type="submit" value="Créer"></a>
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageAccueilNonConnecte.html"><input type="reset" value="Annuler" ></a>
			    
			</form>
		</div>
	</body>
</html>