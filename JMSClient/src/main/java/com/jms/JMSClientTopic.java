package com.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSClientTopic {

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        Topic topic = (Topic) context.lookup("jms/topic/test");
        TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("jms/RemoteConnectionFactory");

        TopicConnection connection = factory.createTopicConnection("guest", "guest");
        TopicSession session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        TopicPublisher publisher = session.createPublisher(topic);

        MapMessage mapMessage = session.createMapMessage();

        mapMessage.setString("action", "addData");
        mapMessage.setString("region", "bd09fba1-48d8-4fce-9d2d-ed76e6cc93f2");
        mapMessage.setString("infected","600");
        mapMessage.setString("hospitalized","20");
        mapMessage.setString("tested","3000");

        publisher.publish(mapMessage,DeliveryMode.NON_PERSISTENT,3,2000);
        session.close();
    }
}
