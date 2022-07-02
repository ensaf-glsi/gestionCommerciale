<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${controllerPath}">Retour</a>
	<h1>${(entity != null) ? 'Modifier un' : 'Ajouter un nouveau' } produit : </h1>
	<form onSubmit="valider(event)" action="${controllerPath}" method="POST">
		<input type="hidden" name="_method" value="${(entity != null) ? 'PUT' : 'POST' }">
		<input type="hidden" name="id" value="${entity.id}" />
		Nom : <input name="name" value="${entity.name}" required /> <br />
		PU : <input type="number" name="price" value="${entity.price}" /> <br />
		Unité : <input name="unit" value="${entity.unit}" /> <br />
		<button type="reset">Réinitialiser</button>
		<button type="submit">Valider</button>
	</form>

	<script type="text/javascript">
		function valider(event) {
			// ajouter les controles front ici
			//event.preventDefault();
		}
	</script>
</body>
</html>