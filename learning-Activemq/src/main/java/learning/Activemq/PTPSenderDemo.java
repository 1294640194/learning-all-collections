package learning.Activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PTPSenderDemo {
public static void main(String[] args) throws JMSException, InterruptedException {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("test");
		
		 MessageProducer producer = session.createProducer(destination);
		
		int i =0;
		
		while(i !=100 ){
			i++;
			TextMessage message = session.createTextMessage(""+i+""+i+""+i+""+i);
			producer.send(message);
			session.commit();
		}
		
	}
}
