<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, br.com.caelum.jdbc.*, br.com.caelum.jdbc.dao.*, br.com.caelum.jdbc.modelo.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de contatos</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

	<div class="page-header">
		<h1>Lista de contatos</h1>
	</div>

	<div class="container">

		<div class="panel panel-default panel-primary">

			<div class="panel-heading">Contatos</div>
			<div class="panel-body">

				<table class="table table-striped table-bordered">
					<tbody>
						<tr>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Endereço</th>
							<th>Telefone</th>
							<th>Data de nascimento</th>
							<th>Ações</th>
						</tr>
						<%
							ContatoDao dao = new ContatoDao();
							Collection<Contato> contatos = dao.getLista("");
							for (Contato contato : contatos) {
						%>
						<tr>
							<td><%=contato.getNome()%></td>
							<td><%=contato.getEmail()%></td>
							<td><%=contato.getEndereco()%></td>
							<td><%=contato.getTelefone()%></td>
							<td><%=Util.formatarData(contato.getDataNascimento())%></td>
							<td><div class="btn-group btn-group-xs">
									<a href="alterar-contato.jsp?id=<%=contato.getId()%>"
										class="btn btn-default"><span
										class="glyphicon glyphicon-pencil"></span></a> <a
										href="exibir-contato.jsp?id=<%=contato.getId()%>"
										class="btn btn-default"><span
										class="glyphicon glyphicon-eye-open"></span></a> <a
										href="remover-contato.jsp?id=<%=contato.getId()%>"
										class="btn btn-default"><span
										class="glyphicon glyphicon-trash"></span></a>
								</div></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>

			<div class="panel-footer">
				<a href="adicionar-contato.jsp" class="btn btn-default"><span
					class="glyphicon glyphicon-plus"></span> Adicionar contato</a>
			</div>
		</div>
	</div>

	<script src="js/bootstrap.js"></script>
</body>
</html>