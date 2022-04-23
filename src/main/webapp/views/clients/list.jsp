<!DOCTYPE html>
<%@page import="ma.ensaf.model.Client"%>
<%@page import="java.util.List"%>
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
			<%
			List<Client> list = (List<Client>) request.getAttribute("list");
			for (Client c : list) {
			%>
			<tr>
				<td>
					<%
					out.print(c.getNom());
					%>
				</td>
				<td><%=c.getEmail()%></td>
				<td><%=c.getTel()%></td>
				<td><%=c.getAdresse()%></td>
				<td>
					<a href="${pageContext.request.contextPath}/clients/edit?id=<%= c.getId()%>">Modifier</a>
					 delete</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>