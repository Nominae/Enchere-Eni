<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Connexion</title>
	</head>
	<body>
		<nav>
			<a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageAccueilNonConnecte.html">ENI-Encheres</a>
		</nav>
		<br>
		<br>
		<div>
			<form action="" method="get" class="form">
			    <label for="pseudo">Pseudo : </label>
			    <input type="text" value="<%=request.getParameter("pseudo")%>" name="pseudo" id="pseudo" required><br>
			    
			    <label for="email">Email : </label>
			    <input type="email" value="<%=request.getParameter("email")%>" name="email" id="email" required><br>
			    
			    <label for="mdp">Mot de passe : </label>
			    <input type="password" value="<%=request.getParameter("motDePasse")%>" name="mdp" id="mdp" required><br>
			    
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageListeEncheresConnecte.html"><input type="submit" value="Connexion"></a>
			    
			    <input type="checkbox" id="save" name="save">
 				<label for="save">Se souvenir de moi</label><br>
			</form>
		</div>
		<div>
			<a href="/Eclipse/Enchere-Eni/WebContent/Page Connexion/pageCreerCompte.html">Créer un compte</a>
		</div>
	</body>
</html>