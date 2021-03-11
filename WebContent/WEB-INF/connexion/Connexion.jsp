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
			<a href="<%=request.getContextPath()%>/NonConnecte">ENI-Encheres</a>
		</nav>
		<br>
		<p class="erreur">${message}</p>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/Validation" method="POST" class="form">
			   
			    <label for="EmailouPseudo">Email ou Pseudo : </label>
			    <input type="text" name="EmailouPseudo" id="EmailouPseudo" required><br>
			    
			    <label for="motDePasse">Mot de passe : </label>
			    <input type="password" name="motDePasse" id="motDePasse" required><br>
			    
			    <a href="/Eclipse/Enchere-Eni/WebContent/Page Accueil/pageListeEncheresConnecte.html"><input type="submit" value="Connexion"></a>
			    
			    <input type="checkbox" id="save" name="save">
 				<label for="save">Se souvenir de moi</label><br>
 				
			</form>
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/creationcompte">Créer un compte</a>
		</div>
	</body>
</html>