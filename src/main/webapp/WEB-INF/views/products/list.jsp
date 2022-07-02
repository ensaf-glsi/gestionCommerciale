<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des produits</title>
</head>
<body>
	<h1>Liste des produits :</h1>
	<a href="${controllerPath}/edit">Ajouter</a>

	<table border="1">
		<thead>
			<tr>
				<th>Réf.</th>
				<th>Nom</th>
				<th>PU</th>
				<th>Unité</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${list}" var="item">
				<tr>
					<td>
						<a href="${controllerPath}/view?id=${item.id}">${item.id}</a>
					</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.unit}</td>
					<td>
						<a href="${controllerPath}/edit?id=${item.id}">Modifier</a>
						<a onClick="confirmDelete(event)"
							href="${controllerPath}?id=${item.id}&_method=DELETE">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<script type="text/javascript">
	// alert
	// confirm => 
	function confirmDelete(event) {
// 		console.log('event ? ', event);
		if (!confirm('Etes vous de vouloir supprimer cette ligne ?')) {
			console.log('annulation de la suppression');
			event.preventDefault();
		}
	}
</script>
</body>
</html>