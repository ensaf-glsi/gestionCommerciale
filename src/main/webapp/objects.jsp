<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<pre>
	ligne 1
	ligne 2
		ligne 3
		\${paramValues["loisirs"]} : ${paramValues["loisirs"]}
		\${param["loisirs"]} : ${param["loisirs"]}	
		\${header["Connection"]} : ${header["Connection"]}	
		\${header["Accept-Language"]} : ${header["Accept-Language"]}	
	</pre>
	
	test modif
	modif a chaud
	
<%
	String[] loisirs = request.getParameterValues("loisirs");
	for(int i = 0; i < loisirs.length; i++) {
		out.print("<br />" + loisirs[i]);
	}
%>
</body>
</html>