package org.activemq.receive.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**消息订阅测试
 * 消息订阅模式只能接收当前消息，过期消息无法接收
 * @author fengchao
 *
 */
public class CustomerFactory{

	public static Customer getCustomer(String url) {
		Customer customer = new Customer(url);
		return customer;
	}

	public static class Customer {
		private String brokerUrl = "tcp://192.168.0.37:61616";
		private ConnectionFactory factory;
		private Connection connection;
		private Session session;
		private MessageConsumer customer;

		private Customer(String brokerurl) {
			init(brokerurl);
		}

		/**
		 * 初始化基本配置
		 * 
		 * @param publish
		 */
		private void init(String brokerurl) {
			this.brokerUrl = brokerurl;
			factory = new ActiveMQConnectionFactory(brokerurl);
			try {
				connection = factory.createConnection();
				connection.start();              //启动连接
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destation = session.createTopic("mySubscribe");
				customer = session.createConsumer(destation); // 创建消息接收者
				customer.setMessageListener(new receiveListener());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		/**接收消息
		 * @throws JMSException
		 */
		public void receiveMsg() throws JMSException {
			this.customer.receive();
		}

		public String getBrokerUrl() {
			return brokerUrl;
		}

		public void setBrokerUrl(String brokerUrl) {
			this.brokerUrl = brokerUrl;
		}

		public ConnectionFactory getFactory() {
			return factory;
		}

		public void setFactory(ConnectionFactory factory) {
			this.factory = factory;
		}

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}

		public MessageConsumer getCustomer() {
			return customer;
		}

		public void setCustomer(MessageConsumer customer) {
			this.customer = customer;
		}
	}
	private static class receiveListener implements MessageListener{

		@Override
		public void onMessage(Message message) {
			System.out.println("收到一条消息:"+message+"from queue");
		}
		
	}
}
