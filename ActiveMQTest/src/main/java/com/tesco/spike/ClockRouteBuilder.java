package com.tesco.spike;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: gaurav
 * Date: 27/7/12
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClockRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:clockQueue").threads(5).marshal().xmljson().beanRef("clockListener","receiveMessage");
    }
}
