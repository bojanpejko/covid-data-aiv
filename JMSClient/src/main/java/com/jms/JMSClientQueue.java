package com.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSClientQueue {

    public static void main(String[] args) throws JMSException, NamingException {
        InitialContext context = new InitialContext();
        Queue queue = (Queue) context.lookup("jms/queue/test");
        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");

        QueueConnection connection = factory.createQueueConnection("guest", "guest");
        QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        QueueSender sender = session.createSender(queue);

        MapMessage mapMessage = session.createMapMessage();

        mapMessage.setString("action", "addData");
        mapMessage.setString("region", "bd09fba1-48d8-4fce-9d2d-ed76e6cc93f2");
        mapMessage.setString("infected","600");
        mapMessage.setString("hospitalized","20");
        mapMessage.setString("tested","3000");

        sender.send(mapMessage, DeliveryMode.NON_PERSISTENT, 3,2000);
        session.close();
    }
}
