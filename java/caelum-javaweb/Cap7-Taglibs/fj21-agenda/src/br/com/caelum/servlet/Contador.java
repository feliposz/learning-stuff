package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contador")
public class Contador extends HttpServlet {

	// Esta vari�vel ser� compartilhada entre todas as threads do servlet
	private int contador = 0;

	// Para evitar acesso simult�neo
	//             |
	//             V
	protected synchronized void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// Se houver acesso simult�neo, as vari�veis podem ser afetadas.
		// Sempre que poss�vel, � bom evitar compartilhar vari�veis no servlet.
		// Se isso for necess�rio, o acesso deve ser sincronizado para evitar
		// problemas, colocando a palavra chave synchronized no m�todo service.
		// Isso ir� afetar a escalabilidade, no entanto, j� que somente uma
		// requisi��o poder� ser atendida por vez.
		
		contador++;

		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<p>Contador: " + contador + "</p>");
		out.println("</body></html>");

	}

}
