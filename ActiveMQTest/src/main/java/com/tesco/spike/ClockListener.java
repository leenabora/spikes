package com.tesco.spike;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Handler;

import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: gaurav
 * Date: 27/7/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClockListener {


    ConsumerTemplate consumerTemplate;
    @Handler

    public void receiveMessage(Message message) {
//        try {
            System.out.println("$%$#@$@Message Received >>>>>>");
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }

    }

    public void onMessage(Message message) {
        System.out.println("Received Msg: "+message.toString());
    }
}
