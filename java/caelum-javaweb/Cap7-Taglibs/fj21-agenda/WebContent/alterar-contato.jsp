<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="br.com.caelum.jdbc.dao.*,br.com.caelum.jdbc.modelo.*,br.com.caelum.jdbc.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar contato</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

  <div class="page-header">
    <h1>Alterar contato contato</h1>
  </div>

  <div class="container">

    <%
			ContatoDao dao = new ContatoDao();
			long id = Long.parseLong(request.getParameter("id"));
			Contato contato = dao.pesquisar(id);
		%>

    <form class="form-horizontal" role="form" action="alterar-contato-salvar.jsp" method="post">

      <div class="panel panel-primary">
        <div class="panel-heading">Contato</div>
        <div class="panel-body">


          <div class="form-group">
            <label for="id" class="col-sm-2 control-label">Id</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" value="<%=contato.getId()%>" disabled="disabled" /> <input
                type="hidden" name="id" value="<%=contato.getId()%>" />
            </div>
          </div>
          <div class="form-group">
            <label for="nome" class="col-sm-2 control-label">Nome</label>
            <div class="col-sm-10">
              <input name="nome" type="text" class="form-control" value="<%=contato.getNome()%>" />
            </div>
          </div>
          <div class="form-group">
            <label for="email" class="col-sm-2 control-label">E-mail</label>
            <div class="col-sm-10">
              <input name="email" type="text" class="form-control" value="<%=contato.getEmail()%>" />
            </div>
          </div>
          <div class="form-group">
            <label for="endereco" class="col-sm-2 control-label">Endere�o</label>
            <div class="col-sm-10">
              <input name="endereco" type="text" class="form-control" value="<%=contato.getEndereco()%>" />
            </div>
          </div>
          <div class="form-group">
            <label for="telefone" class="col-sm-2 control-label">Telefone</label>
            <div class="col-sm-10">
              <input name="telefone" type="text" class="form-control" value="<%=contato.getTelefone()%>" />
            </div>
          </div>
          <div class="form-group">
            <label for="dataNascimento" class="col-sm-2 control-label">Nascimento</label>
            <div class="col-sm-10">
              <input name="dataNascimento" type="text" class="form-control"
                value="<%=Util.formatarData(contato.getDataNascimento())%>" />
            </div>
          </div>



        </div>
        <div class="panel-footer">
          <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-ok"></span> Salvar
          </button>
          <a href="lista-contatos.jsp" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span>
            Cancelar</a>
        </div>

      </div>

    </form>


  </div>

  <script src="js/jquery.js"></script>
  <script src="js/bootstrap.js"></script>

</body>
</html>