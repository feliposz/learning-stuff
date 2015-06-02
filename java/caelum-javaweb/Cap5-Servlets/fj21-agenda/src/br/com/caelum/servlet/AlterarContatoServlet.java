package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.Util;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.DaoException;
import br.com.caelum.jdbc.modelo.Contato;

@WebServlet("/alterarContato/*")
public class AlterarContatoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(Util.ultimoElemento(req.getRequestURI()));
		ContatoDao dao = new ContatoDao();

		Contato contato = dao.pesquisar(id);

		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Alterar contato</h1>");
		out.println("<form method='post' action='alterarContato/"
				+ contato.getId() + "'>");
		out.println("<dl>");
		out.println("<dt>Id:</dt> <dd>" + contato.getId() + "</dd>");
		out.println("<dt>Nome:</dt> <dd><input name='nome' value='"
				+ contato.getNome() + "'/></dd>");
		out.println("<dt>E-mail:</dt> <dd><input name='email' value='"
				+ contato.getEmail() + "'/></dd>");
		out.println("<dt>Endereço:</dt> <dd><input name='endereco' value='"
				+ contato.getEndereco() + "'/></dd>");
		out.println("<dt>Nascimento:</dt> <dd><input name='dataNascimento' value='"
				+ Util.formatarData(contato.getDataNascimento()) + "'/></dd>");
		out.println("<dt>Telefone:</dt> <dd><input name='telefone' value='"
				+ contato.getTelefone() + "'/></dd>");
		out.println("</dl>");
		out.println("<input action='action' type='submit' value='Gravar' />");
		out.println("<input action='action' type='button' value='Cancelar' onclick='window.location=\"../listarContatos\";' />");
		out.println("</form></body></html>");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws IOException, ServletException {

		PrintWriter out = resp.getWriter();
		ContatoDao dao = new ContatoDao();
		int id = Integer.parseInt(Util.ultimoElemento(request.getRequestURI()));

		Contato contato = new Contato();
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataTexto = request.getParameter("dataNascimento");
		String telefone = request.getParameter("telefone");
		Calendar dataNascimento = null;
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			throw new DaoException(e);
		}

		contato.setId((long) id);
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);
		contato.setTelefone(telefone);

		dao.altera(contato);
		resp.sendRedirect("../../listarContatos");
		out.close();
	}

}
