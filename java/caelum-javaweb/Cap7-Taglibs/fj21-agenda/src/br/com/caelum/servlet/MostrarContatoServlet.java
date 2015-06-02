package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

@WebServlet("/mostrarContato/*")
public class MostrarContatoServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(Util.ultimoElemento(req.getRequestURI()));
		ContatoDao dao = new ContatoDao();

		Contato contato = dao.pesquisar(id);

		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Contato</h1>");
		out.println("<dl>");
		out.println("<dt>Id:</dt> <dd>" + contato.getId() + "</dd>");
		out.println("<dt>Nome:</dt> <dd>" + contato.getNome() + "</dd>");
		out.println("<dt>E-mail:</dt> <dd>" + contato.getEmail() + "</dd>");
		out.println("<dt>Endereço:</dt> <dd>" + contato.getEndereco() + "</dd>");
		out.println("<dt>Nascimento:</dt> <dd>"
				+ Util.formatarData(contato.getDataNascimento()) + "</dd>");
		out.println("<dt>Telefone:</dt> <dd>" + contato.getTelefone() + "</dd>");
		out.println("</dl>");
		out.println("<a href='../listarContatos'>Lista</a>");

	};

}
