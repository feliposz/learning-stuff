<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="br.com.caelum.jdbc.dao.*,br.com.caelum.jdbc.modelo.*,br.com.caelum.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novo contato</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery-ui.theme.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
</head>
<body>

  <c:import url="cabecalho.jsp" />

  <div class="container">

    <form class="form-horizontal" role="form" action="adicionar-contato-salvar.jsp" method="post">

      <div class="panel panel-primary">
        <div class="panel-heading">Novo contato</div>
        <div class="panel-body">

          <div class="form-group">
            <caelum:campoTextoLb rotulo="Nome" id="nome" />
          </div>
          <div class="form-group">
            <caelum:campoTextoLb rotulo="E-mail" id="email" />
          </div>
          <div class="form-group">
            <caelum:campoTextoLb rotulo="Endereço" id="endereco" />
          </div>
          <div class="form-group">
            <caelum:campoTextoLb rotulo="Telefone" id="telefone" />
          </div>
          <div class="form-group">
            <caelum:campoDataLb rotulo="Nascimento" id="dataNascimento" />
          </div>

        </div>
        <div class="panel-footer">
          <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-ok"></span> Confirmar
          </button>
          <a href="lista-contatos.jsp" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span>
            Cancelar</a>
        </div>

      </div>

    </form>

  </div>

  <c:import url="rodape.jsp" />

  <script src="js/bootstrap.js"></script>

</body>
</html>