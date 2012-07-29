package com.tesco.spike;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: gaurav
 * Date: 27/7/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class JMSRouteTest {

    @Autowired
    JmsTemplate jmsTemplate;

    private ComplexProducer complexProducer;

    @Before
    public void setUp() throws Exception {
        complexProducer = new ComplexProducer();
        complexProducer.setJmsTemplate(jmsTemplate);
    }

    @Test
    public void testXMLSendAndReceive() throws Exception {
        complexProducer.createAndSendData();
    }
}
