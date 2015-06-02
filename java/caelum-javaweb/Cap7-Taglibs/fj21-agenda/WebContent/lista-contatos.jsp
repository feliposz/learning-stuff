<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de contatos</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery.js"></script>
<style>
.acoes {
	width: 90px;
}
</style>
</head>

<body>

  <c:import url="cabecalho.jsp" />

  <div class="container">

    <div class="panel panel-default panel-primary">

      <div class="panel-heading">Lista de contatos</div>
      <div class="panel-body">

        <table class="table table-striped table-bordered">
          <tbody>
            <tr>
              <th>Nome</th>
              <th>E-mail</th>
              <th>Endereço</th>
              <th>Telefone</th>
              <th>Data de nascimento</th>
              <th class="acoes">Ações</th>
            </tr>

            <jsp:useBean id="dao" class="br.com.caelum.jdbc.dao.ContatoDao" />

            <c:forEach var="contato" items="${dao.lista}" varStatus="numLinha">
              <!-- <tr class="${numLinha.count % 2 == 0 ? 'success' : 'info' }">  -->
              <tr>
                <td>${contato.nome}</td>
                <td><c:choose>
                    <c:when test="${not empty contato.email }">
                      <a href="mailto:${contato.email}">${contato.email}</a>
                    </c:when>
                    <c:otherwise>(e-mail não informado)</c:otherwise>
                  </c:choose></td>
                <td>${contato.endereco}</td>
                <td>${contato.telefone}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${contato.dataNascimento.time}"></fmt:formatDate></td>
                <td class="acoes"><div class="btn-group btn-group-xs">
                    <a href="alterar-contato.jsp?id=${contato.id}" class="btn btn-default"><span
                      class="glyphicon glyphicon-pencil"></span></a> <a href="exibir-contato.jsp?id=${contato.id}"
                      class="btn btn-default"><span class="glyphicon glyphicon-eye-open"></span></a> <a
                      href="remover-contato.jsp?id=${contato.id}" class="btn btn-default"><span
                      class="glyphicon glyphicon-trash"></span></a>
                  </div></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="panel-footer">
        <a href="adicionar-contato.jsp" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span>
          Adicionar contato</a>
      </div>
    </div>
  </div>

  <c:import url="rodape.jsp" />

  <script src="js/bootstrap.js"></script>

</body>

</html>