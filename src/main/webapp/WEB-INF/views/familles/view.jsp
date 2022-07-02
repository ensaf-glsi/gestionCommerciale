<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${controllerPath}">Retour</a>
	<h1>Famille ${entity.id}</h1>
	
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
		</tbody>
	</table>

</body>
</html>