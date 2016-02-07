package com.wang.yan.mvc.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Queue;

/**
 * Created by ywang on 07/02/16.
 */
public class JmsDemo {

        public static void main(String[] args) {
            // init spring context
            ApplicationContext ctx = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");

            // get bean from context
            JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");

            // send to default destination
            jmsMessageSender.send("hello JMS");

            // send to a code specified destination
            Queue queue = new ActiveMQQueue("AnotherDest");
            jmsMessageSender.send(queue, "hello Another Message");

            // close spring application context
            ((ClassPathXmlApplicationContext)ctx).close();
        }
}
