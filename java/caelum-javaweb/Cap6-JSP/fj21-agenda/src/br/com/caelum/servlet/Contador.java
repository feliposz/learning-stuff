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

	// Esta variável será compartilhada entre todas as threads do servlet
	private int contador = 0;

	// Para evitar acesso simultâneo
	//             |
	//             V
	protected synchronized void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// Se houver acesso simultâneo, as variáveis podem ser afetadas.
		// Sempre que possível, é bom evitar compartilhar variáveis no servlet.
		// Se isso for necessário, o acesso deve ser sincronizado para evitar
		// problemas, colocando a palavra chave synchronized no método service.
		// Isso irá afetar a escalabilidade, no entanto, já que somente uma
		// requisição poderá ser atendida por vez.
		
		contador++;

		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<p>Contador: " + contador + "</p>");
		out.println("</body></html>");

	}

}
