package org.activemq.receive;

import org.activemq.receive.p2p.CustomerFactory;
import org.activemq.receive.p2p.CustomerFactory.Customer;
import org.junit.Before;
import org.junit.BeforeClass;

public class Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void testCustomer(){
		@SuppressWarnings("unused")
		Customer customer=CustomerFactory.getCustomer("tcp://192.168.0.37:61616");
	}
	@org.junit.Test
	public void testTopic(){
		@SuppressWarnings("unused")
		org.activemq.receive.topic.CustomerFactory.Customer customer=org.activemq.receive.topic.CustomerFactory.getCustomer("tcp://192.168.0.37:61616");
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*Customer customer=CustomerFactory.getCustomer("tcp://192.168.0.37:61616");*/
		org.activemq.receive.topic.CustomerFactory.Customer customer=org.activemq.receive.topic.CustomerFactory.getCustomer("tcp://192.168.0.37:61616");
	}
}
