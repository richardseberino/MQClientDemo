package com.ibm.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadMessages
 */
@WebServlet("/ReadMessages")
public class ReadMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fila = request.getParameter("queue");
		int qtd = Integer.parseInt(request.getParameter("qtd"));
		String mensagem = request.getParameter("mensagem");
		String qm = request.getParameter("qm");
		String channel = request.getParameter("channel");
		String host = request.getParameter("host");
		int port = Integer.parseInt(request.getParameter("port"));
		
		try
		{
			int y=0;
			MensagemMQ mq = new MensagemMQ();
			mq.consumingMessages(host, port, channel, qm, fila, mensagem, qtd);
			response.getWriter().append(y + " Message(s) read!");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			response.getWriter().append("Error: " + e.getMessage());
			response.getWriter().flush();
			response.getWriter().close();
		}
		
	}

}
