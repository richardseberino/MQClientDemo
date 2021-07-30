package com.ibm.demo;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnviaMEnsagens
 */
@WebServlet("/EnviaMensagens")
public class EnviaMEnsagens extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviaMEnsagens() {
        super();
        
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
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		int port = Integer.parseInt(request.getParameter("port"));
		int iterations = Integer.parseInt(request.getParameter("iterations"));
		int wait = Integer.parseInt(request.getParameter("wait"));
		
		
		try
		{
			int y=0;
			MensagemMQ mq = new MensagemMQ();
			for (int x=0; x< iterations; x++)
			{
				for (int i=0; i< qtd; i++)
				{
					y++;
					mq.enviaMensagem(host, port, channel, qm, fila, mensagem, user, password);
				}
				Thread.sleep(wait);
			}
		
			response.getWriter().append(y + " Message(s) sent!");
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
