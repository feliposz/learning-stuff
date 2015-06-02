<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bem-vindo</title>
</head>
<body>

	<%-- comentário em JSP aqui: nossa primeira página JSP --%>

	<%
		String mensagem = "Bem vindo ao sistema de agenda do FJ-21!";
	%>
	<p>
		<%
			out.println(mensagem);
		%>
	</p>

	<%
		String desenvolvimento = "Desenvolvido por Felipo Soranz";
	%>
	<p><%=desenvolvimento%></p>

	<%
		System.out.println("Tudo foi executado!");
	%>

</body>
</html>