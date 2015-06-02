package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

@WebServlet("/listarContatos")
public class ListarContatosServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		ContatoDao dao = new ContatoDao();
		List<Contato> lista = dao.getLista("");

		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Lista de contatos</h1>");
		out.println("<a href='adiciona-contato.html'>Novo</a>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>Nome</th>");
		out.println("<th>E-mail</th>");
		out.println("<th>Endereço</th>");
		out.println("<th>Nascimento</th>");
		out.println("<th>Telefone</th>");
		out.println("<th>&nbsp;</th>");
		out.println("</tr>");
		for (Contato c : lista) {
			out.println("<tr>");
			out.println("<td>" + c.getId() + "</td>");
			out.println("<td>" + c.getNome() + "</td>");
			out.println("<td>" + c.getEmail() + "</td>");
			out.println("<td>" + c.getEndereco() + "</td>");
			out.println("<td>" + Util.formatarData(c.getDataNascimento())
					+ "</td>");
			out.println("<td>" + c.getTelefone() + "</td>");
			out.println("<td>");
			out.println("<a href=\"alterarContato/" + c.getId()
					+ "\">Alterar</a>");
			out.println("<a href=\"mostrarContato/" + c.getId()
					+ "\">Mostrar</a>");
			out.println("<a href=\"excluirContato/" + c.getId()
					+ "\">Excluir</a>");
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
