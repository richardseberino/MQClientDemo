package com.ibm.demo;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class MensagemMQ {

	public static void main(String[] args)
	{
		MensagemMQ ms = new MensagemMQ();
		try
		{
			ms.enviaMensagem("mq03-ibm-mq-qm-cp4i.cp4i-cluster-510ad6ebead8e7457a6e62904edfa48f-0000.eu-gb.containers.appdomain.cloud", 443, "MQ.QS.SVRCONN", "CUSTOM", "FILA1", "Ola MUndo", "","");
			System.out.println("Mensagem enviada com sucesso!");
		}
		catch (JMSException e)
		{
			System.err.println("Erro: " + e.getMessage());
		}
		
	}
	public void enviaMensagem(String host, int port, String channel,String qm, String fila, String mensagem, String user, String password) throws JMSException
	{
	

			  JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			  JmsConnectionFactory cf = ff.createConnectionFactory();

			  cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host); 
			  cf.setIntProperty(WMQConstants.WMQ_PORT, port);
			  cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
			  cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			  cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, qm);
			  cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "JmsPut (JMS)");
	          
	          if (password!=null && password.length()>0)
	          {
	        	  cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
		          cf.setStringProperty(WMQConstants.USERID, user);
	        	  cf.setStringProperty(WMQConstants.PASSWORD, password);
	          }
 
			  Connection connection = cf.createConnection();
			  Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			  Destination destination = session.createQueue("queue:///" + fila);
			  MessageProducer producer = session.createProducer(destination);

			  TextMessage message = session.createTextMessage(mensagem);
			  connection.start();
			  producer.send(message);
			  connection.close();


	}
	
	public void consumingMessages(String host, int port, String channel,String qm, String fila, String mensagem, int messages) throws JMSException
	{
		  JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
		  JmsConnectionFactory cf = ff.createConnectionFactory();

		  cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host); 
		  cf.setIntProperty(WMQConstants.WMQ_PORT, port);
		  cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
		  cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		  cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, qm);
		  cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "JmsPut (JMS)");
        

		  Connection connection = cf.createConnection();
		  Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		  Destination queue = session.createQueue("queue:///" + fila);
		  MessageConsumer consumer = session.createConsumer(queue);
		  connection.start();
		  for (int i=0; i < messages; i++)
		  {                                           // run forever
			  Message msg = consumer.receive();
		  }
		  connection.close();
	}
	
}
