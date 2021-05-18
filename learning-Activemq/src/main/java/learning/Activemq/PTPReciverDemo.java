package learning.Activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.util.StringUtils;

public class PTPReciverDemo {

	public static void main(String[] args) throws JMSException, InterruptedException {
	    
	    System.out.println(" Git 测试 的 第二版本");
	    System.out.println(" Git 测试 的 第3版本");
	    System.out.println(" Git 分支合并测试，来自 hot-fix 分支");
	    System.out.println(" Master 分支的冲突数据准备 ！！！ ");
	    System.out.println(" hot-fix 分支的冲突数据准备 ！！！");
	    System.out.println(" push 到 GitHub 的数据准备 ！！！");

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("test");
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		int i =0;
		
		while(true ){
			TextMessage message = (TextMessage)consumer.receive();
			System.out.println("conusmer1收到的："+message.getText());
			
			if(i == 3){  // i=3的时候才提交事务签收。否则不签收
				session.commit();
			}
			
			i++;
		}
		
	}

}
