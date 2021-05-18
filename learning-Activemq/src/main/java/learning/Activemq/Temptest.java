package learning.Activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Temptest {
public static void main(String[] args) throws JMSException, InterruptedException {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		//Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("test");
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		TextMessage message = (TextMessage)consumer.receive();
		consumer.receiveNoWait();
		
		int i =0;
		while(message != null ){
			
			System.out.println("conusmer2收到的："+message.getText());
			message = (TextMessage)consumer.receive();
			
			
			
		}
		
	}
}
