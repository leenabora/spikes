package com.tesco.spike;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the queue we will receive messages from
    private static String subject = "TESTQUEUE";

    public static void main(String[] args) throws JMSException {
        Connection connection=null;
        try {
            // Getting JMS connection from the server
            ConnectionFactory connectionFactory
                    = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();

            // Creating session for seding messages
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // Getting the queue 'TESTQUEUE'
            Destination destination = session.createQueue(subject);

            // MessageConsumer is used for receiving (consuming) messages
            MessageConsumer consumer = session.createConsumer(destination);

            // Here we receive the message.
            // By default this call is blocking, which means it will wait
            // for a message to arrive on the queue.
            while (true) {
                Message message = consumer.receive();

                // There are many types of Message and TextMessage
                // is just one of them. Producer sent us a TextMessage
                // so we must cast to it to get access to its .getText()
                // method.
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("Received message '"
                            + textMessage.getText() + "'");
                }

            }
        } finally {

            connection.close();
        }
    }
}
