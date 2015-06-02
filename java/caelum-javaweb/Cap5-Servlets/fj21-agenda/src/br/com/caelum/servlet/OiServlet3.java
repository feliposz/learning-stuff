package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name = "OiServlet3",
		urlPatterns = {"/oi3"},
		// Definindo parâmetros de inicialização (pra que serve isso?!?!?!)
		initParams = {
				@WebInitParam(name = "param1", value = "value1"),
				@WebInitParam(name = "param2", value = "value2")
		}
)
public class OiServlet3 extends HttpServlet {

	private String parametro1;
	private String parametro2;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Primeiro método para pegar os parâmetros de inicialização
		this.parametro1 = config.getInitParameter("param1");
		this.parametro2 = config.getInitParameter("param2");
		log("Iniciando o servlet OiServlet3");
	}
	
	@Override
	public void destroy() {		
		super.destroy();
		log("Iniciando o servlet OiServlet3");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h2>Exemplo com InitParam Servlet</h2>");

		// Segundo método para pegar os parâmetros de inicialização
		ServletConfig config = getServletConfig();
		
		String parametro1 = config.getInitParameter("param1");
		out.println("<p>Valor do parâmetro 1: " + parametro1 + "</p>");
		
		String parametro2 = config.getInitParameter("param2");
		out.println("<p>Valor do parâmetro 2: " + parametro2 + "</p>");
		
		out.close();
	}
}
