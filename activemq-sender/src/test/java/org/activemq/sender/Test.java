package org.activemq.sender;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.BeforeClass;

import com.activemq.sender.p2p.PublisherFactory;
import com.activemq.sender.p2p.PublisherFactory.Publisher;

public class Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void test() throws JMSException {
		Publisher publish=PublisherFactory.getPublisher("tcp://192.168.0.37:61616");
		TextMessage msg=publish.getSession().createTextMessage("hello,i am test p2p message"); 
		publish.sendMsg(msg);
	}
	@org.junit.Test
	public void testTopic() throws JMSException{
		com.activemq.sender.topic.PublisherFactory.Publisher publish=com.activemq.sender.topic.PublisherFactory.getPublisher("tcp://192.168.0.37:61616");
		TextMessage msg=publish.getSession().createTextMessage("hello,i am test topic message"); 
		publish.sendMsg(msg);
	}
	public static void main(String[] args) throws JMSException {
		Publisher publish=PublisherFactory.getPublisher("tcp://192.168.0.37:61616");
		TextMessage msg=publish.getSession().createTextMessage("hello,i am test p2p message,No");
		publish.sendMsg(msg);
		publish.close();
	}

}
