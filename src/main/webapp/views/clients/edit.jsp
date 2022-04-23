<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${(entity != null) ? 'Modifier un' : 'Ajouter un nouveau' } client : </h1>
	<a href="list">Retour</a>
	<form action="${pageContext.request.contextPath}/clients" method="post">
		<input type="hidden" name="id" value="${entity.id}" />
		Nom : <input name="nom" value="${entity.nom}" /> <br />
		Tél. : <input name="tel" value="${entity.tel}" /> <br />
		Email : <input name="email" value="${entity.email}" /> <br />
		Adresse : <input name="adresse" value="${entity.adresse}" /> <br />
		<button type="reset">Réinitialiser</button> <button type="submit">Valider</button>
	</form>

</body>
</html>