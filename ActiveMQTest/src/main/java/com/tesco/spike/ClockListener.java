package com.tesco.spike;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: gaurav
 * Date: 27/7/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClockListener {

    @Autowired
    public JmsTemplate jmsTemplate;

    public void receiveMessage(String message) {
            System.out.println(Thread.currentThread().getName()+">>>>Message Received >>>>>>"+message);
    }
}
