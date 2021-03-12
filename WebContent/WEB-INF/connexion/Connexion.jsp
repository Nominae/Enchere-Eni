<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Connexion</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  			<a class="navbar-brand" href="<%=request.getContextPath()%>/NonConnecte">ENI-Encheres</a>
    			<span class="navbar-text">
      			Bienvenue sur le site enchère v1.0
    			</span>
  			</div>
		</nav>
		<br>
		<p class="erreur">${message}</p>
		<br>
		
		<form action="<%=request.getContextPath()%>/Validation" method="POST">
  			<div class="form-group">
    			<label for="EmailouPseudo">Email ou Pseudo :</label>
    			<input type="text" class="form-control" name="EmailouPseudo" id="Email ou Pseudo :" placeholder="Entrez email ou pseudo" required>
    			
  			</div>
  			<div class="form-group">
    			<label for="motDePasse">Password</label>
   				<input type="password" class="form-control" name="motDePasse" id="motDePasse" placeholder="Mot de passe" aria-describedby="passwordHelp" required>
   				<small id="passwordHelp" class="form-text text-muted">Ne partagez jamais votre mot de passe.</small>
  			</div>
  			<div class="form-check">
    			<input type="checkbox" class="form-check-input" id="save" name="save">
    			<label class="form-check-label" for="save">Se souvenir de moi</label>
  			</div>
  			<button type="submit" class="btn btn-primary">Connection</button>
		</form>
		<div>
			<a href="<%=request.getContextPath()%>/creationcompte">Créer un compte</a>
		</div>
	
	</body>
</html>