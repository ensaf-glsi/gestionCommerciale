<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${controllerPath}">Retour</a>
	<h1>Produit ${entity.id}</h1>
	
	<table border="1">
		<tbody>
			<tr>
				<th>Réf.</th>
				<td>${entity.id}</td>
			</tr>
			<tr>
				<th>Nom</th>
				<td>${entity.name}</td>
			</tr>
			<tr>
				<th>PU</th>
				<td>${entity.price}</td>
			</tr>
			<tr>
				<th>Unité</th>
				<td>${entity.unit}</td>
			</tr>
		</tbody>
	</table>

</body>
</html>