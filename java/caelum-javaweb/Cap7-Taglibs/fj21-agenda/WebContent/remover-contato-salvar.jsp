<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.text.*,java.util.*,br.com.caelum.jdbc.dao.*,br.com.caelum.jdbc.modelo.*,br.com.caelum.jdbc.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alteração de contato</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

  <div class="page-header">
    <h1>Alteração de contato</h1>
  </div>

  <div class="container">

    <%
    	Long id = Long.parseLong(request.getParameter("id"));

    	ContatoDao dao = new ContatoDao();
    	Contato contato = new Contato();
    	contato.setId(id);

    	boolean sucesso;
    	String msg;

    	try {
    		dao.remove(contato);
    		sucesso = true;
    		msg = "Removido com sucesso.";
    	} catch (Exception e) {
    		sucesso = false;
    		msg = e.getMessage();
    	}
    %>

    <div class="alert alert-<%=sucesso ? "success" : "danger"%>" role="alert"><%=msg%></div>

    <a href="lista-contatos.jsp" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> Lista de
      contatos</a>


  </div>

  <script src="js/jquery.js"></script>
  <script src="js/bootstrap.js"></script>

</body>
</html>