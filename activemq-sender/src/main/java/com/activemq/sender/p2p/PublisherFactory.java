package com.activemq.sender.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 点对点模式消息生产者
 * 
 * @author fengchao
 *
 */
public class PublisherFactory {

	public static Publisher getPublisher(String url) {
		Publisher publish = new Publisher(url);
		return publish;
	}

	public static class Publisher {
		private String brokerUrl = "tcp://192.168.0.37:61616";
		private ConnectionFactory factory;
		private Connection connection;
		private Session session;
		private MessageProducer producer;

		private Publisher(String brokerurl) {
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
				Destination destation = session.createQueue("p2p.JOBS"); // 创建消息队列
				producer = session.createProducer(destation); // 创建消息生产者
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 发送测试
		 * 
		 * @param message
		 */
		public <T extends Message> void sendMsg(T message) {
			try {
				producer.send(message);
				System.out.println("消息已发送:" + message + " to destation:" + producer.getDestination());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		public void close() throws JMSException{
			this.getProducer().close();
			this.getSession().close();
			this.getConnection().close();
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

		public MessageProducer getProducer() {
			return producer;
		}

		public void setProducer(MessageProducer producer) {
			this.producer = producer;
		}
	}

}
