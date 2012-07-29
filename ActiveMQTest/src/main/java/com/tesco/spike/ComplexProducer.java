package com.tesco.spike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component("complexProducer")
public class ComplexProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    private TextMessage textMessage;

    public void createAndSendData() throws JMSException {

        for (int i = 0; i < 50; i++) {
            String msg = getData();
            if (msg != null) {
                msg=msg.replace("3", Integer.toString(i));
                sendMessage(msg);
                System.out.println("Sent message " + msg);
            }
        }
    }

    private void sendMessage(final String message) {
        jmsTemplate.send(
                new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(message);
                    }
                }
        );
    }

    private String getData() {
        StringBuilder str = new StringBuilder();
        try {

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ClockResult.xml");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line

            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                str.append(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
