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

    private int threadPoolSize;
    private int maxThreadPoolSize;

    @Override
    public void configure() throws Exception {
//        from("jms:queue:clockQueue").threads(5).marshal().xmljson().beanRef("clockService","ingestClockResult");
        from("jms:queue:clockQueue").threads(threadPoolSize, maxThreadPoolSize).beanRef("clockService", "ingestClockResult");
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public void setMaxThreadPoolSize(int maxThreadPoolSize) {
        this.maxThreadPoolSize = maxThreadPoolSize;
    }
}
