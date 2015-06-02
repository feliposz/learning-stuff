<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="br.com.caelum.jdbc.dao.*,br.com.caelum.jdbc.modelo.*,br.com.caelum.jdbc.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exibir contato</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

	<div class="page-header">
		<h1>Exibir contato</h1>
	</div>

	<div class="container">

		<%
			ContatoDao dao = new ContatoDao();
			long id = Long.parseLong(request.getParameter("id"));
			Contato contato = dao.pesquisar(id);
		%>

		<div class="panel panel-primary">
			<div class="panel-heading">Contato</div>
			<div class="panel-body">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="id" class="col-sm-2 control-label">Id</label>
						<div class="col-sm-10">
							<input name="id" type="text" class="form-control"
								value="<%=contato.getId()%>" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label for="nome" class="col-sm-2 control-label">Nome</label>
						<div class="col-sm-10">
							<input name="nome" type="text" class="form-control"
								value="<%=contato.getNome()%>" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">E-mail</label>
						<div class="col-sm-10">
							<input name="email" type="text" class="form-control"
								value="<%=contato.getEmail()%>" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label for="endereco" class="col-sm-2 control-label">Endereço</label>
						<div class="col-sm-10">
							<input name="endereco" type="text" class="form-control"
								value="<%=contato.getEndereco()%>" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label for="telefone" class="col-sm-2 control-label">Telefone</label>
						<div class="col-sm-10">
							<input name="telefone" type="text" class="form-control"
								value="<%=contato.getTelefone()%>" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label for="dataNascimento" class="col-sm-2 control-label">Nascimento</label>
						<div class="col-sm-10">
							<input name="dataNascimento" type="text" class="form-control"
								value="<%=Util.formatarData(contato.getDataNascimento())%>"
								disabled="disabled" />
						</div>
					</div>

				</form>

			</div>
			<div class="panel-footer">
				<a href="lista-contatos.jsp" class="btn btn-default"><span
					class="glyphicon glyphicon glyphicon-list"></span> Lista de
					contatos</a>
			</div>

		</div>


	</div>

	<script src="js/bootstrap.js"></script>

</body>
</html>