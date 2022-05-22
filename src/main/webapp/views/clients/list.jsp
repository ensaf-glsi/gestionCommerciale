<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Liste des clients :</h1>
	<a href="new">Ajouter</a>

	<table border="1">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Email</th>
				<th>Tél.</th>
				<th>Adresse</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${list}" var="item">
				<tr>
					<td>${item.nom}</td>
					<td>${item.email}</td>
					<td>${item.tel}</td>
					<td>${item.adresse}</td>
					<td>
						<a href="${pageContext.request.contextPath}/clients/edit?id=${item.id}">Modifier</a>
						 delete</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>