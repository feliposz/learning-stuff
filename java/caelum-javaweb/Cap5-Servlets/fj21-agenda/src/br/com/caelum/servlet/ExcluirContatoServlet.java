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

@WebServlet("/excluirContato/*")
public class ExcluirContatoServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		ContatoDao dao = new ContatoDao();
		int id = Integer.parseInt(Util.ultimoElemento(req.getRequestURI()));
		Contato contato = dao.pesquisar(id);
		out.println("<html><body>");
		out.println("<h1>Excluir contato</h1>");
		out.println("<p>Deseja mesmo excluir o contato " + contato.getNome() + "?</p>");
		out.println("<form method='post' action='excluirContato/" + contato.getId() + "'>");
		out.println("<input action='action' type='submit' value='Sim' />");
		out.println("<input action='action' type='button' value='Não' onclick='window.location=\"/fj21-agenda/listarContatos\";' />");
		out.println("</form></body></html>");
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		ContatoDao dao = new ContatoDao();
		int id = Integer.parseInt(Util.ultimoElemento(req.getRequestURI()));
		dao.pesquisar(id);
		Contato contato = dao.pesquisar(id);
		dao.remove(contato);
		resp.sendRedirect("/fj21-agenda/listarContatos");
		out.close();
	}	
}
